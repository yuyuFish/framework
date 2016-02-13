package org.framework.authorize.auth.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.SysResourcesDao;
import org.framework.authorize.auth.model.SysResources;
import org.framework.authorize.auth.services.SysResourcesService;
import org.framework.authorize.base.model.ConstData;
import org.framework.authorize.base.utils.Pager;
import org.springframework.stereotype.Service;
@Service("sysResourcesService")
public class SysResourcesServiceImpl implements SysResourcesService {
	protected static final Log LOG=LogFactory.getLog(SysResourcesServiceImpl.class);
	@Resource(name="sysResourcesDao")
	private SysResourcesDao sysResourcesDao;
	
	@Override
	public SysResources getById(String resourcesId) {
		
		return sysResourcesDao.getById(resourcesId);
	}

	@Override
	public List<SysResources> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
		
		return sysResourcesDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public List<SysResources> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		
		return sysResourcesDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public void getByWherePage(Pager<SysResources> page, String whereSql,
			String paraKey, Object... parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(RESOURCES_ID) as dataCount FROM sys_resources "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=sysResourcesDao.selectBySql(countSql, paraKey, parameters);
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
			List<SysResources> result=sysResourcesDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);

	}

	@Override
	public void getByWherePage(Pager<SysResources> page, String whereSql,
			String paraKey, Map<String, Object> parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(RESOURCES_ID) as dataCount FROM sys_resources "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=sysResourcesDao.selectBySql(countSql, paraKey, parameters);
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
			List<SysResources> result=sysResourcesDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);

	}

	@Override
	public void add(SysResources sysResources) {
		Long leftValue=1L;
		Long rightValue=2L;
		
		if(sysResources.getParentId()==null){
			LOG.debug(">>>>create root node>>>>");
			String productIdWhere=sysResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
			String sql=productIdWhere+" AND PARENT_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
			Map<String, Object> parameters=new HashMap<String, Object>();
			parameters.put("productId", sysResources.getProductId());
			List<SysResources> resources=sysResourcesDao.getByWhere(sql, "par",parameters );
			if(resources!=null&&resources.size()>0){
				SysResources maxResources=resources.get(0);
				leftValue=maxResources.getRightValue()+1;
				rightValue=maxResources.getRightValue()+2;
			}
		}else{
			LOG.debug(">>>>create subnode>>>>");
			SysResources parentResources=sysResourcesDao.getById(sysResources.getParentId());
			if(parentResources==null) throw new RuntimeException(">>>>["+sysResources.getParentId()+"] node non-existent");
			leftValue=parentResources.getRightValue();
			rightValue=leftValue+1;
			
			String productIdWhere=sysResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[1]}";
			
			String sql="update sys_resources set RIGHT_VALUE=RIGHT_VALUE+2 where RIGHT_VALUE>=#{par[0]} and "+productIdWhere;
			sysResourcesDao.updateBySql(sql, "par", leftValue,sysResources.getProductId());
			sql="update sys_resources set LEFT_VALUE=LEFT_VALUE+2 where LEFT_VALUE>=#{par[0]} and "+productIdWhere;
			sysResourcesDao.updateBySql(sql, "par", leftValue,sysResources.getProductId());
		}
		LOG.debug(">>>>LEFT_VALUE:"+leftValue+",RIGHT_VALUE:"+rightValue);
		sysResources.setLeftValue(leftValue);
		sysResources.setRightValue(rightValue);
		Date currentDate=new Date();
		sysResources.setCreateTime(currentDate);
		sysResources.setEditTime(currentDate);
		sysResources.setDataState(ConstData.DATA_NORMAL);
		int result=sysResourcesDao.save(sysResources);
		LOG.debug(">>>>Add Result Value:"+result);

	}

	@Override
	public void update(SysResources sysResources) {
		if(sysResources.getResourcesId()==null) throw new RuntimeException(">>>>resourcesId is null");
		SysResources dbResources=sysResourcesDao.getById(sysResources.getResourcesId());
		if(dbResources==null)throw new RuntimeException(">>>>["+sysResources.getResourcesId()+"] update node non-existent");
		dbResources.setResourcesName(sysResources.getResourcesName());
		dbResources.setResourcesInfo(sysResources.getResourcesInfo());
		dbResources.setResourcesCode(sysResources.getResourcesCode());
		dbResources.setResourcesType(sysResources.getResourcesType());
		dbResources.setRequestUrl(sysResources.getRequestUrl());
		dbResources.setAreaId(sysResources.getAreaId());
		dbResources.setSort(sysResources.getSort());
		dbResources.setEditTime(new Date());
		int result=sysResourcesDao.update(dbResources);
		LOG.debug(">>>>Update Result Value:"+result);

	}

	@Override
	public void changePoint(String sourceNodeId, String parentId,
			String targetNodeId) {
		long beginTime=System.currentTimeMillis();
		SysResources dbSourceResources=sysResourcesDao.getById(sourceNodeId);
		if(dbSourceResources==null) return;
		
		
		SysResources dbParentResources=null;
		SysResources dbTargetResources=null;
		if(parentId!=null&&!"".equals(parentId.trim())){
			dbParentResources=sysResourcesDao.getById(parentId);
			if(dbParentResources==null) throw new RuntimeException(">>>>["+parentId+"] Parent node non-existent");
			if(dbParentResources.getLeftValue()>=dbSourceResources.getLeftValue()
					&&dbParentResources.getRightValue()<=dbSourceResources.getRightValue()){
				throw new RuntimeException(">>>>parentId Can't be a child node");
			}
		}else{
			parentId=null;
		}
		if(targetNodeId!=null&&!"".equals(targetNodeId.trim())){
			dbTargetResources=sysResourcesDao.getById(targetNodeId);
			if(dbTargetResources==null) throw new RuntimeException(">>>>["+targetNodeId+"] Target node non-existent");
			if(dbTargetResources.getLeftValue()>=dbSourceResources.getLeftValue()
					&&dbTargetResources.getRightValue()<=dbSourceResources.getRightValue()){
				throw new RuntimeException(">>>>targetNodeId Can't be a child node");
			}
		}else{
			targetNodeId=null;
		}
		
		if(dbTargetResources!=null
				&&(dbTargetResources.getParentId()==null&&parentId!=null
				||dbTargetResources.getParentId()!=null&&!dbTargetResources.getParentId().equals(parentId))){
			throw new RuntimeException(">>>>targetNodeId parentId Don't match");
		}
		
		Long offset=dbSourceResources.getRightValue()-dbSourceResources.getLeftValue()+1;
		
		String productIdWhere=dbSourceResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbSourceResources.getProductId());
		par.put("leftValue", dbSourceResources.getLeftValue());
		par.put("rightValue", dbSourceResources.getRightValue());
		par.put("currentTime", new Date());
		par.put("offset", offset);
		
		String sql=null;
		
		Long startLeftValue=1L;
		if(dbParentResources==null){
			if(dbTargetResources==null){
				sql=productIdWhere+" AND PARENT_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
				
				List<SysResources> resources=sysResourcesDao.getByWhere(sql, "par",par );
				if(resources!=null&&resources.size()>0){
					SysResources maxResources=resources.get(0);
					startLeftValue=maxResources.getRightValue()+1;
				}
			}else{
				startLeftValue=dbTargetResources.getLeftValue();
			}
		}else{
			if(dbTargetResources==null){
				startLeftValue=dbParentResources.getRightValue();
			}else{
				startLeftValue=dbTargetResources.getLeftValue();
			}
		}
		
		par.put("startLeftValue", startLeftValue);
		
		sql="update sys_resources set RIGHT_VALUE=RIGHT_VALUE+#{par.offset} "
				+ "where RIGHT_VALUE>=#{par.startLeftValue} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par", par);

		sql="update sys_resources set LEFT_VALUE=LEFT_VALUE+#{par.offset} "
				+ "where LEFT_VALUE>=#{par.startLeftValue} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par", par);
		
		dbSourceResources=sysResourcesDao.getById(sourceNodeId);
		par.put("leftValue", dbSourceResources.getLeftValue());
		par.put("rightValue", dbSourceResources.getRightValue());
		
		Long sourceOffset=startLeftValue-dbSourceResources.getLeftValue();
		
		par.put("sourceOffset", sourceOffset);
		
		sql="update sys_resources set RIGHT_VALUE=RIGHT_VALUE+#{par.sourceOffset},LEFT_VALUE=LEFT_VALUE+#{par.sourceOffset},EDIT_TIME=#{par.currentTime} "
				+ "where LEFT_VALUE>=#{par.leftValue} and RIGHT_VALUE<=#{par.rightValue} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par", par);
		
		sql="update sys_resources set LEFT_VALUE=LEFT_VALUE-#{par.offset} where LEFT_VALUE>#{par.rightValue} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par", par);

		sql="update sys_resources set RIGHT_VALUE=RIGHT_VALUE-#{par.offset} where RIGHT_VALUE>#{par.rightValue} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par", par);
		
		dbSourceResources=sysResourcesDao.getById(sourceNodeId);
		
		dbSourceResources.setParentId(parentId);
		
		sysResourcesDao.update(dbSourceResources);
		
		long endTime=System.currentTimeMillis();
		LOG.debug(">>>>run time:"+(endTime-beginTime));

	}

	@Override
	public void deleteLogic(String resourcesId) {
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return;
		
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[4]}";
		
		String sql="update sys_resources set DATA_STATE=#{par[0]},DELETE_TIME=#{par[1]} "
				+ "where LEFT_VALUE>=#{par[2]} and RIGHT_VALUE<=#{par[3]} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par", ConstData.DATA_DELETE,new Date(),dbResources.getLeftValue(),dbResources.getRightValue(),dbResources.getProductId());

	}

	@Override
	public void deleteLogicRestore(String resourcesId) {
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return;
		
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[4]}";
		
		String sql="update sys_resources set DATA_STATE=#{par[0]},DELETE_TIME=#{par[1]} "
				+ "where LEFT_VALUE>=#{par[2]} and RIGHT_VALUE<=#{par[3]} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par", ConstData.DATA_NORMAL,null,dbResources.getLeftValue(),dbResources.getRightValue(),dbResources.getProductId());

	}

	@Override
	public void deletePhysical(String resourcesId) {
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return;
		Long offset=dbResources.getRightValue()-dbResources.getLeftValue()+1;
		LOG.debug(">>>>delete offset:"+offset);
		
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[2]}";
		
		String sql="delete from sys_resources where LEFT_VALUE>=#{par[0]} and RIGHT_VALUE<=#{par[1]} and "+productIdWhere;
		int result=sysResourcesDao.deleteBySql(sql, "par", dbResources.getLeftValue(),dbResources.getRightValue(),dbResources.getProductId());
		LOG.debug(">>>>delete node count:"+result);
		sql="update sys_resources set LEFT_VALUE=LEFT_VALUE-#{par[0]} where LEFT_VALUE>#{par[1]} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par",offset,dbResources.getRightValue(),dbResources.getProductId());
		sql="update sys_resources set RIGHT_VALUE=RIGHT_VALUE-#{par[0]} where RIGHT_VALUE>#{par[1]} and "+productIdWhere;
		sysResourcesDao.updateBySql(sql, "par",offset,dbResources.getRightValue(),dbResources.getProductId());

	}

	@Override
	public List<SysResources> getDirectChild(String resourcesId) {
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return null;
		
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		String sql=productIdWhere+" and PARENT_ID=#{par.parentId} and DATA_STATE=#{par.dataState} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbResources.getProductId());
		par.put("parentId", dbResources.getResourcesId());
		par.put("dataState", ConstData.DATA_NORMAL);
		
		List<SysResources> result=sysResourcesDao.getByWhere(sql, "par", par);
		
		return result;
	}

	@Override
	public int getDirectChildCount(String resourcesId) {
		int result=0;
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return result;
		
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		String sql="SELECT COUNT(RESOURCES_ID) AS cou FROM sys_resources WHERE  DATA_STATE=#{par.dataState} AND PARENT_ID=#{par.parentId} AND "+productIdWhere;
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbResources.getProductId());
		par.put("parentId", dbResources.getResourcesId());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<Map<String, Object>> list=sysResourcesDao.selectBySql(sql, "par", par);
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
	public List<SysResources> getAllChild(String resourcesId) {
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return null;
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		String sql=productIdWhere+" and LEFT_VALUE>#{par.leftValue} and RIGHT_VALUE<#{par.rightValue} and DATA_STATE=#{par.dataState} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbResources.getProductId());
		par.put("leftValue", dbResources.getLeftValue());
		par.put("rightValue", dbResources.getRightValue());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<SysResources> result=sysResourcesDao.getByWhere(sql, "par", par);
		return result;
	}

	@Override
	public int getAllChildCount(String resourcesId) {
		int result=0;
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return result;
		
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		String sql="SELECT COUNT(RESOURCES_ID) AS cou FROM sys_resources WHERE  DATA_STATE=#{par.dataState} "
				+ " AND LEFT_VALUE>#{par.leftValue} AND RIGHT_VALUE<#{par.rightValue} AND "+productIdWhere;
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbResources.getProductId());
		par.put("leftValue", dbResources.getLeftValue());
		par.put("rightValue", dbResources.getRightValue());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<Map<String, Object>> list=sysResourcesDao.selectBySql(sql, "par", par);
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
	public List<SysResources> getPath(String resourcesId) {
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return null;
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		String sql=productIdWhere+" and LEFT_VALUE<=#{par.leftValue} and RIGHT_VALUE>=#{par.rightValue} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbResources.getProductId());
		par.put("leftValue", dbResources.getLeftValue());
		par.put("rightValue", dbResources.getRightValue());
		List<SysResources> result=sysResourcesDao.getByWhere(sql, "par", par);
		return result;
	}

	@Override
	public int getLevel(String resourcesId) {
		int result=-1;
		SysResources dbResources=sysResourcesDao.getById(resourcesId);
		if(dbResources==null) return result;
		
		String productIdWhere=dbResources.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		String sql="SELECT COUNT(RESOURCES_ID) AS cou FROM sys_resources WHERE "
				+ "LEFT_VALUE<=#{par.leftValue} AND RIGHT_VALUE>=#{par.rightValue} AND "+productIdWhere;
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbResources.getProductId());
		par.put("leftValue", dbResources.getLeftValue());
		par.put("rightValue", dbResources.getRightValue());
		List<Map<String, Object>> list=sysResourcesDao.selectBySql(sql, "par", par);
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

}
