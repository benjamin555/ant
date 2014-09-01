package com.sp.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.TopLevelWindow;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.sp.net.command.Command;
import com.sp.net.domain.Form;
import com.sp.net.domain.Rule;
import com.sp.net.domain.Site;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-27 下午4:14:58
* @email benjaminchen555@gmail.com
*/
public class WebClient {
	private com.gargoylesoftware.htmlunit.WebClient webClient = new com.gargoylesoftware.htmlunit.WebClient();
	private Site site;
	private String userName;
	private String password;
	private String vcode;
	private Logger logger = LoggerFactory.getLogger(WebClient.class);


	private ThreadLocal<HtmlPage> threadPage = new ThreadLocal<HtmlPage>();

	/**
	 * 是否开启代理
	 */
	private boolean enableProxy;

	/**
	 * 代理服务器
	 */
	private String proxyServer;

	/**
	 * 代理端口
	 */
	private int ProxyPort;

	public WebClient() {
		super();
	}

	public void setLoginInfo(Site s, String userName, String password) {
		this.site = s;
		this.userName = userName;
		this.password = password;
	}

	public void doLogin() throws Exception {
//				site.turn2LoginPage();
		Form loginForm = site.getLoginForm();
		Rule rule = loginForm.getRules().get(0);
		Map<String, Object> formValueMap = new HashMap<String, Object>();
		formValueMap.put("userName", userName);
		formValueMap.put("password", password);
		formValueMap.put("vcode", vcode);
		loginForm.setFormValueMap(formValueMap);
		loginForm.perform(rule);
		//		HtmlPage page;
		//		page = webClient.getPage(url);
		//
		//		Assert.assertEquals("中国海油采办业务系统", page.getTitleText());
		//
		//		final String pageAsText = page.asText();
		//		Assert.assertTrue(pageAsText.contains("姓名"));
		//
		//		final HtmlForm form = page.getFormByName("frmLogin");
		//
		//		final HtmlTextInput usernameInput = form.getInputByName("j_username");
		//		final HtmlPasswordInput passwordInput = form.getInputByName("j_password");
		//
		//		usernameInput.setValueAttribute(userName);
		//
		//		passwordInput.setValueAttribute(password);
		//
		//		ScriptResult s = page.executeJavaScript("doLogin();");
		//		HtmlPage main = (HtmlPage) s.getNewPage();
		//
		//		HtmlPage banner = (HtmlPage) main.getFrameByName("banner").getEnclosedPage();
		//
		//		String asText = banner.asText();
		//		logger.info(asText);
		//
		//		logger.info(main.asText());
	}

	/**
	 * 初始化
	 */
	public void initClient() {
		logger.info("enableProxy:{}", enableProxy);
		if (enableProxy) {
			logger.info("ProxyServer():{}", getProxyServer());
			ProxyConfig proxyConfig = new ProxyConfig(getProxyServer(), getProxyPort());
			webClient.getOptions().setProxyConfig(proxyConfig);
		}

		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setPrintContentOnFailingStatusCode(false);
		//		webClient.getOptions().setCssEnabled(false);

		webClient.setJavaScriptTimeout(10000);
		webClient.setAlertHandler(new AlertHandler() {
			@Override
			public void handleAlert(Page page, String message) {
				throw new RuntimeException(message);
			}
		});
		webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {
			@Override
			public void timeoutError(HtmlPage htmlPage, long allowedTime, long executionTime) {
				logger.info("timeoutError");
			}

			@Override
			public void scriptException(HtmlPage htmlPage, ScriptException scriptException) {
				logger.info("scriptException");
			}

			@Override
			public void malformedScriptURL(HtmlPage htmlPage, String url, MalformedURLException malformedURLException) {
				logger.info("malformedScriptURL");
			}

			@Override
			public void loadScriptError(HtmlPage htmlPage, URL scriptUrl, Exception exception) {
				logger.info("loadScriptError");
			}
		});
	}

	public void closeAllWindows() {
		webClient.closeAllWindows();
	}

	public void perform(Command c) throws Exception {
		c.execute();
	}

	public HtmlPage getPage(String url2) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		return webClient.getPage(url2);
	}

	public HtmlPage getCurrentPage() {
		HtmlPage htmlPage = threadPage.get();
		logger.info("getCurrentPage:{}",htmlPage);
		return htmlPage;
	}

	public void setCurrentPage(HtmlPage currentPage) {
		logger.info("setCurrentPage:{}",currentPage);
		this.threadPage.set(currentPage);
	}

	public void setLoginInfo(Site s, String userName2, String password2, String vcode) {
		setLoginInfo(s, userName2, password2);
		this.vcode = vcode;
	}

	public boolean isEnableProxy() {
		return enableProxy;
	}

	public void setEnableProxy(boolean enableProxy) {
		this.enableProxy = enableProxy;
	}

	public String getProxyServer() {
		return proxyServer;
	}

	public void setProxyServer(String proxyServer) {
		this.proxyServer = proxyServer;
	}

	public int getProxyPort() {
		return ProxyPort;
	}

	public void setProxyPort(int proxyPort) {
		ProxyPort = proxyPort;
	}

	public ThreadLocal<HtmlPage> getThreadPage() {
		return threadPage;
	}

	public HtmlPage newPage(String url) throws IOException {
		HtmlPage h = null;
		URL u;
		u = new URL(url);
		WebWindow w = getWebWindowByName(url);
		logger.info("WebWindow:{}",w);
		if (w!= null) {
			TopLevelWindow t = (TopLevelWindow) w;
			t.close();
		}
		w = webClient.openWindow(u, url);
		h = (HtmlPage) w.getEnclosedPage();
		setCurrentPage(h);
		return h;
	}

	private WebWindow getWebWindowByName(final String name) {
		logger.info("name:{}",name);
		List<WebWindow> ls =  webClient.getWebWindows();
		WebWindow rWebWindow = (WebWindow) CollectionUtils.find(ls, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				WebWindow w =	(WebWindow)object;
				return w.getName().equals(name);
			}
		});
		return rWebWindow;
	}

}
