package org.framework.authorize.auth.services;

import java.util.List;

import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.auth.model.AmPermission;

/**
 * 操作
 * @author ztgoto
 * @version  
 * @date  2016年2月1日 下午5:12:33
 */
public interface AmPermissionService {
	/**
	 * 添加一条信息
	 * @param amPermission
	 */
	void add(AmPermission amPermission);
	
	/**
	 * 更新一条信息
	 * @param amPermission
	 */
	void update(AmPermission amPermission);
	
	/**
	 * 删除节点
	 * 逻辑删除
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
