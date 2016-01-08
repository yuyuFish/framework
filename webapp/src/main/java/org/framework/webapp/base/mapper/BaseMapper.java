package org.framework.webapp.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper {
	
	List selectBySql(@Param("sql")String sql);
	
	List selectBySql(@Param("sql")String sql,@Param("parameters")Object... parameters);
	
	List selectBySql(@Param("sql")String sql,@Param("parameters")Map<String, Object> parameters);
	
	int insertBySql(@Param("sql")String sql);
	
	int insertBySql(@Param("sql")String sql,@Param("parameters")Object... parameters);
	
	int insertBySql(@Param("sql")String sql,@Param("parameters")Map<String, Object> parameters);
	
	int deleteBySql(@Param("sql")String sql);
	
	int deleteBySql(@Param("sql")String sql,@Param("parameters")Object... parameters);
	
	int deleteBySql(@Param("sql")String sql,@Param("parameters")Map<String, Object> parameters);
	
	int updateBySql(@Param("sql")String sql);
	
	int updateBySql(@Param("sql")String sql,@Param("parameters")Object... parameters);
	
	int updateBySql(@Param("sql")String sql,@Param("parameters")Map<String, Object> parameters);
}
