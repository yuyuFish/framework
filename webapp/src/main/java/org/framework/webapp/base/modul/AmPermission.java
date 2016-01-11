package org.framework.webapp.base.modul;

import java.io.Serializable;
import java.util.Date;

public class AmPermission implements Serializable {
	private String permissionId;
	private String permissionName;
	private String permissionInfo;
	private String permissionUrl;
	private String permissionCode;
	private String operateIp;
	private String operateType;
	private String requestType;
	private String requestHeaders;
	private String requestParameters;
	private String logicType;
	private String permissionClassMethod;
	private Date createTime;
	private Date editTime;
	private Date deleteTime;
	private String dataState;
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
	@Override
	public String toString() {
		return "AmPermission [permissionId=" + permissionId
				+ ", permissionName=" + permissionName + ", permissionInfo="
				+ permissionInfo + ", permissionUrl=" + permissionUrl
				+ ", permissionCode=" + permissionCode + ", operateIp="
				+ operateIp + ", operateType=" + operateType + ", requestType="
				+ requestType + ", requestHeaders=" + requestHeaders
				+ ", requestParameters=" + requestParameters + ", logicType="
				+ logicType + ", permissionClassMethod="
				+ permissionClassMethod + ", createTime=" + createTime
				+ ", editTime=" + editTime + ", deleteTime=" + deleteTime
				+ ", dataState=" + dataState + "]";
	}
	
}
