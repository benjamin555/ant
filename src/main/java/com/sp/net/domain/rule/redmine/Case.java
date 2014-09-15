package com.sp.net.domain.rule.redmine;

import com.sp.net.annotation.HtmlElement;


/**
* @author 陈嘉镇
* @version 创建时间：2014-8-31 下午1:14:31
* @email benjaminchen555@gmail.com
* 案例
*/
public class Case {
	
	@HtmlElement(xpath="//input[@id='issue_subject']")
	private String subject;
	@HtmlElement(xpath="//select[@id='issue_status_id']")
	private String statue;
	@HtmlElement(xpath="//select[@id='issue_priority_id']")
	private String priority;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_1']")
	private String no;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_2']")
	private String orderDate;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_3']")
	private String applier;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_18']")
	private String regCountry;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_4']")
	private String caseType;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_20']")
	private String businessProj;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_19']")
	private String type;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_6']")
	private String acceptDate;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_21']")
	private String regNo;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_22']")
	private String applyNo;
	
	
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_8']")
	private String noticeDate;
	
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_9']")
	private String regDate;
	
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_10']")
	private String endDate;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_11']")
	private String fee;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_23']")
	private String unpay;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_12']")
	private String settleStatue;
	
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_13']")
	private String customerType;
	
	/**
	 * 是否经常联系
	 */
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_35']")
	private String contactFrequent;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_14']")
	private String contactMan;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_15']")
	private String phone;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_30']")
	private String fax;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_16']")
	private String qqNemail;
	
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_27']")
	private String address;
	
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_24']")
	private String shoutongArrivalDate;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_32']")
	private String rejectArrivalDate;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_25']")
	private String certificateArrivalDate;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_26']")
	private String annualDay;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_29']")
	private String  certifHasSend;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_28']")
	private String certifSendRemark;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_31']")
	private String isSaleable;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_34']")
	private String caseStatue;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_33']")
	private String traceDate;
	
	

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}



	public String getApplier() {
		return applier;
	}

	public void setApplier(String applier) {
		this.applier = applier;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getRegCountry() {
		return regCountry;
	}

	public void setRegCountry(String regCountry) {
		this.regCountry = regCountry;
	}

	public String getBusinessProj() {
		return businessProj;
	}

	public void setBusinessProj(String businessProj) {
		this.businessProj = businessProj;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getUnpay() {
		return unpay;
	}

	public void setUnpay(String unpay) {
		this.unpay = unpay;
	}

	public String getSettleStatue() {
		return settleStatue;
	}

	public void setSettleStatue(String settleStatue) {
		this.settleStatue = settleStatue;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getQqNemail() {
		return qqNemail;
	}

	public void setQqNemail(String qqNemail) {
		this.qqNemail = qqNemail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShoutongArrivalDate() {
		return shoutongArrivalDate;
	}

	public void setShoutongArrivalDate(String shoutongArrivalDate) {
		this.shoutongArrivalDate = shoutongArrivalDate;
	}

	public String getRejectArrivalDate() {
		return rejectArrivalDate;
	}

	public void setRejectArrivalDate(String rejectArrivalDate) {
		this.rejectArrivalDate = rejectArrivalDate;
	}

	public String getCertificateArrivalDate() {
		return certificateArrivalDate;
	}

	public void setCertificateArrivalDate(String certificateArrivalDate) {
		this.certificateArrivalDate = certificateArrivalDate;
	}

	public String getAnnualDay() {
		return annualDay;
	}

	public void setAnnualDay(String annualDay) {
		this.annualDay = annualDay;
	}

	public String getCertifHasSend() {
		return certifHasSend;
	}

	public void setCertifHasSend(String certifHasSend) {
		this.certifHasSend = certifHasSend;
	}

	public String getCertifSendRemark() {
		return certifSendRemark;
	}

	public void setCertifSendRemark(String certifSendRemark) {
		this.certifSendRemark = certifSendRemark;
	}

	public String getIsSaleable() {
		return isSaleable;
	}

	public void setIsSaleable(String isSaleable) {
		this.isSaleable = isSaleable;
	}

	public String getCaseStatue() {
		return caseStatue;
	}

	public void setCaseStatue(String caseStatue) {
		this.caseStatue = caseStatue;
	}

	public String getTraceDate() {
		return traceDate;
	}

	public void setTraceDate(String traceDate) {
		this.traceDate = traceDate;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	@Override
	public String toString() {
		return "Case [subject=" + subject + ", statue=" + statue + ", priority=" + priority + ", no=" + no
				+ ", orderDate=" + orderDate + ", applier=" + applier + ", regCountry=" + regCountry + ", caseType="
				+ caseType + ", businessProj=" + businessProj + ", type=" + type + ", acceptDate=" + acceptDate
				+ ", regNo=" + regNo + ", applyNo=" + applyNo + ", noticeDate=" + noticeDate + ", regDate=" + regDate
				+ ", endDate=" + endDate + ", fee=" + fee + ", unpay=" + unpay + ", settleStatue=" + settleStatue
				+ ", customerType=" + customerType + ", contactMan=" + contactMan + ", phone=" + phone + ", fax=" + fax
				+ ", qqNemail=" + qqNemail + ", address=" + address + ", shoutongArrivalDate=" + shoutongArrivalDate
				+ ", rejectArrivalDate=" + rejectArrivalDate + ", certificateArrivalDate=" + certificateArrivalDate
				+ ", annualDay=" + annualDay + ", certifHasSend=" + certifHasSend + ", certifSendRemark="
				+ certifSendRemark + ", isSaleable=" + isSaleable + ", caseStatue=" + caseStatue + ", traceDate="
				+ traceDate + "]";
	}

	public String getContactFrequent() {
		return contactFrequent;
	}

	public void setContactFrequent(String contactFrequent) {
		this.contactFrequent = contactFrequent;
	}

	
	
	

}
