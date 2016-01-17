package org.framework.authorize.base.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.framework.authorize.base.dao.BaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("baseDao")
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {
	
	protected static final Log LOG=LogFactory.getLog(BaseDaoImpl.class);

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public List selectBySql(String sql) {
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("sql", sql);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List result=getSqlSession().selectList("org.framework.authorize.base.mapper.BaseMapper.selectBySql",parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public List selectBySql(String sql,String paraName, Object... parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List result=getSqlSession().selectList("org.framework.authorize.base.mapper.BaseMapper.selectBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public List selectBySql(String sql,String paraName, Map<String, Object> parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+parameters);
		}
		List result=getSqlSession().selectList("org.framework.authorize.base.mapper.BaseMapper.selectBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int insertBySql(String sql) {
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().insert("org.framework.authorize.base.mapper.BaseMapper.insertBySql", paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int insertBySql(String sql,String paraName, Object... parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().insert("org.framework.authorize.base.mapper.BaseMapper.insertBySql", paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int insertBySql(String sql,String paraName, Map<String, Object> parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().insert("org.framework.authorize.base.mapper.BaseMapper.insertBySql", paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int deleteBySql(String sql) {
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().delete("org.framework.authorize.base.mapper.BaseMapper.deleteBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int deleteBySql(String sql,String paraName, Object... parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().delete("org.framework.authorize.base.mapper.BaseMapper.deleteBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int deleteBySql(String sql,String paraName, Map<String, Object> parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().delete("org.framework.authorize.base.mapper.BaseMapper.deleteBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int updateBySql(String sql) {
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().update("org.framework.authorize.base.mapper.BaseMapper.updateBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int updateBySql(String sql,String paraName, Object... parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().update("org.framework.authorize.base.mapper.BaseMapper.updateBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	@Override
	public int updateBySql(String sql,String paraName, Map<String, Object> parameters) {
		if(paraName==null||"".equals(paraName.trim())) paraName="parameters";
		Map<String, Object> paraWarp=new HashMap<String, Object>();
		paraWarp.put("sql", sql);
		paraWarp.put(paraName, parameters);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>parameters warp map:"+paraWarp);
		}
		int result=getSqlSession().update("org.framework.authorize.base.mapper.BaseMapper.updateBySql",paraWarp);
		if(LOG.isDebugEnabled()){
			LOG.debug(">>>>result:"+result);
		}
		return result;
	}

	
	/*public Object executeSql(String sql) throws SQLException{
		Object result=null;
		PreparedStatement statement=getSqlSession().getConnection().prepareStatement(sql);
		
		return result;
	}*/
	
	

}
