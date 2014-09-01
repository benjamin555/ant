package com.sp.net.domain.rule.inte;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.domain.Form;
import com.sp.net.domain.Rule;

/**
* @author 陈嘉镇
* @version 创建时间：2014-4-8 下午5:00:53
* @email benjaminchen555@gmail.com
*/
public class RefreshTacticRule extends Rule {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	protected void fillData(Form form) throws Exception {
	}

	@Override
	protected void submit(Form form) throws Exception {
	}

	@Override
	protected void check(Form form2) throws Exception {
		super.check(form2);
		HtmlPage p = form2.getSite().getCurrentPage();
		boolean contains = p.asText().contains("策略名称");
		logger.info("contains:{}",contains);
		Assert.isTrue(contains,"页面未正常打开");
		
	}
	
	

}
