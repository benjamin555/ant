package com.sp.net.domain.rule.douban;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.sp.net.WebClient;
import com.sp.net.domain.Form;
import com.sp.net.domain.Rule;

/**
* @author 陈嘉镇
* @version 创建时间：2014-4-2 上午11:18:49
* @email benjaminchen555@gmail.com
*/
public class LoginRule extends Rule {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void fillData(Form form1) throws Exception {
		String userName = (String) form1.getFormValueMap().get("userName");
		String password = (String) form1.getFormValueMap().get("password");
		String vcode = (String) form1.getFormValueMap().get("vcode");
		HtmlPage page = form1.getSite().getCurrentPage();
		
		final HtmlForm form = page.getFormByName("lzform");

		final HtmlTextInput usernameInput = form.getInputByName("form_email");
		final HtmlPasswordInput passwordInput = form.getInputByName("form_password");
		final HtmlTextInput vcodeInput = form.getInputByName("captcha-solution");

		usernameInput.setValueAttribute(userName);

		passwordInput.setValueAttribute(password);
		
		vcodeInput.setValueAttribute(vcode);
		
	}

	@Override
	protected void submit(Form form) throws Exception {
		HtmlPage page = form.getSite().getCurrentPage();
//		HtmlForm loginForm =page.getFormByName("loginForm");
		final HtmlSubmitInput submitBtn = (HtmlSubmitInput) page.getElementByName("user_login");
		HtmlPage p = submitBtn.click();
		form.getSite().setCurrentPage(p);
//		logger.info(p.asText());
		
	}

	@Override
	protected void check(Form form) throws Exception {
		HtmlPage page = form.getSite().getCurrentPage();
		String asText = page.asText();
		Assert.isTrue(asText.contains("我的豆瓣"),asText) ;
	}
	
	/**
	 * 取消页面跳转
	 */
	public Page turn2Page() throws Exception {
		WebClient webClient = getForm().getSite().getWebClient();
		return webClient.getCurrentPage();
	}
	

}
