package com.sp.net.domain.rule.qunar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
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
		
		final HtmlForm form = page.getFormByName("loginForm");

		final HtmlTextInput usernameInput = form.getInputByName("username");
		final HtmlPasswordInput passwordInput = form.getInputByName("password");
		final HtmlTextInput vcodeInput = form.getInputByName("vcode");

//		usernameInput.setValueAttribute(userName);

//		passwordInput.setValueAttribute(password);
		
//		vcodeInput.setValueAttribute(vcode);
		
	}

	@Override
	protected void submit(Form form) throws Exception {
		HtmlPage page = form.getSite().getCurrentPage();
//		HtmlForm loginForm =page.getFormByName("loginForm");
		final HtmlSubmitInput submitBtn = (HtmlSubmitInput) page.getElementById("submit");
		HtmlPage p = submitBtn.click();
		
		logger.info(p.asText());
		
	}

}
