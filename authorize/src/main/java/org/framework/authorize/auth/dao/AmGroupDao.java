package org.framework.authorize.auth.dao;

import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.base.dao.BaseDao;

/**
 * 用户组
 * @author ztgoto
 * @version  
 * @date  2016年1月12日 下午5:26:00
 */
public interface AmGroupDao extends BaseDao {
	/**
	 * 新建一条数据
	 * @param amGroup
	 * @return
	 */
	int save(AmGroup amGroup);
	
	/**
	 * 修改一条数据
	 * @param amGroup
	 * @return
	 */
	int update(AmGroup amGroup);
	
	/**
	 * 根据id删除一条数据
	 * @param groupId
	 * @return
	 */
	int deleteById(String groupId);
	
	/**
	 * 根据id查询一条数据
	 * @param groupId
	 * @return
	 */
	AmGroup getById(String groupId);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<AmGroup> getAll();
	
	
	/**
	 * 条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters where条件中的对应参数
	 * @return
	 */
	List<AmGroup> getByWhere(String whereSql,String paraKey,Map<String, Object> parameters);
	
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
	List<AmGroup> getByWherePage(String whereSql,String paraKey,Map<String, Object> parameters,Long startIndex,Long size);
}
