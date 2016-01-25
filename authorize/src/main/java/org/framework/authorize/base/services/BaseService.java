package org.framework.authorize.base.services;

import java.util.List;
import java.util.Map;

/**
 * 基础Service
 * @author ztgoto
 * @version  
 * @date  2016年1月25日 上午9:50:38
 */
public interface BaseService {
	/**
	 * 执行查询sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	List selectBySql(String sql);

	/**
	 * 执行查询sql
	 * sql 参数使用方式必须为#{paraName[*]} 的形式
	 * paraName为指定的参数引用名称，默认为parameters，*为parameters中参数索引位置
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	List selectBySql(String sql,String paraName,Object... parameters);

	/**
	 * 执行查询sql
	 * sql 参数使用方式必须为#{paraName.*} 的形式
	 * araName为指定的参数引用名称，默认为parameters，*传入参数的key
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	List selectBySql(String sql,String paraName,Map<String, Object> parameters);

	/**
	 * 执行增加sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	int insertBySql(String sql);

	/**
	 * 执行插入sql
	 * sql 参数使用方式必须为#{paraName[*]} 的形式
	 * paraName为指定的参数引用名称，默认为parameters，*为parameters中参数索引位置
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	int insertBySql(String sql,String paraName,Object... parameters);

	/**
	 * 执行插入sql
	 * sql 参数使用方式必须为#{paraName.*} 的形式
	 * araName为指定的参数引用名称，默认为parameters，*传入参数的key
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	int insertBySql(String sql,String paraName,Map<String, Object> parameters);

	/**
	 * 执行删除sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	int deleteBySql(String sql);

	/**
	 * 执行删除sql
	 * sql 参数使用方式必须为#{paraName[*]} 的形式
	 * paraName为指定的参数引用名称，默认为parameters，*为parameters中参数索引位置
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	int deleteBySql(String sql,String paraName,Object... parameters);
	
	/**
	 * 执行删除sql
	 * sql 参数使用方式必须为#{paraName.*} 的形式
	 * araName为指定的参数引用名称，默认为parameters，*传入参数的key
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	int deleteBySql(String sql,String paraName,Map<String, Object> parameters);

	/**
	 * 执行修改sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	int updateBySql(String sql);

	/**
	 * 执行修改sql
	 * sql 参数使用方式必须为#{paraName[*]} 的形式
	 * paraName为指定的参数引用名称，默认为parameters，*为parameters中参数索引位置
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	int updateBySql(String sql,String paraName,Object... parameters);
	
	/**
	 * 执行修改sql
	 * sql 参数使用方式必须为#{paraName.*} 的形式
	 * araName为指定的参数引用名称，默认为parameters，*传入参数的key
	 * @param sql
	 * @param paraName
	 * @param parameters
	 * @return
	 */
	int updateBySql(String sql,String paraName,Map<String, Object> parameters);
}
