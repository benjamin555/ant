package com.sp.net.domain.rule.inte;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.domain.Form;
import com.sp.net.domain.Rule;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-28 下午3:15:07
* @email benjaminchen555@gmail.com
*/
public class SingleInputRule extends Rule {
	private Logger logger = LoggerFactory.getLogger(SingleInputRule.class);

	@Override
	protected void fillData(Form form) throws Exception {
		logger.info("fillData:{}", form);
		HtmlPage currentPage = form.getSite().getCurrentPage();
		HtmlForm f = currentPage.getFormByName("list");
		final String applyNO = (String) form.getFormValueMap().get("applyNO");
		List<HtmlInput> ls = f.getInputsByName("ids");
		CollectionUtils.filter(ls, new Predicate() {
			public boolean evaluate(Object object) {
				HtmlInput htmlInput = (HtmlInput) object;
				DomNode td = htmlInput.getParentNode().getNextSibling();
				if (td == null) {
					return false;
				}
				return applyNO.equals(td.asText());
			}
		});

		HtmlInput i = ls.get(0);
		i.click();

	}

	@Override
	protected void submit(Form form) throws Exception {
		logger.info("submit:{}", form);
		HtmlPage currentPage = form.getSite().getCurrentPage();
		HtmlForm f = currentPage.getFormByName("list");
		HtmlButton b = f.getFirstByXPath("//button[@id='but_req']");
		b.click();

	}

}
