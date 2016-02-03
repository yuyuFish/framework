package org.framework.authorize.auth.services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.AmPermissionDao;
import org.framework.authorize.auth.model.AmPermission;
import org.framework.authorize.auth.services.AmPermissionService;
import org.framework.authorize.base.model.ConstData;
import org.framework.authorize.base.utils.Pager;
import org.springframework.stereotype.Service;
@Service("amPermissionService")
public class AmPermissionServiceImpl implements AmPermissionService {
	
	protected static final Log LOG=LogFactory.getLog(AmPermissionServiceImpl.class);
	
	@Resource(name="amPermissionDao")
	private AmPermissionDao amPermissionDao;
	
	@Override
	public AmPermission getById(String permissionId) {
		
		return amPermissionDao.getById(permissionId);
	}

	@Override
	public List<AmPermission> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
		
		return amPermissionDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public List<AmPermission> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		
		return amPermissionDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public void getByWherePage(Pager<AmPermission> page, String whereSql,
			String paraKey, Object... parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(PERMISSION_ID) as dataCount FROM am_permission "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=amPermissionDao.selectBySql(countSql, paraKey, parameters);
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
			List<AmPermission> result=amPermissionDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);

	}

	@Override
	public void getByWherePage(Pager<AmPermission> page, String whereSql,
			String paraKey, Map<String, Object> parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(PERMISSION_ID) as dataCount FROM am_permission "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=amPermissionDao.selectBySql(countSql, paraKey, parameters);
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
			List<AmPermission> result=amPermissionDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);

	}

	@Override
	public void add(AmPermission amPermission) {
		
		amPermissionDao.save(amPermission);

	}

	@Override
	public void update(AmPermission amPermission) {

		amPermissionDao.update(amPermission);

	}

	@Override
	public void deleteLogic(String permissionId) {
		String sql="update am_group set DATA_STATE=#{par[0]} where PERMISSION_ID=#{par[1]}";
		amPermissionDao.updateBySql(sql, "par", ConstData.DATA_DELETE,permissionId);

	}

	@Override
	public void deleteLogicRestore(String permissionId) {
		String sql="update am_group set DATA_STATE=#{par[0]} where PERMISSION_ID=#{par[1]}";
		amPermissionDao.updateBySql(sql, "par", ConstData.DATA_NORMAL,permissionId);

	}

	@Override
	public void deletePhysical(String permissionId) {
		amPermissionDao.deleteById(permissionId);

	}

}
