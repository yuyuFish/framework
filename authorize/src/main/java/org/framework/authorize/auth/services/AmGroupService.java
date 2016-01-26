package org.framework.authorize.auth.services;

import org.framework.authorize.auth.model.AmGroup;

/**
 * 用户组service
 * @author ztgoto
 * @version  
 * @date  2016年1月25日 上午10:19:44
 */
public interface AmGroupService {
	/**
	 * 添加一条group
	 * @param amGroup
	 */
	void addGroup(AmGroup amGroup);
	
	/**
	 * 更新一条group
	 * 该更新只能更新基础信息，不能更新节点位置，顺序及数据状态
	 * @param amGroup
	 */
	void updateGroup(AmGroup amGroup);
	
	/**
	 * 删除节点
	 * 逻辑删除，可恢复，删除同时会删除所有子节点
	 * @param groupId
	 */
	void deleteLoic(String groupId);
	
	/**
	 * 删除节点
	 * 物理删除，不可恢复，删除的同时会物理删除所有子节点(包括前面已经逻辑删除的子节点)
	 * @param groupId
	 */
	void deletePhysical(String groupId);
}
