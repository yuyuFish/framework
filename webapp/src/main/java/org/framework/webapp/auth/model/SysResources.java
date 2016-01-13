package org.framework.webapp.auth.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统资源
 * @author ztgoto
 * @version  
 * @date  2016年1月12日 上午11:40:37
 */
public class SysResources implements Serializable {
	private String resourcesId;	//资源id	RESOURCES_ID varchar(128)
	private String productId;	//产品线ID 	PRODUCT_ID varchar(128)
	private String parentResourcesId;	//父资源ID	PARENT_RESOURCES_ID varchar(128)
	private String resourcesName;	//资源名称	RESOURCES_NAME varchar(256)
	private String resourcesInfo;	//资源描述	RESOURCES_INFO varchar(512)
	private String resourcesCode;	//资源Code	RESOURCES_CODE varchar(128)
	private Integer resourcesType;	//资源类型		RESOURCES_TYPE int,
	private String requestUrl;	//资源请求路径	 REQUEST_URL varchar(1024),
	private String areaId;		//区域id（暂定）	AREA_ID varchar(128),
	private Long sort;		//排序	SORT int
	private Long leftValue;  	//左值	LEFT_VALUE bigint
	private Long rightValue;	//右值	RIGHT_VALUE bigint
	private Date createTime;		//数据创建时间	CREATE_TIME datetime
	private Date editTime;			//数据修改时间	EDIT_TIME datetime
	private Date deleteTime;		//数据删除时间	DELETE_TIME datetime
	private String dataState;		//数据逻辑状态	DATA_STATE varchar(32)
	public String getResourcesId() {
		return resourcesId;
	}
	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getParentResourcesId() {
		return parentResourcesId;
	}
	public void setParentResourcesId(String parentResourcesId) {
		this.parentResourcesId = parentResourcesId;
	}
	public String getResourcesName() {
		return resourcesName;
	}
	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}
	public String getResourcesInfo() {
		return resourcesInfo;
	}
	public void setResourcesInfo(String resourcesInfo) {
		this.resourcesInfo = resourcesInfo;
	}
	public String getResourcesCode() {
		return resourcesCode;
	}
	public void setResourcesCode(String resourcesCode) {
		this.resourcesCode = resourcesCode;
	}
	public Integer getResourcesType() {
		return resourcesType;
	}
	public void setResourcesType(Integer resourcesType) {
		this.resourcesType = resourcesType;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public Long getLeftValue() {
		return leftValue;
	}
	public void setLeftValue(Long leftValue) {
		this.leftValue = leftValue;
	}
	public Long getRightValue() {
		return rightValue;
	}
	public void setRightValue(Long rightValue) {
		this.rightValue = rightValue;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public Date getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	public String getDataState() {
		return dataState;
	}
	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
	@Override
	public String toString() {
		return "SysResources [resourcesId=" + resourcesId + ", productId="
				+ productId + ", parentResourcesId=" + parentResourcesId
				+ ", resourcesName=" + resourcesName + ", resourcesInfo="
				+ resourcesInfo + ", resourcesCode=" + resourcesCode
				+ ", resourcesType=" + resourcesType + ", requestUrl="
				+ requestUrl + ", areaId=" + areaId + ", sort=" + sort
				+ ", leftValue=" + leftValue + ", rightValue=" + rightValue
				+ ", createTime=" + createTime + ", editTime=" + editTime
				+ ", deleteTime=" + deleteTime + ", dataState=" + dataState
				+ "]";
	}
	
	
}
