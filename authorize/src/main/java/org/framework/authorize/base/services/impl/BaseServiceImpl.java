package org.framework.authorize.base.services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.framework.authorize.base.dao.BaseDao;
import org.springframework.stereotype.Service;

/**
 * 基础Service实现
 * @author ztgoto
 * @version  
 * @date  2016年1月25日 上午9:55:08
 */
@Service("baseService")
public class BaseServiceImpl implements BaseDao {
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public List selectBySql(String sql) {
		
		return baseDao.selectBySql(sql);
	}

	@Override
	public List selectBySql(String sql, String paraName, Object... parameters) {
		
		return baseDao.selectBySql(sql, paraName, parameters);
	}

	@Override
	public List selectBySql(String sql, String paraName,
			Map<String, Object> parameters) {
		
		return baseDao.selectBySql(sql, paraName, parameters);
	}

	@Override
	public int insertBySql(String sql) {
		
		return insertBySql(sql);
	}

	@Override
	public int insertBySql(String sql, String paraName, Object... parameters) {
		
		return insertBySql(sql, paraName, parameters);
	}

	@Override
	public int insertBySql(String sql, String paraName,
			Map<String, Object> parameters) {
		
		return insertBySql(sql, paraName, parameters);
	}

	@Override
	public int deleteBySql(String sql) {
		
		return deleteBySql(sql);
	}

	@Override
	public int deleteBySql(String sql, String paraName, Object... parameters) {
		
		return deleteBySql(sql, paraName, parameters);
	}

	@Override
	public int deleteBySql(String sql, String paraName,
			Map<String, Object> parameters) {
		
		return deleteBySql(sql, paraName, parameters);
	}

	@Override
	public int updateBySql(String sql) {
		
		return updateBySql(sql);
	}

	@Override
	public int updateBySql(String sql, String paraName, Object... parameters) {
		
		return updateBySql(sql, paraName, parameters);
	}

	@Override
	public int updateBySql(String sql, String paraName,
			Map<String, Object> parameters) {
		
		return updateBySql(sql, paraName, parameters);
	}
	
}
