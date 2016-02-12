package org.framework.authorize.auth.services;

import java.util.List;
import java.util.Map;

import org.framework.authorize.auth.model.SysProductLine;
import org.framework.authorize.base.utils.Pager;

/**
 * 产品线
 * @author ztgoto
 * @version  
 * @date  2016-2-12 23:48:20
 */
public interface SysProductLineService {
	/**
	 * 根据id查询一条数据
	 * @param productId
	 * @return
	 */
	SysProductLine getById(String productId);
	
	
	/**
	 * 条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.[index]} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “index” 为parameters 索引位置
	 * @param parameters where条件中的对应参数
	 * @return
	 */
	List<SysProductLine> getByWhere(String whereSql,String paraKey,Object... parameters);
	
	/**
	 * 条件查询
	 * @param whereStr where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters where条件中的对应参数
	 * @return
	 */
	List<SysProductLine> getByWhere(String whereSql,String paraKey,Map<String, Object> parameters);
	
	
	/**
	 * 分页条件查询
	 * @param page 分页信息
	 * @param whereSql	where 条件
	 * @param paraKey where条件中所使用的参数键 如#{paraKey.[index]} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “index” 为parameters 索引位置
	 * @param parameters where条件中的对应参数
	 */
	void getByWherePage(Pager<SysProductLine> page,String whereSql,String paraKey,Object... parameters);
	
	
	/**
	 * 分页条件查询
	 * @param page	分页信息
	 * @param whereSql where 条件
	  * @param paraKey where条件中所使用的参数键 如#{paraKey.*} 
	 * paraKey是你想使用的键名默认为parameters，
	 *  “*” 为parameters map中的key
	 * @param parameters	where条件中的对应参数
	 */
	void getByWherePage(Pager<SysProductLine> page,String whereSql,String paraKey,Map<String, Object> parameters);
	/**
	 * 添加一条信息
	 * @param sysProductLine
	 */
	void add(SysProductLine sysProductLine);
	
	/**
	 * 更新一条信息
	 * 该更新只能更新基础信息，不能更新节点位置，顺序及数据状态
	 * 可更新字段如下:
	 * productName,productInfo,productProtocol,productHostName
	 * ,productIp,productPort,productCode
	 * @param sysProductLine
	 */
	void update(SysProductLine sysProductLine);
	
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
	 * @param productId
	 */
	void deleteLogic(String productId);
	/**
	 * 恢复逻辑删除的节点及其子节点
	 * @param productId
	 */
	void deleteLogicRestore(String productId);
	
	/**
	 * 删除节点
	 * 物理删除，不可恢复，删除的同时会物理删除所有子节点(包括前面已经逻辑删除的子节点)
	 * @param productId
	 */
	void deletePhysical(String productId);
	
	/**
	 * 获取指定节点的直接子节点
	 * @param productId
	 * @return
	 */
	List<SysProductLine> getDirectChild(String productId);
	
	/**
	 * 获取直接子节点数量
	 * @param productId
	 * @return
	 */
	int getDirectChildCount(String productId);
	
	/**
	 * 获取所有子孙节点
	 * @param productId
	 * @return
	 */
	List<SysProductLine> getAllChild(String productId);
	
	/**
	 * 获取所有子孙节点数量
	 * @param productId
	 * @return
	 */
	int getAllChildCount(String productId);
	
	/**
	 * 获取族谱路径
	 * @param productId
	 * @return
	 */
	List<SysProductLine> getPath(String productId);
	
	/**
	 * 获取节点所在层级(从1开始)
	 * @return
	 */
	int getLevel(String productId);
}
