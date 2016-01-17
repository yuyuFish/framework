package org.framework.authorize.auth.dao.impl;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SysResources sysResources) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String resourcesId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysResources getById(String resourcesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysResources> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysResources> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysResources> getByWherePage(String whereSql, String paraKey,
			Map<String, Object> parameters, Long startIndex, Long size) {
		// TODO Auto-generated method stub
		return null;
	}

}
