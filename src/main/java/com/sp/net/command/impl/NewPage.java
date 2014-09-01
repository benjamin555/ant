package com.sp.net.command.impl;

import com.sp.net.WebClient;
import com.sp.net.command.Command;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-27 下午4:48:05
* @email benjaminchen555@gmail.com
* 新建一个页面，如果窗口已打开相同的url则直接在该窗口刷新页面
*/
public class NewPage implements Command {
	private String url;

	private WebClient webClient;
	@Override
	public void execute() throws Exception {
		webClient.newPage(url);
	}

	public NewPage(String url, WebClient webClient) {
		this.url = url;
		this.webClient =webClient;
	}

}
