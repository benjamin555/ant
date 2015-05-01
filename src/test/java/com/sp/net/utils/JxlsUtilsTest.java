package com.sp.net.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 陈嘉镇
* @version 创建时间：2015-4-29 下午10:32:46
*/
public class JxlsUtilsTest {
	
	private Logger logger =LoggerFactory.getLogger(getClass());
	
	@Test
	public void testRead() throws Exception {
		Map beans = new HashMap();
		List<Object> c = new ArrayList<Object>();
		beans.put("result01", c);
		InputStream inputXML = getClass().getResourceAsStream("/excelXMLConfig/excelMappingCase.xml");
		InputStream inputXLS = new FileInputStream("G:/newPro/ant/src/test/resources/case.xls");
		JxlsUtils.readXLS(inputXLS, inputXML, beans);
		logger.info("ls:{}",c);
		
	}

}
