package com.sp.net.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sp.net.domain.Site;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-28 下午12:02:53
* @email benjaminchen555@gmail.com
*/
public class SiteFactory {
	
	private static Map<String,Site> context = new HashMap<String, Site>();
	
	static{
//		String url = "http://10.63.2.111:8090/integration";
//		Site inteSite = new Site(url);
//		List<Form> forms = new ArrayList<Form>();
//		Form f = new Form();
//		f.setKey("applyList");
//		f.setUrl("http://10.63.2.111:8090/integration/pr/central/requisition/list.do");
//		f.setName("list");
//		forms.add(f);
//		inteSite.setForms(forms);
//		context.put(url, inteSite);
		
	}

	public static Site getSite(String url) {
		url=StringUtils.prependIfMissing(url, "http://", "http://");
		return context.get(url);
	}

}
