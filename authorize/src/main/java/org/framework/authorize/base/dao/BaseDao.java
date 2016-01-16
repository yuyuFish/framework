package org.framework.authorize.base.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础dao
 * @author ztgoto
 * @version  
 * @date  2016年1月12日 下午12:15:03
 */
public interface BaseDao {
	/**
	 * 执行查询sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	List selectBySql(String sql);

	/**
	 * 执行查询sql
	 * sql 参数使用方式必须为#{parameters[*]} 的形式，*为参数索引位置
	 * @param sql
	 * @param parameters
	 * @return
	 */
	List selectBySql(String sql,Object... parameters);

	/**
	 * 执行查询sql
	 * sql 参数使用方式必须为#{parameters.*} 的形式，*传入参数的key
	 * @param sql
	 * @param parameters
	 * @return
	 */
	List selectBySql(String sql,Map<String, Object> parameters);

	/**
	 * 执行增加sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	int insertBySql(String sql);

	/**
	 * 执行增加sql
	 * sql 参数使用方式必须为#{parameters[*]} 的形式，*为参数索引位置
	 * @param sql
	 * @param parameters
	 * @return
	 */
	int insertBySql(String sql,Object... parameters);

	/**
	 * 执行增加sql
	 * sql 参数使用方式必须为#{parameters.*} 的形式，*传入参数的key
	 * @param sql
	 * @param parameters
	 * @return
	 */
	int insertBySql(String sql,Map<String, Object> parameters);

	/**
	 * 执行删除sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	int deleteBySql(String sql);

	/**
	 * 执行删除sql
	 * sql 参数使用方式必须为#{parameters[*]} 的形式，*为参数索引位置
	 * @param sql
	 * @param parameters
	 * @return
	 */
	int deleteBySql(String sql,Object... parameters);
	
	/**
	 * 执行删除sql
	 * sql 参数使用方式必须为#{parameters.*} 的形式，*传入参数的key
	 * @param sql
	 * @param parameters
	 * @return
	 */
	int deleteBySql(String sql,Map<String, Object> parameters);

	/**
	 * 执行修改sql（不推荐使用，sql注入风险）
	 * @param sql
	 * @return
	 */
	int updateBySql(String sql);

	/**
	 * 执行修改sql
	 * sql 参数使用方式必须为#{parameters[*]} 的形式，*为参数索引位置
	 * @param sql
	 * @param parameters
	 * @return
	 */
	int updateBySql(String sql,Object... parameters);
	
	/**
	 * 执行修改sql
	 * sql 参数使用方式必须为#{parameters.*} 的形式，*传入参数的key
	 * @param sql
	 * @param parameters
	 * @return
	 */
	int updateBySql(String sql,Map<String, Object> parameters);
}
