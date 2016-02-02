package org.framework.authorize.auth.dao;

import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.model.AmPermission;
import org.framework.authorize.base.dao.BaseDao;
/**
 * 操作权限
 * @author ztgoto
 * @version  
 * @date  2016年1月14日 下午4:30:55
 */
public interface AmPermissionDao extends BaseDao {
	/**
	 * 新建一条数据
	 * @param amPermission
	 * @return
	 */
	int save(AmPermission amPermission);
	
	/**
	 * 修改一条数据
	 * @param amPermission
	 * @return
	 */
	int update(AmPermission amPermission);
	
	/**
	 * 根据id删除一条数据
	 * @param permissionId
	 * @return
	 */
	int deleteById(String permissionId);
	
	/**
	 * 根据id查询一条数据
	 * @param groupId
	 * @return
	 */
	AmPermission getById(String permissionId);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<AmPermission> getAll();
	
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
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters where条件中的对应参数
	 * @param startIndex 开始索引
	 * @param size 数据条数
	 * @return
	 */
	List<AmPermission> getByWherePage(String whereSql,Long startIndex,Long size,String paraKey,Map<String, Object> parameters);
	
	/**
	 * 分页条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.[index]} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “index” 为parameters 索引位置
	 * @param parameters where条件中的对应参数
	 * @param startIndex 开始索引
	 * @param size 数据条数
	 * @return
	 */
	List<AmPermission> getByWherePage(String whereSql,Long startIndex,Long size,String paraKey,Object... parameters);
}
