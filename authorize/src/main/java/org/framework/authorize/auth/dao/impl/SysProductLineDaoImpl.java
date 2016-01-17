package org.framework.authorize.auth.dao.impl;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SysProductLine sysProductLine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysProductLine getById(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysProductLine> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysProductLine> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysProductLine> getByWherePage(String whereSql, String paraKey,
			Map<String, Object> parameters, Long startIndex, Long size) {
		// TODO Auto-generated method stub
		return null;
	}

}
