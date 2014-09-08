package com.sp.net.domain.rule.redmine;

import com.sp.net.annotation.HtmlElement;

/**
* @author 陈嘉镇
* @version 创建时间：2014-9-8 上午11:11:23
* @email benjaminchen555@gmail.com
*/
public class TempCustomer {
	@HtmlElement(xpath="//input[@id='issue_subject']")
	private String subject;
	@HtmlElement(xpath="//select[@id='issue_status_id']")
	private String statue;
	@HtmlElement(xpath="//select[@id='issue_priority_id']")
	private String priority;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_2']")
	private String orderDate;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_13']")
	private String customerType;
	@HtmlElement(xpath="//*[@id='issue_custom_field_values_14']")
	private String contactMan;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_15']")
	private String phone;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_30']")
	private String fax;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_16']")
	private String qqNemail;
	@HtmlElement(xpath="//input[@id='issue_custom_field_values_33']")
	private String traceDate;
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
	public String getTraceDate() {
		return traceDate;
	}
	public void setTraceDate(String traceDate) {
		this.traceDate = traceDate;
	}
	
	
	
}
