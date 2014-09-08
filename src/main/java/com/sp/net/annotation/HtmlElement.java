package com.sp.net.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author 陈嘉镇
* @version 创建时间：2014-9-5 上午9:52:39
* @email benjaminchen555@gmail.com
*/
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlElement {
	
	 String xpath() default "";
	

}
