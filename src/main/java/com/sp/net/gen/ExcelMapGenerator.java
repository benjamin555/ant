package com.sp.net.gen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.WebClient;

/**
* @author 陈嘉镇
* @version 创建时间：2015-5-11 下午11:09:08
*/
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExcelMapGenerator {
	private String pkg;
	
	@Resource(name = "webClient")
	private WebClient c;
	@Autowired
	private FreeMarkerService freeMarkerService;
	@Autowired
	private XLSExport export;
	@Autowired
	private TextExport textExport;
	
	private String outputPath = "d:/temp/";

	public String parse2Config(String url, String clazz) throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		List<HtmlElement> ls = getFormElements(url);
		List<Map<String, String>> ms = new ArrayList<Map<String, String>>();
		int i = 0;
		for (HtmlElement ele : ls) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("field", ele.getAttribute("id"));
			m.put("col", i + "");

			ms.add(m);
			i++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clazz", clazz);
		map.put("ms", ms);
		return freeMarkerService.populateTempalte("map.ftl", map);
	}


	public String parse2Java(String url, String className) throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		List<HtmlElement> ls = getFormElements(url);
		List<Map<String, String>> ms = new ArrayList<Map<String, String>>();
		int i = 0;
		for (HtmlElement ele : ls) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("field", ele.getAttribute("id"));
			m.put("col", i + "");
			ms.add(m);
			i++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pkg", getPkg());
		map.put("clazz", className);
		map.put("ms", ms);
		return freeMarkerService.populateTempalte("java.ftl", map);
	}

	@SuppressWarnings("unchecked")
	private List<HtmlElement> getFormElements(String url) throws MalformedURLException, IOException {
		HtmlPage page = c.getPage(url);
		HtmlForm form = (HtmlForm) page.getByXPath("//*[@id='issue-form']").get(0);
		List<HtmlElement> ls = (List<HtmlElement>) form
				.getByXPath("descendant::select|descendant::input[@type='text']");
		return ls;
	}

	public void genExcel(String url, String fileName) throws Exception {
		List<HtmlElement> ls = getFormElements(url);
		export.setXlsFileName(outputPath + fileName+".xls");
		export.createRow(0);
		int i = 0;
		for (HtmlElement ele : ls) {
			String id = ele.getAttribute("id");
			HtmlLabel l = ele.getParentNode().getFirstByXPath("label[@for='" + id + "']");
			export.setCell(i, l.getTextContent());
			i++;
		}
		export.exportXLS();

	}

	public void genXml(String url, String clazz,String fileName) throws Exception {
		textExport.setFileName(outputPath+fileName+".xml");
		textExport.setText(parse2Config(url, clazz));
		textExport.export();
	}

	public void genJava(String url, String clazz) throws Exception {
		textExport.setFileName(outputPath+clazz+".java");
		textExport.setText(parse2Java(url, clazz));
		textExport.export();
			
	}


	public String getPkg() {
		return pkg;
	}


	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	

}
