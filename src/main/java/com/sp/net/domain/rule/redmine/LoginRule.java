package com.sp.net.domain.rule.redmine;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.sp.net.domain.Form;
import com.sp.net.domain.Rule;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-31 上午10:34:24
* @email benjaminchen555@gmail.com
*/
@SuppressWarnings("unchecked")
public class LoginRule extends Rule{

	@Override
	protected void fillData(Form form) throws Exception {
		String userName = (String) form.getFormValueMap().get("userName");
		String password = (String) form.getFormValueMap().get("password");
		HtmlPage page = form.getSite().getCurrentPage();
		


		final HtmlForm form1 = getLoginForm(page);

		final HtmlTextInput usernameInput = form1.getInputByName("username");
		final HtmlPasswordInput passwordInput = form1.getInputByName("password");

		usernameInput.setValueAttribute(userName);

		passwordInput.setValueAttribute(password);
		
	}

	@Override
	protected void submit(Form form) throws Exception {
		HtmlPage currentPage = form.getSite().getCurrentPage();
		final HtmlForm form1 = getLoginForm(currentPage);
		HtmlInput b = form1.getFirstByXPath("//input[@name='login']");
		HtmlPage main = (HtmlPage) b.click();
		getLogger().info(main.asText());
		
		if(main.asText().indexOf("我的工作台 ")>=0){
			setResult(true);
		}else {
			setResult(false);
		}
		
	}

	protected HtmlForm getLoginForm(HtmlPage currentPage) {
		return ((List<HtmlForm>) currentPage.getByXPath("//div[@id='login-form']/form")).get(0);
	}

	
}
