package org.framework.authorize.auth.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.AmRoleDao;
import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.auth.model.AmRole;
import org.framework.authorize.auth.services.AmRoleService;
import org.framework.authorize.base.model.ConstData;
import org.framework.authorize.base.utils.Pager;
import org.springframework.stereotype.Service;
@Service("amRoleService")
public class AmRoleServiceImpl implements AmRoleService {
	protected static final Log LOG=LogFactory.getLog(AmRoleServiceImpl.class);
	@Resource(name="amRoleDao")
	private AmRoleDao amRoleDao;
	@Override
	public AmRole getById(String roleId) {
		
		return amRoleDao.getById(roleId);
	}
	@Override
	public List<AmRole> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
		
		return amRoleDao.getByWhere(whereSql, paraKey, parameters);
	}
	@Override
	public List<AmRole> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		
		return amRoleDao.getByWhere(whereSql, paraKey, parameters);
	}
	@Override
	public void getByWherePage(Pager<AmRole> page, String whereSql,
			String paraKey, Object... parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(ROLE_ID) as dataCount FROM am_role "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=amRoleDao.selectBySql(countSql, paraKey, parameters);
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
			List<AmRole> result=amRoleDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);
		
	}
	@Override
	public void getByWherePage(Pager<AmRole> page, String whereSql,
			String paraKey, Map<String, Object> parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(ROLE_ID) as dataCount FROM am_role "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=amRoleDao.selectBySql(countSql, paraKey, parameters);
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
			List<AmRole> result=amRoleDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);
		
	}
	@Override
	public void add(AmRole amRole) {
		Long leftValue=1L;
		Long rightValue=2L;
		
		if(amRole.getParentId()==null){
			LOG.debug(">>>>create root node>>>>");
			String productIdWhere=amRole.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
			String sql=productIdWhere+" AND PARENT_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
			Map<String, Object> parameters=new HashMap<String, Object>();
			parameters.put("productId", amRole.getProductId());
			List<AmRole> roles=amRoleDao.getByWhere(sql, "par",parameters );
			if(roles!=null&&roles.size()>0){
				AmRole maxRole=roles.get(0);
				leftValue=maxRole.getRightValue()+1;
				rightValue=maxRole.getRightValue()+2;
			}
		}else{
			LOG.debug(">>>>create subnode>>>>");
			AmRole parentRole=amRoleDao.getById(amRole.getParentId());
			if(parentRole==null) throw new RuntimeException(">>>>["+amRole.getParentId()+"] node non-existent");
			leftValue=parentRole.getRightValue();
			rightValue=leftValue+1;
			
			String productIdWhere=amRole.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[1]}";
			
			String sql="update am_role set RIGHT_VALUE=RIGHT_VALUE+2 where RIGHT_VALUE>=#{par[0]} and "+productIdWhere;
			amRoleDao.updateBySql(sql, "par", leftValue,amRole.getProductId());
			sql="update am_role set LEFT_VALUE=LEFT_VALUE+2 where LEFT_VALUE>=#{par[0]} and "+productIdWhere;
			amRoleDao.updateBySql(sql, "par", leftValue,amRole.getProductId());
		}
		LOG.debug(">>>>LEFT_VALUE:"+leftValue+",RIGHT_VALUE:"+rightValue);
		amRole.setLeftValue(leftValue);
		amRole.setRightValue(rightValue);
		Date currentDate=new Date();
		amRole.setCreateTime(currentDate);
		amRole.setEditTime(currentDate);
		amRole.setDataState(ConstData.DATA_NORMAL);
		int result=amRoleDao.save(amRole);
		LOG.debug(">>>>Add Result Value:"+result);
		
	}
	@Override
	public void update(AmRole amRole) {
		if(amRole.getRoleId()==null) throw new RuntimeException(">>>>roleId is null");
		AmRole dbRole=amRoleDao.getById(amRole.getRoleId());
		if(dbRole==null)throw new RuntimeException(">>>>["+amRole.getRoleId()+"] update node non-existent");
		dbRole.setRoleName(amRole.getRoleName());
		dbRole.setRoleInfo(amRole.getRoleInfo());
		dbRole.setRoleCode(amRole.getRoleCode());
		dbRole.setIsInherit(amRole.getIsInherit());
		dbRole.setEditTime(new Date());
		int result=amRoleDao.update(dbRole);
		LOG.debug(">>>>Update Result Value:"+result);
		
	}
	@Override
	public void changePoint(String sourceNodeId, String parentId,
			String targetNodeId) {
		long beginTime=System.currentTimeMillis();
		AmRole dbSourceRole=amRoleDao.getById(sourceNodeId);
		if(dbSourceRole==null) return;
		
		
		AmRole dbParentRole=null;
		AmRole dbTargetRole=null;
		if(parentId!=null&&!"".equals(parentId.trim())){
			dbParentRole=amRoleDao.getById(parentId);
			if(dbParentRole==null) throw new RuntimeException(">>>>["+parentId+"] Parent node non-existent");
			if(dbParentRole.getLeftValue()>=dbSourceRole.getLeftValue()
					&&dbParentRole.getRightValue()<=dbSourceRole.getRightValue()){
				throw new RuntimeException(">>>>parentId Can't be a child node");
			}
		}else{
			parentId=null;
		}
		if(targetNodeId!=null&&!"".equals(targetNodeId.trim())){
			dbTargetRole=amRoleDao.getById(targetNodeId);
			if(dbTargetRole==null) throw new RuntimeException(">>>>["+targetNodeId+"] Target node non-existent");
			if(dbTargetRole.getLeftValue()>=dbSourceRole.getLeftValue()
					&&dbTargetRole.getRightValue()<=dbSourceRole.getRightValue()){
				throw new RuntimeException(">>>>targetNodeId Can't be a child node");
			}
		}else{
			targetNodeId=null;
		}
		
		if(dbTargetRole!=null
				&&(dbTargetRole.getParentId()==null&&parentId!=null
				||dbTargetRole.getParentId()!=null&&!dbTargetRole.getParentId().equals(parentId))){
			throw new RuntimeException(">>>>targetNodeId parentId Don't match");
		}
		
		Long offset=dbSourceRole.getRightValue()-dbSourceRole.getLeftValue()+1;
		
		String productIdWhere=dbSourceRole.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
		
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("productId", dbSourceRole.getProductId());
		par.put("leftValue", dbSourceRole.getLeftValue());
		par.put("rightValue", dbSourceRole.getRightValue());
		par.put("currentTime", new Date());
		par.put("offset", offset);
		
		String sql=null;
		
		Long startLeftValue=1L;
		if(dbParentRole==null){
			if(dbTargetRole==null){
				sql=productIdWhere+" AND PARENT_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
				
				List<AmRole> roles=amRoleDao.getByWhere(sql, "par",par );
				if(roles!=null&&roles.size()>0){
					AmRole maxRole=roles.get(0);
					startLeftValue=maxRole.getRightValue()+1;
				}
			}else{
				startLeftValue=dbTargetRole.getLeftValue();
			}
		}else{
			if(dbTargetRole==null){
				startLeftValue=dbParentRole.getRightValue();
			}else{
				startLeftValue=dbTargetRole.getLeftValue();
			}
		}
		
		par.put("startLeftValue", startLeftValue);
		
		sql="update am_role set RIGHT_VALUE=RIGHT_VALUE+#{par.offset} "
				+ "where RIGHT_VALUE>=#{par.startLeftValue} and "+productIdWhere;
		amRoleDao.updateBySql(sql, "par", par);

		sql="update am_role set LEFT_VALUE=LEFT_VALUE+#{par.offset} "
				+ "where LEFT_VALUE>=#{par.startLeftValue} and "+productIdWhere;
		amRoleDao.updateBySql(sql, "par", par);
		
		dbSourceRole=amRoleDao.getById(sourceNodeId);
		par.put("leftValue", dbSourceRole.getLeftValue());
		par.put("rightValue", dbSourceRole.getRightValue());
		
		Long sourceOffset=startLeftValue-dbSourceRole.getLeftValue();
		
		par.put("sourceOffset", sourceOffset);
		
		sql="update am_role set RIGHT_VALUE=RIGHT_VALUE+#{par.sourceOffset},LEFT_VALUE=LEFT_VALUE+#{par.sourceOffset},EDIT_TIME=#{par.currentTime} "
				+ "where LEFT_VALUE>=#{par.leftValue} and RIGHT_VALUE<=#{par.rightValue} and "+productIdWhere;
		amRoleDao.updateBySql(sql, "par", par);
		
		sql="update am_role set LEFT_VALUE=LEFT_VALUE-#{par.offset} where LEFT_VALUE>#{par.rightValue} and "+productIdWhere;
		amRoleDao.updateBySql(sql, "par", par);

		sql="update am_role set RIGHT_VALUE=RIGHT_VALUE-#{par.offset} where RIGHT_VALUE>#{par.rightValue} and "+productIdWhere;
		amRoleDao.updateBySql(sql, "par", par);
		
		dbSourceRole=amRoleDao.getById(sourceNodeId);
		
		dbSourceRole.setParentId(parentId);
		
		amRoleDao.update(dbSourceRole);
		
		long endTime=System.currentTimeMillis();
		LOG.debug(">>>>run time:"+(endTime-beginTime));
		
	}
	@Override
	public void deleteLogic(String roleId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteLogicRestore(String roleId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deletePhysical(String roleId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<AmRole> getDirectChild(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDirectChildCount(String roleId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<AmRole> getAllChild(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getAllChildCount(String roleId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<AmRole> getPath(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getLevel(String roleId) {
		// TODO Auto-generated method stub
		return 0;
	}
}
