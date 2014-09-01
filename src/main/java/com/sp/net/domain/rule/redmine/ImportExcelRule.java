package com.sp.net.domain.rule.redmine;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
		Case case1 = (Case) form.getFormValueMap().get("data");
		
		HtmlForm f = currentPage.getElementById("issue-form", false);
		
		
		f.getSelectByName("issue[tracker_id]").setSelectedAttribute("5", true);
		f.getInputByName("issue[subject]").setValueAttribute("testImprot");
		f.getInputByName("issue[custom_field_values][1]").setValueAttribute(case1.getNo());
		String date = case1.getDate();
		f.getInputByName("issue[custom_field_values][2]").setValueAttribute(date);
		f.getInputByName("issue[custom_field_values][3]").setValueAttribute(case1.getApplier());
		f.getSelectByName("issue[custom_field_values][4]").setSelectedAttribute(case1.getCaseType(), true);

		
	}

	@Override
	protected void submit(Form form) throws Exception {
		getLogger().info("submit:{}",form);
		HtmlPage currentPage = form.getSite().getCurrentPage();
		HtmlForm f = currentPage.getElementById("issue-form", false);
		HtmlInput b = f.getFirstByXPath("//input[@name='commit']");
		b.click();
	}

}
