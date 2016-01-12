package org.framework.webapp.base.dao;

import java.util.List;

import org.framework.webapp.base.model.AmGroup;

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
	
}
