package com.sp.net.gen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* @author 陈嘉镇
* @version 创建时间：2015-5-11 下午11:05:56
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-site.xml" })
public class ExcelMapGeneratorTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ExcelMapGenerator g ;

	@Test
	public void testParse2String() throws Exception {
		String url = "G:\\newPro\\ant\\src\\test\\resources\\新建问题 - 1. 客户信息记录 - Redmine.html";
		String s = g.parse2String(url);
		logger.info("s:{}",s);
	}
	
}
