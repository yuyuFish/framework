package org.framework.authorize.auth.services;

import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.model.SysResources;
import org.framework.authorize.base.utils.Pager;

/**
 * 资源
 * @author ztgoto
 * @version  
 * @date  2016-2-12 23:57:36
 */
public interface SysResourcesService {
	/**
	 * 根据id查询一条数据
	 * @param resourcesId
	 * @return
	 */
	SysResources getById(String resourcesId);
	
	
	/**
	 * 条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.[index]} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “index” 为parameters 索引位置
	 * @param parameters where条件中的对应参数
	 * @return
	 */
	List<SysResources> getByWhere(String whereSql,String paraKey,Object... parameters);
	
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
	 * @param page 分页信息
	 * @param whereSql	where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.[index]} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “index” 为parameters 索引位置
	 * @param parameters where条件中的对应参数
	 */
	void getByWherePage(Pager<SysResources> page,String whereSql,String paraKey,Object... parameters);
	
	
	/**
	 * 分页条件查询
	 * @param page	分页信息
	 * @param whereSql where 条件
	  * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters	where条件中的对应参数
	 */
	void getByWherePage(Pager<SysResources> page,String whereSql,String paraKey,Map<String, Object> parameters);
	/**
	 * 添加一条信息
	 * @param sysResources
	 */
	void add(SysResources sysResources);
	
	/**
	 * 更新一条信息
	 * 该更新只能更新基础信息，不能更新节点位置，顺序及数据状态
	 * 可更新字段如下:
	 * resourcesName,resourcesInfo,resourcesCode,resourcesType
	 * ,requestUrl,areaId,sort
	 * @param sysResources
	 */
	void update(SysResources sysResources);
	
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
	 * @param resourcesId
	 */
	void deleteLogic(String resourcesId);
	/**
	 * 恢复逻辑删除的节点及其子节点
	 * @param resourcesId
	 */
	void deleteLogicRestore(String resourcesId);
	
	/**
	 * 删除节点
	 * 物理删除，不可恢复，删除的同时会物理删除所有子节点(包括前面已经逻辑删除的子节点)
	 * @param resourcesId
	 */
	void deletePhysical(String resourcesId);
	
	/**
	 * 获取指定节点的直接子节点
	 * @param resourcesId
	 * @return
	 */
	List<SysResources> getDirectChild(String resourcesId);
	
	/**
	 * 获取直接子节点数量
	 * @param resourcesId
	 * @return
	 */
	int getDirectChildCount(String resourcesId);
	
	/**
	 * 获取所有子孙节点
	 * @param resourcesId
	 * @return
	 */
	List<SysResources> getAllChild(String resourcesId);
	
	/**
	 * 获取所有子孙节点数量
	 * @param resourcesId
	 * @return
	 */
	int getAllChildCount(String resourcesId);
	
	/**
	 * 获取族谱路径
	 * @param resourcesId
	 * @return
	 */
	List<SysResources> getPath(String resourcesId);
	
	/**
	 * 获取节点所在层级(从1开始)
	 * @return
	 */
	int getLevel(String resourcesId);
}
