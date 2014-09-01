package com.sp.net.command.cnooc;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sp.net.WebClient;
import com.sp.net.command.Command;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-27 下午6:41:04
* @email benjaminchen555@gmail.com
*/
public class SubmitApply implements Command{
	private String applyNO;
	private WebClient webClient;
	public SubmitApply(String applyNO,WebClient webClient) {
		this.applyNO= applyNO;
		this.webClient = webClient;
	}

	@Override
	public void execute() throws Exception {
		
		HtmlPage currentPage = webClient.getCurrentPage();
		HtmlForm  f = currentPage.getFormByName("list");
		
		fillData(f);
		
		submit(f);
	}

	private void fillData(HtmlForm f) throws IOException {
		List<HtmlInput>  ls = f.getInputsByName("ids");
		 CollectionUtils.filter(ls, new Predicate() {
			public boolean evaluate(Object object) {
				HtmlInput htmlInput = (HtmlInput)object;
				DomNode td = htmlInput.getParentNode().getNextSibling();
				if (td==null) {
					return false;
				}
				return applyNO.equals(td.asText());
			}
		});
		 
		 HtmlInput i = ls.get(0);
		 i.click();
	}

	private void submit(HtmlForm f) throws IOException {
		HtmlButton b =  f.getFirstByXPath("//button[@id='but_req']");
		 
		 b.click();
	}

}
