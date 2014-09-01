package com.sp.net.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.Page;
import com.sp.net.WebClient;
import com.sp.net.command.impl.NewPage;

/**
* @author 陈嘉镇
* @version 创建时间：2014-3-28 上午11:53:46
* @email benjaminchen555@gmail.com
*/
public abstract class Rule {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String key = this.getClass().toString();

	private String name = this.getClass().toString();

	private Form form;
	
	/**
	 * 是否异步
	 */
	private boolean isAsync = false; 

	/**
	 * 是否循环
	 */
	private boolean isLoop;

	/**
	 * 间隔，单位为秒
	 */
	private int interval;

	/**
	 * 异步执行下述操作
	 * @throws Exception
	 */
	public void execute() throws Exception {
		if (isAsync) {
			asynExec();
		}else {
			exec();
		}
		

	}

	/**
	 * 异步执行
	 * @throws Exception
	 * @throws InterruptedException
	 */
	private void asynExec() throws Exception, InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					exec();
				} catch (Exception e) {
					logger.error("error.",e);
				}
			}

			
		}).start();

	}
	
	
	private void exec() throws Exception {
		do {
			
			turn2Page();
			
			fillData(form);
			logger.info("fillData done.");
			submit(form);
			logger.info("submit done.");
			//检查是否执行成功
			check(form);
			logger.info("check done.");

			logger.info("isLoop:{}", isLoop);
			if (isLoop) {
				Thread.sleep(interval * 1000);
				logger.info("sleep done.");
			}
		} while (isLoop);
		logger.info("execute done.");
	}

	public Page turn2Page() throws Exception {
		WebClient webClient = form.getSite().getWebClient();
		NewPage nPage = new NewPage(form.getUrl(), webClient);
		logger.info("form:{}",form);
		webClient.perform(nPage);
		return webClient.getCurrentPage();
	}

	protected void check(Form form2) throws Exception {
	};

	protected abstract void fillData(Form form) throws Exception;

	protected abstract void submit(Form form) throws Exception;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		logger.info("form:{}",form);
		this.form = form;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isLoop() {
		return isLoop;
	}

	public void setLoop(boolean isLoop) {
		this.isLoop = isLoop;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public boolean isAsync() {
		return isAsync;
	}

	public void setAsync(boolean isAsync) {
		this.isAsync = isAsync;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	

}
