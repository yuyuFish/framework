package org.framework.authorize.auth.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户组model
 * @author ztgoto
 * @version  
 * @date  2016年1月12日 上午11:00:58
 */
public class AmGroup implements Serializable {
	private String groupId;	//组ID GROUP_ID varchar(128)
	private String productId;	//产品线ID 	PRODUCT_ID varchar(128)
	private String parentGroupId;	//父节点ID PARENT_GROUP_ID varchar(128)
	private String groupName;	//组名	  GROUP_NAME varchar(256)
	private String groupInfo;	//组描述	GROUP_INFO varchar(512)
	private String groupCode;  //组CODE	GROUP_CODE varchar(1024),
	private Integer isInherit;	//是否继承 	IS_INHERIT int
	private Long leftValue;  	//左值	LEFT_VALUE bigint
	private Long rightValue;	//右值	RIGHT_VALUE bigint
	private Date createTime;		//数据创建时间	CREATE_TIME datetime
	private Date editTime;			//数据修改时间	EDIT_TIME datetime
	private Date deleteTime;		//数据删除时间	DELETE_TIME datetime
	private String dataState;		//数据逻辑状态	DATA_STATE varchar(32)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupInfo() {
		return groupInfo;
	}
	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "AmGroup [groupId=" + groupId + ", productId=" + productId
				+ ", parentGroupId=" + parentGroupId + ", groupName="
				+ groupName + ", groupInfo=" + groupInfo + ", groupCode="
				+ groupCode + ", isInherit=" + isInherit + ", leftValue="
				+ leftValue + ", rightValue=" + rightValue + ", createTime="
				+ createTime + ", editTime=" + editTime + ", deleteTime="
				+ deleteTime + ", dataState=" + dataState + "]";
	}
	
	
	   
}
