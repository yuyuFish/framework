package org.framework.authorize.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.dao.SysResourcesDao;
import org.framework.authorize.auth.model.SysResources;
import org.framework.authorize.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
@Repository("sysResourcesDao")
public class SysResourcesDaoImpl extends BaseDaoImpl implements SysResourcesDao {

	@Override
	public int save(SysResources sysResources) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+sysResources);
		}
		int result=getSqlSession().insert("org.framework.authorize.auth.mapper.SysResourcesMapper.save", sysResources);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int update(SysResources sysResources) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+sysResources);
		}
		int result=getSqlSession().update("org.framework.authorize.auth.mapper.SysResourcesMapper.update", sysResources);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int deleteById(String resourcesId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+resourcesId);
		}
		int result=getSqlSession().delete("org.framework.authorize.auth.mapper.SysResourcesMapper.deleteById", resourcesId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public SysResources getById(String resourcesId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+resourcesId);
		}
		SysResources result=getSqlSession().selectOne("org.framework.authorize.auth.mapper.SysResourcesMapper.getById", resourcesId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public List<SysResources> getAll() {
		List<SysResources> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysResourcesMapper.getAll");
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<SysResources> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<SysResources> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysResourcesMapper.getByWhere",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<SysResources> getByWherePage(String whereSql, Long startIndex, Long size
			, String paraKey,Map<String, Object> parameters) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		paraWarp.put("startIndex", startIndex);
		paraWarp.put("size", size);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<SysResources> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysResourcesMapper.getByWherePage",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<SysResources> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<SysResources> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysResourcesMapper.getByWhere",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<SysResources> getByWherePage(String whereSql, Long startIndex,
			Long size, String paraKey, Object... parameters) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		paraWarp.put("startIndex", startIndex);
		paraWarp.put("size", size);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<SysResources> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysResourcesMapper.getByWherePage",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

}
