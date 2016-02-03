package org.framework.authorize.auth.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.AmGroupDao;
import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.auth.services.AmGroupService;
import org.framework.authorize.base.model.ConstData;
import org.framework.authorize.base.utils.Pager;
import org.springframework.stereotype.Service;
/**
 * 采用左右值实现树形结构（测试中，有待优化）
 * @author ztgoto
 * @version  
 * @date  2016年1月25日 下午4:56:49
 */
@Service("amGroupService")
public class AmGroupServiceImpl implements AmGroupService {

	protected static final Log LOG=LogFactory.getLog(AmGroupServiceImpl.class);
	
	@Resource(name="amGroupDao")
	private AmGroupDao amGroupDao;
	
	@Override
	public void add(AmGroup amGroup) {
		Long leftValue=1L;
		Long rightValue=2L;
		
		if(amGroup.getParentGroupId()==null){
			LOG.debug(">>>>create root node>>>>");
			String productIdWhere=amGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
			String sql=productIdWhere+" AND PARENT_GROUP_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
			Map<String, Object> parameters=new HashMap<String, Object>();
			parameters.put("productId", amGroup.getProductId());
			List<AmGroup> groups=amGroupDao.getByWhere(sql, "par",parameters );
			if(groups!=null&&groups.size()>0){
				AmGroup maxGroup=groups.get(0);
				leftValue=maxGroup.getRightValue()+1;
				rightValue=maxGroup.getRightValue()+2;
			}
		}else{
			LOG.debug(">>>>create subnode>>>>");
			AmGroup parentGroup=amGroupDao.getById(amGroup.getParentGroupId());
			if(parentGroup==null) throw new RuntimeException(">>>>["+amGroup.getParentGroupId()+"] node non-existent");
			leftValue=parentGroup.getRightValue();
			rightValue=leftValue+1;
			
			String productIdWhere=amGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[1]}";
			
			String sql="update am_group set RIGHT_VALUE=RIGHT_VALUE+2 where RIGHT_VALUE>=#{par[0]} and "+productIdWhere;
			amGroupDao.updateBySql(sql, "par", leftValue,amGroup.getProductId());
			sql="update am_group set LEFT_VALUE=LEFT_VALUE+2 where LEFT_VALUE>=#{par[0]} and "+productIdWhere;
			amGroupDao.updateBySql(sql, "par", leftValue,amGroup.getProductId());
		}
		LOG.debug(">>>>LEFT_VALUE:"+leftValue+",RIGHT_VALUE:"+rightValue);
		amGroup.setLeftValue(leftValue);
		amGroup.setRightValue(rightValue);
		Date currentDate=new Date();
		amGroup.setCreateTime(currentDate);
		amGroup.setEditTime(currentDate);
		amGroup.setDataState(ConstData.DATA_NORMAL);
		int result=amGroupDao.save(amGroup);
		LOG.debug(">>>>Add Result Value:"+result);
	}

	@Override
	public void update(AmGroup amGroup) {
		if(amGroup.getGroupId()==null) throw new RuntimeException(">>>>groupId is null");
		AmGroup dbGroup=amGroupDao.getById(amGroup.getGroupId());
		if(dbGroup==null)throw new RuntimeException(">>>>["+amGroup.getGroupId()+"] update node non-existent");
		dbGroup.setGroupName(amGroup.getGroupName());
		dbGroup.setGroupInfo(amGroup.getGroupInfo());
		dbGroup.setGroupCode(amGroup.getGroupCode());
		dbGroup.setIsInherit(amGroup.getIsInherit());
		dbGroup.setEditTime(new Date());
		int result=amGroupDao.update(amGroup);
		LOG.debug(">>>>Update Result Value:"+result);
	}

	@Override
	public void deleteLogic(String groupId) {
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return;
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[4]}";
		
		String sql="update am_group set DATA_STATE=#{par[0]},DELETE_TIME=#{par[1]} "
				+ "where LEFT_VALUE>=#{par[2]} and RIGHT_VALUE<=#{par[3]} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par", ConstData.DATA_DELETE,new Date(),dbGroup.getLeftValue(),dbGroup.getRightValue(),dbGroup.getProductId());
	}

	@Override
	public void deletePhysical(String groupId) {
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return;
		Long offset=dbGroup.getRightValue()-dbGroup.getLeftValue()+1;
		LOG.debug(">>>>delete offset:"+offset);
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[2]}";
		
		String sql="delete from am_group where LEFT_VALUE>=#{par[0]} and RIGHT_VALUE<=#{par[1]} and "+productIdWhere;
		int result=amGroupDao.deleteBySql(sql, "par", dbGroup.getLeftValue(),dbGroup.getRightValue(),dbGroup.getProductId());
		LOG.debug(">>>>delete node count:"+result);
		sql="update am_group set LEFT_VALUE=LEFT_VALUE-#{par[0]} where LEFT_VALUE>#{par[1]} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par",offset,dbGroup.getRightValue(),dbGroup.getProductId());
		sql="update am_group set RIGHT_VALUE=RIGHT_VALUE-#{par[0]} where RIGHT_VALUE>#{par[1]} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par",offset,dbGroup.getRightValue(),dbGroup.getProductId());
		
	}

	@Override
	public void changePoint(String sourceNodeId, String parentId,
			String targetNodeId) {
		long beginTime=System.currentTimeMillis();
		AmGroup dbSourceGroup=amGroupDao.getById(sourceNodeId);
		if(dbSourceGroup==null) return;
		
		
		AmGroup dbParentGroup=null;
		AmGroup dbTargetGroup=null;
		if(parentId!=null&&!"".equals(parentId.trim())){
			dbParentGroup=amGroupDao.getById(parentId);
			if(dbParentGroup.getLeftValue()>=dbSourceGroup.getLeftValue()
					&&dbParentGroup.getRightValue()<=dbSourceGroup.getRightValue()){
				throw new RuntimeException(">>>>parentId Can't be a child node");
			}
		}else{
			parentId=null;
		}
		if(targetNodeId!=null&&!"".equals(targetNodeId.trim())){
			dbTargetGroup=amGroupDao.getById(targetNodeId);
			if(dbTargetGroup.getLeftValue()>=dbSourceGroup.getLeftValue()
					&&dbTargetGroup.getRightValue()<=dbSourceGroup.getRightValue()){
				throw new RuntimeException(">>>>targetNodeId Can't be a child node");
			}
		}else{
			targetNodeId=null;
		}
		
		if(dbTargetGroup!=null
				&&(dbTargetGroup.getParentGroupId()==null&&parentId!=null
				||dbTargetGroup.getParentGroupId()!=null&&!dbTargetGroup.getParentGroupId().equals(parentId))){
			throw new RuntimeException(">>>>targetNodeId parentId Don't match");
		}
		
		Long offset=dbSourceGroup.getRightValue()-dbSourceGroup.getLeftValue()+1;
		
		String productIdWhere=dbSourceGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbSourceGroup.getProductId());
		par.put("leftValue", dbSourceGroup.getLeftValue());
		par.put("rightValue", dbSourceGroup.getRightValue());
		par.put("currentTime", new Date());
		par.put("offset", offset);
		
		String sql=null;
		
		Long startLeftValue=1L;
		if(dbParentGroup==null){
			if(dbTargetGroup==null){
				sql=productIdWhere+" AND PARENT_GROUP_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
				
				List<AmGroup> groups=amGroupDao.getByWhere(sql, "par",par );
				if(groups!=null&&groups.size()>0){
					AmGroup maxGroup=groups.get(0);
					startLeftValue=maxGroup.getRightValue()+1;
				}
			}else{
				startLeftValue=dbTargetGroup.getLeftValue();
			}
		}else{
			if(dbTargetGroup==null){
				sql=productIdWhere+" AND PARENT_GROUP_ID=#{par.parentGroupId} ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
				par.put("parentGroupId", dbParentGroup.getGroupId());
				List<AmGroup> groups=amGroupDao.getByWhere(sql, "par",par );
				if(groups!=null&&groups.size()>0){
					AmGroup maxGroup=groups.get(0);
					startLeftValue=maxGroup.getRightValue()+1;
				}else{
					startLeftValue=dbParentGroup.getLeftValue()+1;
				}
			}else{
				startLeftValue=dbTargetGroup.getLeftValue();
			}
		}
		
		par.put("startLeftValue", startLeftValue);
		
		sql="update am_group set RIGHT_VALUE=RIGHT_VALUE+#{par.offset} "
				+ "where RIGHT_VALUE>=#{par.startLeftValue} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par", par);

		sql="update am_group set LEFT_VALUE=LEFT_VALUE+#{par.offset} "
				+ "where LEFT_VALUE>=#{par.startLeftValue} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par", par);
		
		dbSourceGroup=amGroupDao.getById(sourceNodeId);
		par.put("leftValue", dbSourceGroup.getLeftValue());
		par.put("rightValue", dbSourceGroup.getRightValue());
		
		Long sourceOffset=startLeftValue-dbSourceGroup.getLeftValue();
		
		par.put("sourceOffset", sourceOffset);
		
		sql="update am_group set RIGHT_VALUE=RIGHT_VALUE+#{par.sourceOffset},LEFT_VALUE=LEFT_VALUE+#{par.sourceOffset},EDIT_TIME=#{par.currentTime} "
				+ "where LEFT_VALUE>=#{par.leftValue} and RIGHT_VALUE<=#{par.rightValue} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par", par);
		
		sql="update am_group set LEFT_VALUE=LEFT_VALUE-#{par.offset} where LEFT_VALUE>#{par.rightValue} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par", par);

		sql="update am_group set RIGHT_VALUE=RIGHT_VALUE-#{par.offset} where RIGHT_VALUE>#{par.rightValue} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par", par);
		
		dbSourceGroup=amGroupDao.getById(sourceNodeId);
		
		dbSourceGroup.setParentGroupId(parentId);
		
		amGroupDao.update(dbSourceGroup);
		
		long endTime=System.currentTimeMillis();
		LOG.debug(">>>>run time:"+(endTime-beginTime));
	}

	@Override
	public void deleteLogicRestore(String groupId) {
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return;
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[4]}";
		
		String sql="update am_group set DATA_STATE=#{par[0]},DELETE_TIME=#{par[1]} "
				+ "where LEFT_VALUE>=#{par[2]} and RIGHT_VALUE<=#{par[3]} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par", ConstData.DATA_NORMAL,null,dbGroup.getLeftValue(),dbGroup.getRightValue(),dbGroup.getProductId());
		
	}

	@Override
	public List<AmGroup> getDirectChild(String groupId) {
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return null;
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		String sql=productIdWhere+" and PARENT_GROUP_ID=#{par.parentGroupId} and DATA_STATE=#{par.dataState} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbGroup.getProductId());
		par.put("parentGroupId", dbGroup.getGroupId());
		par.put("dataState", ConstData.DATA_NORMAL);
		
		List<AmGroup> result=amGroupDao.getByWhere(sql, "par", par);
		
		return result;
	}

	@Override
	public int getDirectChildCount(String groupId) {
		int result=0;
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return result;
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		String sql="SELECT COUNT(GROUP_ID) AS cou FROM am_group WHERE  DATA_STATE=#{par.dataState} AND PARENT_GROUP_ID=#{par.parentGroupId} AND "+productIdWhere;
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbGroup.getProductId());
		par.put("parentGroupId", dbGroup.getGroupId());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<Map<String, Object>> list=amGroupDao.selectBySql(sql, "par", par);
		if(list!=null&&list.size()>0){
			Map<String, Object> record=list.get(0);
			Object count=record.get("cou");
			if(count!=null){
				result=Integer.parseInt(count.toString());
			}
			
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

	@Override
	public List<AmGroup> getAllChild(String groupId) {
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return null;
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		String sql=productIdWhere+" and LEFT_VALUE>#{par.leftValue} and RIGHT_VALUE<#{par.rightValue} and DATA_STATE=#{par.dataState} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbGroup.getProductId());
		par.put("leftValue", dbGroup.getLeftValue());
		par.put("rightValue", dbGroup.getRightValue());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<AmGroup> result=amGroupDao.getByWhere(sql, "par", par);
		return result;
	}

	@Override
	public int getAllChildCount(String groupId) {
		int result=0;
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return result;
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		String sql="SELECT COUNT(GROUP_ID) AS cou FROM am_group WHERE  DATA_STATE=#{par.dataState} "
				+ " AND LEFT_VALUE>#{par.leftValue} AND RIGHT_VALUE<#{par.rightValue} AND "+productIdWhere;
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbGroup.getProductId());
		par.put("leftValue", dbGroup.getLeftValue());
		par.put("rightValue", dbGroup.getRightValue());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<Map<String, Object>> list=amGroupDao.selectBySql(sql, "par", par);
		if(list!=null&&list.size()>0){
			Map<String, Object> record=list.get(0);
			Object count=record.get("cou");
			if(count!=null){
				result=Integer.parseInt(count.toString());
			}
			
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

	@Override
	public List<AmGroup> getPath(String groupId) {
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return null;
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		String sql=productIdWhere+" and LEFT_VALUE<=#{par.leftValue} and RIGHT_VALUE>=#{par.rightValue} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbGroup.getProductId());
		par.put("leftValue", dbGroup.getLeftValue());
		par.put("rightValue", dbGroup.getRightValue());
		List<AmGroup> result=amGroupDao.getByWhere(sql, "par", par);
		return result;
	}

	@Override
	public int getLevel(String groupId) {
		int result=-1;
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return result;
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		String sql="SELECT COUNT(GROUP_ID) AS cou FROM am_group WHERE "
				+ "LEFT_VALUE<=#{par.leftValue} AND RIGHT_VALUE>=#{par.rightValue} AND "+productIdWhere;
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbGroup.getProductId());
		par.put("leftValue", dbGroup.getLeftValue());
		par.put("rightValue", dbGroup.getRightValue());
		List<Map<String, Object>> list=amGroupDao.selectBySql(sql, "par", par);
		if(list!=null&&list.size()>0){
			Map<String, Object> record=list.get(0);
			Object count=record.get("cou");
			if(count!=null){
				result=Integer.parseInt(count.toString());
			}
			
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

	@Override
	public AmGroup getById(String groupId) {
		
		return amGroupDao.getById(groupId);
	}

	@Override
	public List<AmGroup> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
		
		return amGroupDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public List<AmGroup> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		
		return amGroupDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public void getByWherePage(Pager<AmGroup> page, String whereSql,
			String paraKey, Object... parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(GROUP_ID) as dataCount FROM am_group "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=amGroupDao.selectBySql(countSql, paraKey, parameters);
		if(countResult!=null&&countResult.size()>0){
			Map<String, Object> result=countResult.get(0);
			Object dataCount=result.get("dataCount");
			if(dataCount!=null){
				totalCount=Long.parseLong(dataCount.toString());
			}
		}
		LOG.debug(">>>>totalCount:"+totalCount);
		page.setTotalCount(totalCount);
		page.update();
		if(totalCount>0){
			List<AmGroup> result=amGroupDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);
	}

	@Override
	public void getByWherePage(Pager<AmGroup> page, String whereSql,
			String paraKey, Map<String, Object> parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(GROUP_ID) as dataCount FROM am_group "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=amGroupDao.selectBySql(countSql, paraKey, parameters);
		if(countResult!=null&&countResult.size()>0){
			Map<String, Object> result=countResult.get(0);
			Object dataCount=result.get("dataCount");
			if(dataCount!=null){
				totalCount=Long.parseLong(dataCount.toString());
			}
		}
		LOG.debug(">>>>totalCount:"+totalCount);
		page.setTotalCount(totalCount);
		page.update();
		if(totalCount>0){
			List<AmGroup> result=amGroupDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);
		
	}
	
	

	

}
