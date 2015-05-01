package com.sp.net.domain.rule.redmine;

import com.sp.net.annotation.HtmlElement;

/**
* @author 陈嘉镇
* @version 创建时间：2015-4-28 下午6:09:45
*/
public class Contract {
	
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_4']")
	private String contractDate;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_5']")
	private String no;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_1']")
	private String purBU;
	
	
	@HtmlElement(xpath="//*[@id='issue_tracker_id']")
	private String productName;	
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_6']")
	private Integer cnt;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_33']")
	private String salesManager;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_32']")
	private String zone;
	
	
	@HtmlElement(xpath="//*[@id='issue_subject']")
	private String endUser;
	
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_9']")
	private String deliveryDate;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_11']")
	private String address;
	
	
	private String contactMan;
	
	private String contactPosition;
	
	private String contactPhone;
	
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_14']")
	private String room;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_15']")
	private String xz;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_16']")
	private String productType;
	
	
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_24']")
	private String remarks;

	public String getPurBU() {
		return purBU;
	}

	public void setPurBU(String purBU) {
		this.purBU = purBU;
	}


	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getSalesManager() {
		return salesManager;
	}

	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	@Override
	public String toString() {
		return "Contract [contractDate=" + contractDate + ", no=" + no + ", purBU=" + purBU + ", productName="
				+ productName + ", cnt=" + cnt + ", salesManager=" + salesManager + ", zone=" + zone + ", endUser="
				+ endUser + ", deliveryDate=" + deliveryDate + ", address=" + address + ", contactMan=" + contactMan
				+ ", contactPosition=" + contactPosition + ", contactPhone=" + contactPhone + ", room=" + room
				+ ", xz=" + xz + ", productType=" + productType + ", remarks=" + remarks + "]";
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getEndUser() {
		return endUser;
	}

	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public String getContactPosition() {
		return contactPosition;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	

}
