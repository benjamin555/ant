package com.sp.net.gen;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.WebClient;

/**
* @author 陈嘉镇
* @version 创建时间：2015-5-11 下午11:09:08
*/
@Component
public class ExcelMapGenerator {
	private WebClient c;

	public String parse2String(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlPage page = c.getPage(url);
		HtmlForm form = (HtmlForm) page.getByXPath("//*[@id='issue-form']").get(0);
		
		
		return null;
	}

}
