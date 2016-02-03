package org.framework.authorize.auth.services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.AmRoleDao;
import org.framework.authorize.auth.model.AmRole;
import org.framework.authorize.auth.services.AmRoleService;
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(AmRole amRole) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changePoint(String sourceNodeId, String parentId,
			String targetNodeId) {
		// TODO Auto-generated method stub
		
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
