package com.sp.net.gen;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
* @author 陈嘉镇
* @version 创建时间：2014-12-8 下午5:07:18
* @email benjaminchen555@gmail.com
*/
@Service
public class FreeMarkerService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@SuppressWarnings("rawtypes")
	public String populateTempalte(String template, Map<String,Object> map) {
		String content = null;
		try {
			Template tpl = getTemp(template);
			content = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
		} catch (IOException e) {
			log.error("封装FreeMarker内容出现异常", e);
		} catch (TemplateException e) {
			log.error("封装FreeMarker内容出现异常", e);
		}
		return content;
	}
	
	protected Template getTemp(String tempFile) throws IOException {
		Configuration cfg = getConfig();
		Template temp = cfg.getTemplate(tempFile);
		return temp;
	}
	
	protected Configuration getConfig() throws IOException {
		Configuration cfg = new Configuration();

		// Specify the data source where the template files come from. Here I set a
		// plain directory for it, but non-file-system are possible too:
		
		String path = getClass().getClassLoader().getResource("templates").getPath();
		cfg.setDirectoryForTemplateLoading(new File(path));

		// Specify how templates will see the data-model. This is an advanced topic...
		// for now just use this:
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		// Set your preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear. Here we assume we are developing HTML pages.
		// For production systems TemplateExceptionHandler.RETHROW_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		// At least in new projects, specify that you want the fixes that aren't
		// 100% backward compatible too (these are very low-risk changes as far as the
		// 1st and 2nd version number remains):
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));  // FreeMarker 2.3.20\
		return cfg;
	}


}
