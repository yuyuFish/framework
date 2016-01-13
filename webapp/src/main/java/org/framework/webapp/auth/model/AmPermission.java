package org.framework.webapp.auth.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限操作model
 * @author ztgoto
 * @version  
 * @date  2016年1月12日 上午9:00:56
 */
public class AmPermission implements Serializable {
	private String permissionId;	//权限id	PERMISSION_ID varchar(128)
	private String productId;	//产品线ID 	PRODUCT_ID varchar(128)
	private String permissionName;	//权限名称	PERMISSION_NAME varchar(256)
	private String permissionInfo;	//权限描述信息	PERMISSION_INFO varchar(512)
	private String permissionUrl;	//权限操作URL	PERMISSION_URL varchar(2048)
	private String permissionCode;	//权限操作Code(暂使用shiro 语法)	PERMISSION_CODE varchar(1024)
	private String operateIp;		//访问者ip	OPERATE_IP varchar(128)
	private String operateType;		//操作类型(CURD) OPERATE_TYPE varchar(256)
	private String requestType;		//请求类型(GET POST) REQUEST_TYPE varchar(256)
	private String requestHeaders;	//请求头信息	REQUEST_HEADERS varchar(1024)
	private String requestParameters;	//请求参数	REQUEST_PARAMETERS varchar(1024)
	private String logicType;			//逻辑判断类型(暂定) LOGIC_TYPE varchar(128)
	private String permissionClassMethod; //操作所使用的类和方法	PERMISSION_CLASS_METHOD varchar(1024)
	private Date createTime;		//数据创建时间	CREATE_TIME datetime
	private Date editTime;			//数据修改时间	EDIT_TIME datetime
	private Date deleteTime;		//数据删除时间	DELETE_TIME datetime
	private String dataState;		//数据逻辑状态	DATA_STATE varchar(32)
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionInfo() {
		return permissionInfo;
	}
	public void setPermissionInfo(String permissionInfo) {
		this.permissionInfo = permissionInfo;
	}
	public String getPermissionUrl() {
		return permissionUrl;
	}
	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	public String getOperateIp() {
		return operateIp;
	}
	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	public String getRequestParameters() {
		return requestParameters;
	}
	public void setRequestParameters(String requestParameters) {
		this.requestParameters = requestParameters;
	}
	public String getLogicType() {
		return logicType;
	}
	public void setLogicType(String logicType) {
		this.logicType = logicType;
	}
	public String getPermissionClassMethod() {
		return permissionClassMethod;
	}
	public void setPermissionClassMethod(String permissionClassMethod) {
		this.permissionClassMethod = permissionClassMethod;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "AmPermission [permissionId=" + permissionId + ", productId="
				+ productId + ", permissionName=" + permissionName
				+ ", permissionInfo=" + permissionInfo + ", permissionUrl="
				+ permissionUrl + ", permissionCode=" + permissionCode
				+ ", operateIp=" + operateIp + ", operateType=" + operateType
				+ ", requestType=" + requestType + ", requestHeaders="
				+ requestHeaders + ", requestParameters=" + requestParameters
				+ ", logicType=" + logicType + ", permissionClassMethod="
				+ permissionClassMethod + ", createTime=" + createTime
				+ ", editTime=" + editTime + ", deleteTime=" + deleteTime
				+ ", dataState=" + dataState + "]";
	}
	
}
