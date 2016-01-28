package org.framework.authorize.auth.services;

import java.util.List;

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
	 * 可更新字段如下:
	 * groupName,groupInfo,groupCode,isInherit
	 * @param amGroup
	 */
	void updateGroup(AmGroup amGroup);
	
	/**
	 * 更新节点位置
	 * @param sourceNodeId 需要改变的节点id
	 * * @param parentId 父节点id
	 * @param targetNodeId 目的节点id
	 */
	void changePoint(String sourceNodeId,String parentId,String targetNodeId);
	
	/**
	 * 删除节点
	 * 逻辑删除，可恢复，删除同时会删除所有子节点
	 * @param groupId
	 */
	void deleteLogic(String groupId);
	/**
	 * 恢复逻辑删除的节点及其子节点
	 * @param groupId
	 */
	void deleteLogicRestore(String groupId);
	
	/**
	 * 删除节点
	 * 物理删除，不可恢复，删除的同时会物理删除所有子节点(包括前面已经逻辑删除的子节点)
	 * @param groupId
	 */
	void deletePhysical(String groupId);
	
	/**
	 * 获取指定节点的直接子节点
	 * @param groupId
	 * @return
	 */
	List<AmGroup> getDirectChild(String groupId);
	
	/**
	 * 获取直接子节点数量
	 * @param groupId
	 * @return
	 */
	int getDirectChildCount(String groupId);
	
	/**
	 * 获取所有子孙节点
	 * @param groupId
	 * @return
	 */
	List<AmGroup> getAllChild(String groupId);
	
	/**
	 * 获取所有子孙节点数量
	 * @param groupId
	 * @return
	 */
	int getAllChildCount(String groupId);
	
	/**
	 * 获取族谱路径
	 * @param groupId
	 * @return
	 */
	List<AmGroup> getPath(String groupId);
	
	/**
	 * 获取节点所在层级(从1开始)
	 * @return
	 */
	int getLevel(String groupId);
	
}
