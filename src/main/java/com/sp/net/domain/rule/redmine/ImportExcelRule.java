package com.sp.net.domain.rule.redmine;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.sp.net.annotation.HtmlElement;
import com.sp.net.domain.Form;
import com.sp.net.domain.Rule;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-31 上午10:35:02
* @email benjaminchen555@gmail.com
*/
public class ImportExcelRule extends Rule{

	@Override
	protected void fillData(Form form) throws Exception {
		getLogger().info("fillData:{}",form);
		HtmlPage currentPage = form.getSite().getCurrentPage();
		Object case1 =  form.getFormValueMap().get("data");
		
		HtmlForm f = currentPage.getElementById("issue-form", false);
		
		Field[] fs = case1.getClass().getDeclaredFields();
		for (Field field : fs) {
			
			HtmlElement  tElement = field.getAnnotation(HtmlElement.class);
			if(tElement==null){
				continue;
			}
			String xpath = tElement.xpath();
			getLogger().info("xpath:{}",xpath);
			
			Object  el= f.getByXPath(xpath).get(0);
			getLogger().info("el:{}",el);
			if (el instanceof com.gargoylesoftware.htmlunit.html.HtmlElement) {
				com.gargoylesoftware.htmlunit.html.HtmlElement htmlElement = (com.gargoylesoftware.htmlunit.html.HtmlElement)el;
				field.setAccessible(true);
				String value =  field.get(case1).toString();
			
				
				
				
				if (StringUtils.isNoneBlank(value)) {
					getLogger().info("value:{}",value);
					if (htmlElement instanceof HtmlSelect) {
						HtmlSelect s = (HtmlSelect)htmlElement;
						s.setSelectedAttribute(value, true);
					}if(htmlElement instanceof HtmlTextArea){
						HtmlTextArea t= (HtmlTextArea)htmlElement;
						t.setText(value);
					}
					else {
						htmlElement.setAttribute("value", value);
					}
					
					
				}
				
			}
		}
		

		
	}

	@Override
	protected void submit(Form form) throws Exception {
		getLogger().info("submit:{}",form);
		HtmlPage currentPage = form.getSite().getCurrentPage();
		HtmlForm f = currentPage.getElementById("issue-form", false);
		HtmlInput b = f.getFirstByXPath("//input[@name='commit']");
		HtmlPage main = (HtmlPage) b.click();
		getLogger().info(main.asText());
		Assert.isTrue(main.asText().contains("在 一分钟内 之前添加"));
		
	}

}
