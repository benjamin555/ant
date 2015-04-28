package com.sp.net.curl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 陈嘉镇
* @version 创建时间：2014-12-8 下午12:03:34
* @email benjaminchen555@gmail.com
*/
public class AutoTopTest {
	private static Logger logger = LoggerFactory.getLogger(AutoTopTest.class);
	@Test
	public void testCurl() throws Exception {
		//设置代理
				System.setProperty("proxySet", "true");
				System.setProperty("http.proxyHost", "proxy01.cnooc");
				System.setProperty("http.proxyPort", "8080");

				System.setProperty("http.proxyUser", "chenjiazhen");
				System.setProperty("http.proxyPassword", "515736");

				// login
				String userName = "haha";
				String password = "haha";
				String loginUrl = "http://114.215.127.231/ohw/login.action";
				String rateReviewUrl = "http://114.215.127.231/ohw/qstatement!list.action";
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("userName", userName);
				paramMap.put("password", password);
				Content content = AutoTop.curl("POST", loginUrl, paramMap, null, false);

				// build request headers & do rate of user review
				List<String> cookieList = content.getHeaders().get("Set-Cookie");
				Map<String, String> requestHeaders = new HashMap<String, String>();
				if (!Utils.isEmptySafe(cookieList)) {
					StringBuffer sb = new StringBuffer();
					boolean isLast = false;
					int i = 0;
					for (String val : cookieList) {
						i++;
						if (i == cookieList.size()) {
							isLast = true;
						}
						int pos = val.indexOf("=");
						if (pos != -1) {
							String cookieName = val.substring(0, pos);
							String cookieVal = val.substring(pos + 1);
							cookieVal = cookieVal.split(";")[0];
							if (isLast) {
								sb.append(cookieName + "=" + cookieVal);
							} else {
								sb.append(cookieName + "=" + cookieVal + ";");
							}
						}
					}
					requestHeaders.put("Cookie", sb.toString());
				}
				paramMap = new HashMap<String, String>();
				//		paramMap.put("rateValue", "1");
				content = AutoTop.curl("POST", rateReviewUrl, paramMap, requestHeaders, false);

				logger.info(content.getBody());
	}
}
