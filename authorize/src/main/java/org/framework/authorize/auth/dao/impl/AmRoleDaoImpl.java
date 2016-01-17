package org.framework.authorize.auth.dao.impl;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AmRole amRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String roleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AmRole getById(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmRole> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmRole> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmRole> getByWherePage(String whereSql, String paraKey,
			Map<String, Object> parameters, Long startIndex, Long size) {
		// TODO Auto-generated method stub
		return null;
	}

}
