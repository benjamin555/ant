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
		Form  form = redmineSite.findForm("newTempTask");
		Map<String, Object> formValueMap = new HashMap<String, Object>();
		String pathname = "G:\\newPro\\ant-web\\src\\main\\webapp\\xls\\潜在客户.xlsx";
		String mapFile = "/excelXMLConfig/excelMappingTempCustomer.xml";
		List<Object> c = getImportBeans(pathname,mapFile);
		for (Object case1 : c) {
			formValueMap.put("data", case1);
			form.setFormValueMap(formValueMap );
			form.perform("importExcel");
		}
		
		
	}
	
	@Test
	public void testCaseImport() throws Exception {
		String pathname = "G:\\newPro\\ant-web\\src\\main\\webapp\\xls\\潜在客户.xlsx";
		String mapFile = "/excelXMLConfig/excelMappingTempCustomer.xml";
		List<Object> c = getImportBeans(pathname,mapFile);
		logger.info("beans:{}",c);
	}

	protected List<Object> getImportBeans(String pathname,String mapFile) throws FileNotFoundException, Exception {
		
		File dataExcel = new File(pathname);
		InputStream inputXLS = new FileInputStream(dataExcel);
		InputStream inputXML = getClass().getResourceAsStream(mapFile);
		Map beans = new HashMap();
		List<Object> c = new ArrayList<Object>();
		beans.put("result01", c);
		JxlsUtils.readXLS(inputXLS, inputXML, beans);
		return c;
	}
	
	@After
	public void  after(){
	}
}
