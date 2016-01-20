package org.framework.authorize.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.dao.AmRoleDao;
import org.framework.authorize.auth.model.AmRole;
import org.framework.authorize.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
@Repository("amRoleDao")
public class AmRoleDaoImpl extends BaseDaoImpl implements AmRoleDao {

	@Override
	public int save(AmRole amRole) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+amRole);
		}
		int result=getSqlSession().insert("org.framework.authorize.auth.mapper.AmRoleMapper.save", amRole);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int update(AmRole amRole) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+amRole);
		}
		int result=getSqlSession().update("org.framework.authorize.auth.mapper.AmRoleMapper.update", amRole);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int deleteById(String roleId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+roleId);
		}
		int result=getSqlSession().delete("org.framework.authorize.auth.mapper.AmRoleMapper.deleteById", roleId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public AmRole getById(String roleId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+roleId);
		}
		AmRole result=getSqlSession().selectOne("org.framework.authorize.auth.mapper.AmRoleMapper.getById", roleId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public List<AmRole> getAll() {
		List<AmRole> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.AmRoleMapper.getAll");
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<AmRole> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<AmRole> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.AmRoleMapper.getByWhere",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<AmRole> getByWherePage(String whereSql, String paraKey,
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
		List<AmRole> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.AmRoleMapper.getByWherePage",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

}
