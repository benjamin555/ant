package com.sp.net.curl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * 
 * 
 * @author: huazhang
  * @since: 2011-4-15
  */

public class AutoTop {
	
	private static Logger logger = LoggerFactory.getLogger(AutoTop.class);

	public final static String CONTENT_TYPE = "Content-Type";

	public static Content curl(String method, String sUrl, Map<String, String> paramMap,
			Map<String, String> requestHeaderMap, boolean isOnlyReturnHeader) {
		Content content = null;
		HttpURLConnection httpUrlConnection = null;
		InputStream in = null;
		try {
			URL url = new URL(sUrl);
			boolean isPost = "POST".equals(method);
			if (Utils.isEmptyString(method) || (!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method))) {
				method = "POST";
			}
			URL resolvedURL = url;
			if ("GET".equals(method) && !Utils.isEmptySafe(paramMap)) {
				boolean firstParam = true;
				StringBuffer newUrlBuffer = new StringBuffer(url.toExternalForm());
				if (url.getQuery() == null) {
					newUrlBuffer.append("?");
				} else {
					newUrlBuffer.append("&");
				}
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					String encName = URLEncoder.encode(entry.getKey(), StringUtils.ENC_DESC_UTF8);
					if (firstParam) {
						firstParam = false;
					} else {
						newUrlBuffer.append("&");
					}
					String encValue = URLEncoder.encode(entry.getValue(), StringUtils.ENC_DESC_UTF8);
					newUrlBuffer.append(encName);
					newUrlBuffer.append("=");
					newUrlBuffer.append(encValue);
				}
				resolvedURL = new java.net.URL(newUrlBuffer.toString());
			}

			URLConnection urlConnection = resolvedURL.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;
			httpUrlConnection.setRequestMethod(method);
			// Do not follow redirects, We will handle redirects ourself
			httpUrlConnection.setInstanceFollowRedirects(false);
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setConnectTimeout(5000);
			urlConnection.setReadTimeout(5000);
			urlConnection.setUseCaches(false);
			urlConnection.setDefaultUseCaches(false);
			// set request header
			if (!Utils.isEmptySafe(requestHeaderMap)) {
				for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
					String key = entry.getKey();
					String val = entry.getValue();
					if (key != null && val != null) {
						urlConnection.setRequestProperty(key, val);
					}
				}
			}
			if (isPost) {
				urlConnection.setDoOutput(true);
				ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
				boolean firstParam = true;
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					String encName = URLEncoder.encode(entry.getKey(), StringUtils.ENC_DESC_UTF8);
					if (firstParam) {
						firstParam = false;
					} else {
						bufOut.write((byte) '&');
					}
					String encValue = URLEncoder.encode(entry.getValue(), StringUtils.ENC_DESC_UTF8);
					bufOut.write(encName.getBytes(StringUtils.ENC_DESC_UTF8));
					bufOut.write((byte) '=');
					bufOut.write(encValue.getBytes(StringUtils.ENC_DESC_UTF8));
				}
				byte[] postContent = bufOut.toByteArray();
				if (urlConnection instanceof HttpURLConnection) {
					((HttpURLConnection) urlConnection).setFixedLengthStreamingMode(postContent.length);
				}
				OutputStream postOut = urlConnection.getOutputStream();
				postOut.write(postContent);
				postOut.flush();
				postOut.close();
			}
			int responseCode = httpUrlConnection.getResponseCode();
			// We handle redirects ourself
			if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
				String location = httpUrlConnection.getHeaderField("Location");
				URL newAction = new URL(url, location);
				// Recurse
				StringBuffer newUrlSb = new StringBuffer(newAction.getProtocol() + "://" + newAction.getHost());
				if (newAction.getPort() != -1) {
					newUrlSb.append(":" + newAction.getPort());
				}
				if (newAction.getPath() != null) {
					newUrlSb.append(newAction.getPath());
				}
				if (newAction.getQuery() != null) {
					newUrlSb.append("?" + newAction.getQuery());
				}
				if (newAction.getRef() != null) {
					newUrlSb.append("#" + newAction.getRef());
				}
				return curl("GET", newUrlSb.toString(), null, requestHeaderMap, isOnlyReturnHeader);
			} else if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
				byte[] bytes = new byte[0];
				if (!isOnlyReturnHeader) {
					in = httpUrlConnection.getInputStream();
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					while (true) {
						int rc = in.read(buf);
						if (rc <= 0) {
							break;
						} else {
							bout.write(buf, 0, rc);
						}
					}
					bytes = bout.toByteArray();
					in.close();
				}
				// only fetch Content-Length and Last-Modified header
				String encoding = null;
				if (Utils.isEmptyString(encoding)) {
					encoding = getEncodingFromContentType(httpUrlConnection.getHeaderField(CONTENT_TYPE));
				}
				content = new Content(sUrl, new String(bytes, encoding), httpUrlConnection.getHeaderFields());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (httpUrlConnection != null) {
				httpUrlConnection.disconnect();
			}
		}
		return content;
	}

	public static String getEncodingFromContentType(String contentType) {
		String encoding = null;
		if (Utils.isEmptyString(contentType)) {
			return null;
		}
		StringTokenizer tok = new StringTokenizer(contentType, ";");
		if (tok.hasMoreTokens()) {
			tok.nextToken();
			while (tok.hasMoreTokens()) {
				String assignment = tok.nextToken().trim();
				int eqIdx = assignment.indexOf('=');
				if (eqIdx != -1) {
					String varName = assignment.substring(0, eqIdx).trim();
					if ("charset".equalsIgnoreCase(varName)) {
						String varValue = assignment.substring(eqIdx + 1).trim();
						if (varValue.startsWith("\"") && varValue.endsWith("\"")) {
							// substring works on indices
							varValue = varValue.substring(1, varValue.length() - 1);
						}
						if (Charset.isSupported(varValue)) {
							encoding = varValue;
						}
					}
				}
			}
		}
		if (Utils.isEmptyString(encoding)) {
			return StringUtils.ENC_DESC_UTF8;
		}
		return encoding;
	}

	public static void main(String[] args) {
		//设置代理
		System.setProperty("proxySet", "true");
		System.setProperty("http.proxyHost", "proxy01.cnooc");
		System.setProperty("http.proxyPort", "8080");

		System.setProperty("http.proxyUser", "chenjiazhen");
		System.setProperty("http.proxyPassword", "515736");

		// login
		String userName = "haha";
		String password = "haha";
		String loginUrl = "http://114.215.127.231/ohw/login.jsp";
		String rateReviewUrl = "http://114.215.127.231/ohw/qstatement!list.action";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		Content content = curl("POST", loginUrl, paramMap, null, false);

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
		content = curl("POST", rateReviewUrl, paramMap, requestHeaders, false);

		logger.info(content.getBody());
	}

}
