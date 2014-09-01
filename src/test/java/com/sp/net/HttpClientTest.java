package com.sp.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-28 下午5:43:41
* @email benjaminchen555@gmail.com
*/
public class HttpClientTest {
	
	@Test
	public void testLogin() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet("http://localhost/redmine/login");
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		try {
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity1 = response1.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity1);
		} finally {
		    response1.close();
		}
		
		
		HttpPost httpPost = new HttpPost("http://localhost/redmine/login");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "admin"));
		nvps.add(new BasicNameValuePair("password", "admin321"));
		nvps.add(new BasicNameValuePair("back_url", "http://127.0.0.1/redmine/projects/test/issues"));
		nvps.add(new BasicNameValuePair("authenticity_token", "yfNhJld52UsM2+JZBB0aPx7uKXviUqPR/CVFi/4PK/A="));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    System.out.println(EntityUtils.toString(entity2));
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		} finally {
		    response2.close();
		}
	}

}
