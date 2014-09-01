package com.sp.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.net.domain.Form;
import com.sp.net.domain.Site;
import com.sp.net.domain.rule.redmine.Case;
import com.sp.net.utils.JxlsUtils;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-27 上午9:55:47
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-site.xml" })
@SuppressWarnings({"rawtypes","unchecked"})
public class HtmlUnitTest {
	
	private Logger logger = LoggerFactory.getLogger(HtmlUnitTest.class);
	@Autowired
	private Site inteSite;
	@Autowired
	private Site qunarSite;
	@Autowired
	private Site redmineSite;
	
	
	
	
	
	@Test
	public void testDelApply() throws Exception {
		String userName = "ac_xiaochao";
		String password = "test321";
		inteSite.turn2LoginPage();
		inteSite.login(userName, password);
		
		Form form = inteSite.findForm("caogaoList");
		form.perform("ldar");
	}
	
	/**
	 * 测试同时跑两个表单，在一个网站上（即一个浏览器上）
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testRun2FormOn1Site() throws Exception {
		
		String userName = "ac_xiaochao";
		String password = "test321";
//		inteSite.turn2LoginPage();
		inteSite.login(userName, password);
		logger.info("start  rar.");
		Form  form = inteSite.findForm("caogaoList");
		form.perform("rar");
		logger.info("end  rar.");
		
		logger.info("start  rtr.");
		form = inteSite.findForm("tacticList");
		form.perform("rtr");
		logger.info("end  rtr.");
		
		logger.info("main sleep start.");
		Thread.currentThread().sleep(50000);
		logger.info("main sleep end.");
		
	}
	
	@Test
	public void testRedmineImport() throws Exception {
		
		redmineSite.login("admin", "admin321");
		Form  form = redmineSite.findForm("newTask");
		Map<String, Object> formValueMap = new HashMap<String, Object>();
		List<Case> c = getImportBeans();
		for (Case case1 : c) {
			formValueMap.put("data", case1);
			form.setFormValueMap(formValueMap );
			form.perform("redmineImportExcel");
		}
		
		
	}
	
	@Test
	public void testCaseImport() throws Exception {
		
		List<Case> c = getImportBeans();
		logger.info("beans:{}",c);
	}

	protected List<Case> getImportBeans() throws FileNotFoundException, Exception {
		File dataExcel = new File("e:\\temp\\case.xls");
		InputStream inputXLS = new FileInputStream(dataExcel);
		InputStream inputXML = getClass().getResourceAsStream("/excelXMLConfig/excelMappingCase.xml");
		Map beans = new HashMap();
		List<Case> c = new ArrayList<Case>();
		beans.put("result01", c);
		JxlsUtils.readXLS(inputXLS, inputXML, beans);
		return c;
	}
	
	@After
	public void  after(){
	}
}
