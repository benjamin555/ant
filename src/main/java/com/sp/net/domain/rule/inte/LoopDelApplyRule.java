package com.sp.net.domain.rule.inte;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.domain.Form;
import com.sp.net.domain.Rule;

/**
* @author 陈嘉镇
* @version 创建时间：2014-4-8 下午5:00:53
* @email benjaminchen555@gmail.com
*/
public class LoopDelApplyRule extends Rule {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	protected void fillData(Form form) throws Exception {
		HtmlPage p = form.getSite().getCurrentPage();
		List<DomElement> l =   p.getElementsByName("primaryKey");
		CollectionUtils.filter(l, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				HtmlCheckBoxInput checkBoxInput = (HtmlCheckBoxInput) object;
				return StringUtils.isNoneBlank(checkBoxInput.getValueAttribute());
			}
		});
		if (CollectionUtils.isNotEmpty(l)) {
			HtmlCheckBoxInput checkBoxInput = (HtmlCheckBoxInput) l.get(0);
			logger.info("checkBoxInput:{} click.",checkBoxInput);
			checkBoxInput.click();
		}
	}

	@Override
	protected void submit(Form form) throws Exception {
		HtmlPage p = form.getSite().getCurrentPage();
		String sourceCode = "document.forms[0].action ='prwprapply.cmd?method=delApply&reason=test&processType=ProcessType_abf73323e70';document.forms[0].submit();";
		ScriptResult s =  p.executeJavaScript(sourceCode);
		logger.info("s:{}",s);
	}

}
