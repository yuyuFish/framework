package org.framework.authorize.auth.services;

import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.model.AmPermission;
import org.framework.authorize.base.utils.Pager;

/**
 * 操作
 * @author ztgoto
 * @version  
 * @date  2016年2月1日 下午5:12:33
 */
public interface AmPermissionService {
	/**
	 * 根据id查询一条数据
	 * @param permissionId
	 * @return
	 */
	AmPermission getById(String permissionId);
	
	
	/**
	 * 条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.[index]} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “index” 为parameters 索引位置
	 * @param parameters where条件中的对应参数
	 * @return
	 */
	List<AmPermission> getByWhere(String whereSql,String paraKey,Object... parameters);
	
	/**
	 * 条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters where条件中的对应参数
	 * @return
	 */
	List<AmPermission> getByWhere(String whereSql,String paraKey,Map<String, Object> parameters);
	
	
	/**
	 * 分页条件查询
	 * @param page 分页信息
	 * @param whereSql	where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.[index]} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “index” 为parameters 索引位置
	 * @param parameters where条件中的对应参数
	 */
	void getByWherePage(Pager<AmPermission> page,String whereSql,String paraKey,Object... parameters);
	
	
	/**
	 * 分页条件查询
	 * @param page	分页信息
	 * @param whereSql where 条件
	  * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters	where条件中的对应参数
	 */
	void getByWherePage(Pager<AmPermission> page,String whereSql,String paraKey,Map<String, Object> parameters);
	
	/**
	 * 添加一条信息
	 * @param amPermission
	 */
	void add(AmPermission amPermission);
	
	/**
	 * 更新一条信息
	 * @param amGroup
	 */
	void update(AmPermission amPermission);
	
	
	/**
	 * 删除节点
	 * 逻辑删除，可恢复
	 * @param permissionId
	 */
	void deleteLogic(String permissionId);
	/**
	 * 恢复逻辑删除的节点
	 * @param permissionId
	 */
	void deleteLogicRestore(String permissionId);
	
	/**
	 * 删除节点
	 * 物理删除，不可恢复
	 * @param permissionId
	 */
	void deletePhysical(String permissionId);
	
	
}
