package com.sp.net.command.impl;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.WebClient;
import com.sp.net.command.Command;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-27 下午4:48:05
* @email benjaminchen555@gmail.com
*/
public class OpenPage implements Command {
	private String url;

	private WebClient webClient;
	@Override
	public void execute() throws Exception {
		HtmlPage page = webClient.getPage(url);
		webClient.setCurrentPage(page);
	}

	public OpenPage(String url, WebClient webClient) {
		this.url = url;
		this.webClient =webClient;
	}

}
