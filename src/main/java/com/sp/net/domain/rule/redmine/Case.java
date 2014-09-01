package com.sp.net.domain.rule.redmine;


/**
* @author 陈嘉镇
* @version 创建时间：2014-8-31 下午1:14:31
* @email benjaminchen555@gmail.com
* 案例
*/
public class Case {
	
	private String no;
	
	private String date;
	
	private String applier;
	
	private String caseType;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "Case [no=" + no + ", date=" + date + ", applier=" + applier + ", caseType=" + caseType + "]";
	}
	
	
	

}
