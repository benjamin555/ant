package com.sp.net.domain.rule.inte;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
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
* 尾数为某个值的采办需求进行提交
*/
public class SuffixInputRule extends Rule {
	private Logger logger = LoggerFactory.getLogger(SuffixInputRule.class);
	@Override
	protected void fillData(Form form) throws Exception {
		logger.info("fillData:{}",form);
		HtmlPage currentPage = form.getSite().getCurrentPage();
		HtmlForm f = currentPage.getFormByName("list");
		final String suffix = (String) form.getFormValueMap().get("suffix");
		List<HtmlInput> ls = f.getInputsByName("ids");
		CollectionUtils.filter(ls, new Predicate() {
			public boolean evaluate(Object object) {
				HtmlInput htmlInput = (HtmlInput) object;
				DomNode td = htmlInput.getParentNode().getNextSibling();
				if (td == null) {
					return false;
				}
				if (StringUtils.isNoneBlank(td.asText())) {
					int length = td.asText().length();
					String last =  td.asText();
					if (suffix.length()<=length) {
						 last = td.asText().substring(length - suffix.length());
					}
					return suffix.equals(last);
				}
				return false;
			}
		});

		for (HtmlInput htmlInput : ls) {
			htmlInput.click();
		}
		
	}

	@Override
	protected void submit(Form form) throws Exception {
		logger.info("submit:{}",form);
		HtmlPage currentPage = form.getSite().getCurrentPage();
		HtmlForm f = currentPage.getFormByName("list");
		HtmlButton b = f.getFirstByXPath("//button[@id='but_req']");
		b.click();

	}

}
