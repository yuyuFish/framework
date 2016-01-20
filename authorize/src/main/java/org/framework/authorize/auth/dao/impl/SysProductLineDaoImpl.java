package org.framework.authorize.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.dao.SysProductLineDao;
import org.framework.authorize.auth.model.SysProductLine;
import org.framework.authorize.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
@Repository("sysProductLineDao")
public class SysProductLineDaoImpl extends BaseDaoImpl implements
		SysProductLineDao {

	@Override
	public int save(SysProductLine sysProductLine) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+sysProductLine);
		}
		int result=getSqlSession().insert("org.framework.authorize.auth.mapper.SysProductLineMapper.save", sysProductLine);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int update(SysProductLine sysProductLine) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+sysProductLine);
		}
		int result=getSqlSession().update("org.framework.authorize.auth.mapper.SysProductLineMapper.update", sysProductLine);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int deleteById(String productId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+productId);
		}
		int result=getSqlSession().delete("org.framework.authorize.auth.mapper.SysProductLineMapper.deleteById", productId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public SysProductLine getById(String productId) {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameter:"+productId);
		}
		SysProductLine result=getSqlSession().selectOne("org.framework.authorize.auth.mapper.SysProductLineMapper.getById", productId);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public List<SysProductLine> getAll() {
		List<SysProductLine> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysProductLineMapper.getAll");
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<SysProductLine> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		if(paraKey==null||"".equals(paraKey.trim())) paraKey="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("whereSql", whereSql);
		paraWarp.put(paraKey, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List<SysProductLine> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysProductLineMapper.getByWhere",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

	@Override
	public List<SysProductLine> getByWherePage(String whereSql, String paraKey,
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
		List<SysProductLine> result=getSqlSession().selectList("org.framework.authorize.auth.mapper.SysProductLineMapper.getByWherePage",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result size:"+(result==null?"null":result.size()));
		}
		
		return result;
	}

}
