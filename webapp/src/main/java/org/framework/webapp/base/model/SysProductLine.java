package org.framework.webapp.base.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 产品线model
 * @author ztgoto
 * @version  
 * @date  2016年1月12日 上午11:28:52
 */
public class SysProductLine implements Serializable {
	private String productId;	//产品线id	PRODUCT_ID varchar(128)
	private String parentProductId;	//父产品线id	PARENT_PRODUCT_ID varchar(128)
	private String productName;	//产品线名称	PRODUCT_NAME varchar(256)
	private String productInfo;	//产品线描述	 PRODUCT_INFO varchar(512)
	private String productProtocol;	//产品线使用网络协议(http https ftp...)	PRODUCT_PROTOCOL varchar(128)
	private String productHostName;	//产品线域名	PRODUCT_HOSTNAME varchar(256)
	private String productIp;	//产品线ip	PRODUCT_IP varchar(32)
	private Integer productPort;	//产品线端口	PRODUCT_PORT int
	private String productCode;	//产品线code	PRODUCT_CODE varchar(128)
	private Long leftValue;  	//左值	LEFT_VALUE bigint
	private Long rightValue;	//右值	RIGHT_VALUE bigint
	private Date createTime;		//数据创建时间	CREATE_TIME datetime
	private Date editTime;			//数据修改时间	EDIT_TIME datetime
	private Date deleteTime;		//数据删除时间	DELETE_TIME datetime
	private String dataState;		//数据逻辑状态	DATA_STATE varchar(32)
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getParentProductId() {
		return parentProductId;
	}
	public void setParentProductId(String parentProductId) {
		this.parentProductId = parentProductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getProductProtocol() {
		return productProtocol;
	}
	public void setProductProtocol(String productProtocol) {
		this.productProtocol = productProtocol;
	}
	public String getProductHostName() {
		return productHostName;
	}
	public void setProductHostName(String productHostName) {
		this.productHostName = productHostName;
	}
	public String getProductIp() {
		return productIp;
	}
	public void setProductIp(String productIp) {
		this.productIp = productIp;
	}
	public Integer getProductPort() {
		return productPort;
	}
	public void setProductPort(Integer productPort) {
		this.productPort = productPort;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
		return "SysProductLine [productId=" + productId + ", parentProductId="
				+ parentProductId + ", productName=" + productName
				+ ", productInfo=" + productInfo + ", productProtocol="
				+ productProtocol + ", productHostName=" + productHostName
				+ ", productIp=" + productIp + ", productPort=" + productPort
				+ ", productCode=" + productCode + ", leftValue=" + leftValue
				+ ", rightValue=" + rightValue + ", createTime=" + createTime
				+ ", editTime=" + editTime + ", deleteTime=" + deleteTime
				+ ", dataState=" + dataState + "]";
	}
	
	
}
