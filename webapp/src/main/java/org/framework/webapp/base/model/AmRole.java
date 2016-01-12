package org.framework.webapp.base.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 角色 model
 * @author ztgoto
 * @version  
 * @date  2016年1月12日 上午11:09:56
 */
public class AmRole implements Serializable {
	private String roleId;	//角色id	ROLE_ID varchar(128) not null,
	private String productId;	//产品线ID 	PRODUCT_ID varchar(128)
	private String parentRoleId;	//父角色id PARENT_ROLE_ID varchar(128)
	private String roleName;	//角色名	ROLE_NAME varchar(256),
	private String roleInfo;	//角色描述	ROLE_INFO varchar(512),
	private String roleCode;	//角色CODE ROLE_CODE varchar(1024),
	private Integer isInherit;	//是否继承 	IS_INHERIT int
	private Long leftValue;  	//左值	LEFT_VALUE bigint
	private Long rightValue;	//右值	RIGHT_VALUE bigint
	private Date createTime;		//数据创建时间	CREATE_TIME datetime
	private Date editTime;			//数据修改时间	EDIT_TIME datetime
	private Date deleteTime;		//数据删除时间	DELETE_TIME datetime
	private String dataState;		//数据逻辑状态	DATA_STATE varchar(32)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getParentRoleId() {
		return parentRoleId;
	}
	public void setParentRoleId(String parentRoleId) {
		this.parentRoleId = parentRoleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Integer getIsInherit() {
		return isInherit;
	}
	public void setIsInherit(Integer isInherit) {
		this.isInherit = isInherit;
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
		return "AmRole [roleId=" + roleId + ", productId=" + productId
				+ ", parentRoleId=" + parentRoleId + ", roleName=" + roleName
				+ ", roleInfo=" + roleInfo + ", roleCode=" + roleCode
				+ ", isInherit=" + isInherit + ", leftValue=" + leftValue
				+ ", rightValue=" + rightValue + ", createTime=" + createTime
				+ ", editTime=" + editTime + ", deleteTime=" + deleteTime
				+ ", dataState=" + dataState + "]";
	}
	
	
	
}
