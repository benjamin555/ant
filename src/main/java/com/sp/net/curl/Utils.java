package com.sp.net.curl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
* @author 陈嘉镇
* @version 创建时间：2014-12-8 上午11:05:32
* @email benjaminchen555@gmail.com
*/
public class Utils {

	public static boolean isEmptyString(String method) {
		return StringUtils.isEmpty(method);
	}

	public static boolean isEmptySafe(Map<String, String> paramMap) {
		return MapUtils.isEmpty(paramMap);
	}


	public static boolean isEmptySafe(List<String> cookieList) {
		return CollectionUtils.isEmpty(cookieList);
	}

}
