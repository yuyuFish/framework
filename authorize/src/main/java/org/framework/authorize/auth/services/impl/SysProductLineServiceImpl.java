package org.framework.authorize.auth.services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.SysProductLineDao;
import org.framework.authorize.auth.model.SysProductLine;
import org.framework.authorize.auth.services.SysProductLineService;
import org.framework.authorize.base.utils.Pager;
import org.springframework.stereotype.Service;
@Service("sysProductLineService")
public class SysProductLineServiceImpl implements SysProductLineService {

	protected static final Log LOG=LogFactory.getLog(SysProductLineServiceImpl.class);
	@Resource(name="sysProductLineDao")
	private SysProductLineDao sysProductLineDao;
	
	@Override
	public SysProductLine getById(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysProductLine> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
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
	public void getByWherePage(Pager<SysProductLine> page, String whereSql,
			String paraKey, Object... parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getByWherePage(Pager<SysProductLine> page, String whereSql,
			String paraKey, Map<String, Object> parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(SysProductLine sysProductLine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SysProductLine sysProductLine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePoint(String sourceNodeId, String parentId,
			String targetNodeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteLogic(String productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteLogicRestore(String productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePhysical(String productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SysProductLine> getDirectChild(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDirectChildCount(String productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysProductLine> getAllChild(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllChildCount(String productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysProductLine> getPath(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLevel(String productId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
