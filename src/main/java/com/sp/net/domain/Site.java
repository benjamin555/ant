package com.sp.net.domain;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.command.impl.OpenPage;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-28 上午11:53:01
* @email benjaminchen555@gmail.com
*/
public class Site {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String url;
	
	private LoginForm loginForm;
	

	private List<Form> forms;

	private com.sp.net.WebClient webClient ;
	
	private String currentLoginUser;

	public Site(String url2) {
		this.url = url2;
	}

	public Site() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public boolean login(String userName, String password) throws Exception {
		logger.info("userName:{}",userName);
		logger.info("currentLoginUser:{}",currentLoginUser);
		if (StringUtils.isNoneBlank(userName)) {
			if(userName.equals(currentLoginUser)){
				return true;
			}
			webClient.setLoginInfo(this, userName, password);
			boolean b =webClient.doLogin();
			if (b) {
				currentLoginUser = userName;
			}
			return b;
		}
		return false;
	}

	public Form findForm(final String formKey) throws Exception  {
		Form f = (Form) CollectionUtils.find(forms, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				Form form = (Form) object;
				if (formKey.equals(form.getKey())) {
					return true;
				}
				return false;
			}
		});

//		OpenPage openPage = new OpenPage(f.getUrl(), webClient);
//		webClient.perform(openPage);

		return f;
	}

	public String getCurrentLoginUser() {
		return currentLoginUser;
	}

	public void setCurrentLoginUser(String currentLoginUser) {
		this.currentLoginUser = currentLoginUser;
	}
	
	public HtmlPage getCurrentPage() {
		return webClient.getCurrentPage();
	}
	
	public void setCurrentPage(HtmlPage h) {
		 webClient.setCurrentPage(h);
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

	public void turn2LoginPage() throws Exception {
		OpenPage openPage = new OpenPage(loginForm.getUrl(), webClient);
		webClient.perform(openPage);
	}

	public void login(String userName, String password, String vcode) throws Exception {
		logger.info("userName:{}",userName);
		logger.info("currentLoginUser:{}",currentLoginUser);
		if (!userName.equals(currentLoginUser)) {
			webClient.setLoginInfo(this, userName, password,vcode);
			webClient.doLogin();
			currentLoginUser = userName;
		}
	}

	/**
	 * 获取验证码图片src
	 * @return
	 */
	public String getLoginFormVcodeImgSrc() {
		HtmlPage  h =getCurrentPage();
		HtmlImage image =  (HtmlImage)h.getHtmlElementById(getLoginForm().getVcodeImgId());
		return image.getSrcAttribute();
	}

	public boolean isLoginFormHasVcode() {
		if (getLoginForm()!=null&&StringUtils.isNotBlank(getLoginForm().getVcodeImgId())) {
			return true;
		}
		return false;
	}

	public com.sp.net.WebClient getWebClient() {
		logger.info("webClient:{}",webClient.toString());
		return webClient;
	}

	public void setWebClient(com.sp.net.WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	
	
}
