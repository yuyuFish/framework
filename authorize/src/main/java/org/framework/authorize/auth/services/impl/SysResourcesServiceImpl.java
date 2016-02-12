package org.framework.authorize.auth.services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.SysResourcesDao;
import org.framework.authorize.auth.model.SysResources;
import org.framework.authorize.auth.services.SysResourcesService;
import org.framework.authorize.base.utils.Pager;
import org.springframework.stereotype.Service;
@Service("sysResourcesService")
public class SysResourcesServiceImpl implements SysResourcesService {
	protected static final Log LOG=LogFactory.getLog(SysResourcesServiceImpl.class);
	@Resource(name="sysResourcesDao")
	private SysResourcesDao sysResourcesDao;
	
	@Override
	public SysResources getById(String resourcesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysResources> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
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
	public void getByWherePage(Pager<SysResources> page, String whereSql,
			String paraKey, Object... parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getByWherePage(Pager<SysResources> page, String whereSql,
			String paraKey, Map<String, Object> parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(SysResources sysResources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SysResources sysResources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePoint(String sourceNodeId, String parentId,
			String targetNodeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteLogic(String resourcesId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteLogicRestore(String resourcesId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePhysical(String resourcesId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SysResources> getDirectChild(String resourcesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDirectChildCount(String resourcesId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysResources> getAllChild(String resourcesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllChildCount(String resourcesId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysResources> getPath(String resourcesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLevel(String resourcesId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
