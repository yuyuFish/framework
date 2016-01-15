package org.framework.webapp.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.webapp.auth.dao.AmPermissionDao;
import org.framework.webapp.auth.model.AmPermission;
import org.framework.webapp.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
@Repository("amPermissionDao")
public class AmPermissionDaoImpl extends BaseDaoImpl implements AmPermissionDao {

	@Override
	public int save(AmPermission amPermission) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+amPermission);
		}
		int result=getSqlSession().insert("org.framework.webapp.auth.mapper.AmPermissionMapper.save", amPermission);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int update(AmPermission amPermission) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+amPermission);
		}
		int result=getSqlSession().update("org.framework.webapp.auth.mapper.AmPermissionMapper.update", amPermission);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int deleteById(String permissionId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+permissionId);
		}
		int result=getSqlSession().delete("org.framework.webapp.auth.mapper.AmPermissionMapper.deleteById", permissionId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public AmPermission getById(String permissionId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+permissionId);
		}
		AmPermission result=getSqlSession().selectOne("org.framework.webapp.auth.mapper.AmPermissionMapper.getById", permissionId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public List<AmPermission> getAll() {
		List<AmPermission> result=getSqlSession().selectList("org.framework.webapp.auth.mapper.AmPermissionMapper.getAll");
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<AmPermission> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<AmPermission> result=getSqlSession().selectList("org.framework.webapp.auth.mapper.AmPermissionMapper.getByWhere",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<AmPermission> getByWherePage(String whereSql, String paraKey,
			Map<String, Object> parameters, Long startIndex, Long size) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		paraWarp.put("startIndex", startIndex);
		paraWarp.put("size", size);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<AmPermission> result=getSqlSession().selectList("org.framework.webapp.auth.mapper.AmPermissionMapper.getByWherePage",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

}
