package com.sp.net.domain.rule.inte;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
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
		HtmlPage page = form1.getSite().getCurrentPage();
		
		Assert.assertEquals("中国海油采办业务系统", page.getTitleText());

		final String pageAsText = page.asText();
		Assert.assertTrue(pageAsText.contains("姓名"));

		final HtmlForm form = page.getFormByName("frmLogin");

		final HtmlTextInput usernameInput = form.getInputByName("j_username");
		final HtmlPasswordInput passwordInput = form.getInputByName("j_password");

		usernameInput.setValueAttribute(userName);

		passwordInput.setValueAttribute(password);
	}

	@Override
	protected void submit(Form form) throws Exception {
		HtmlPage page = form.getSite().getCurrentPage();
		ScriptResult s = page.executeJavaScript("doLogin();");
		HtmlPage main = (HtmlPage) s.getNewPage();

		HtmlPage banner = (HtmlPage) main.getFrameByName("banner").getEnclosedPage();

		String asText = banner.asText();
		logger.info(asText);
		logger.info(main.asText());
		
	}

}
