package com.sp.net.domain;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.util.Assert;


/**
* @author 陈嘉镇
* @version 创建时间：2014-3-28 上午11:53:12
* @email benjaminchen555@gmail.com
*/
public class Form {
	
	private Site site;
	
	private String key;
	
	/**
	 * form所处的url
	 */
	private String url;
	
	
	
	private Map<String, Object> formValueMap;
	
	private List<Rule> rules;
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void perform(Rule rule) throws Exception{
		rule.setForm(this);
		rule.execute();
	}


	public Map<String, Object> getFormValueMap() {
		return formValueMap;
	}

	public void setFormValueMap(Map<String, Object> formValueMap) {
		this.formValueMap = formValueMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public Rule findRule(final String ruleKey) {
		Rule r = (Rule) CollectionUtils.find(rules, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				Rule r = (Rule) object;
				if (ruleKey.equals(r.getKey())) {
					return true;
				}
				return false;
			}
		});
		return r;
	}

	@Override
	public String toString() {
		return "Form [key=" + key + ", url=" + url + ",  formValueMap="
				+ formValueMap + ", rules=" + rules + "]"+hashCode();
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public void perform(String ruleKey) throws Exception {
		Rule r = findRule(ruleKey);
		Assert.notNull(r,"根据‘"+ruleKey+"’，获取不到规则.");
		perform(r);
	}

	public void init() {
		if(this.rules!=null&&this.rules.size()>0){
			for (Rule r : rules) {
				r.setForm(this);
			}
		}
	}
	

}
