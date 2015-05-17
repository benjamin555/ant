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
	String url = "file:\\G:\\zhouxi\\模板对应表\\设备销售记录\\新建问题 - 2. 设备销售记录 - Redmine.html";


	@Test
	public void testParse2Config() throws Exception {
		String url = getUrl();
		String s = g.parse2Config(url,"Case");
		logger.info("s:{}",s);
	}
	
	@Test
	public void testParse2Java() throws Exception {
		String url = getUrl();
		
		String s = g.parse2Java(url,"Case");
		logger.info("s:{}",s);
		
	}

	private String getUrl() {
		return url;
	}
	
	@Test
	public void testGenExcel() throws Exception {
		g.genExcel(getUrl(),"fileName");
	}
	@Test
	public void testGenAll() throws Exception {
//		setUrl("file:/G:/zhouxi/表单/表单/培训记录/新建问题 - 3. 设备培训记录 - Redmine.html");
//		setUrl("file:/G:/zhouxi/模板对应表/设备销售记录/新建问题 - 2. 设备销售记录 - Redmine.html");
//		setUrl("file:/G:/zhouxi/模板对应表/客户信息记录/新建问题 - 1. 客户信息记录 - Redmine.html");
		setUrl("file:/G:/zhouxi/表单/表单/售后问题记录/新建问题 - 4. 客户售后问题记录 - Redmine.html");

		String en="Service";
		String pkg = "com.sp.net.domain.rule.redmine";
		
		
		String className=en;
		
		g.genExcel(getUrl(),"new"+en);
		g.genXml(getUrl(),pkg+"."+className,"new"+en);
		g.setPkg(pkg);
		g.genJava(getUrl(),className);
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
