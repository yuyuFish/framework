package org.framework.authorize.auth.dao;

import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.model.SysResources;
import org.framework.authorize.base.dao.BaseDao;
/**
 * 资源
 * @author ztgoto
 * @version  
 * @date  2016-1-17 21:30:58
 */
public interface SysResourcesDao extends BaseDao {
	/**
	 * 新建一条数据
	 * @param sysResources
	 * @return
	 */
	int save(SysResources sysResources);
	
	/**
	 * 修改一条数据
	 * @param sysResources
	 * @return
	 */
	int update(SysResources sysResources);
	
	/**
	 * 根据id删除一条数据
	 * @param resourcesId
	 * @return
	 */
	int deleteById(String resourcesId);
	
	/**
	 * 根据id查询一条数据
	 * @param resourcesId
	 * @return
	 */
	SysResources getById(String resourcesId);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<SysResources> getAll();
	
	
	/**
	 * 条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters where条件中的对应参数
	 * @return
	 */
	List<SysResources> getByWhere(String whereSql,String paraKey,Map<String, Object> parameters);
	
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
	List<SysResources> getByWherePage(String whereSql,String paraKey,Map<String, Object> parameters,Long startIndex,Long size);
}
