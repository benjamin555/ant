package com.sp.net.domain;
/**
* @author 陈嘉镇
* @version 创建时间：2014-4-8 上午10:01:07
* @email benjaminchen555@gmail.com
*/
public class LoginForm extends Form{

	/**
	 * 验证码id
	 */
	private String vcodeImgId;
	
	/**
	 * 表单提交url
	 */
	private String formUrl;

	public String getVcodeImgId() {
		return vcodeImgId;
	}

	public void setVcodeImgId(String vcodeImgId) {
		this.vcodeImgId = vcodeImgId;
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	
}
