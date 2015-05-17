package com.sp.net.gen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

/**
* @author 陈嘉镇
* @version 创建时间：2015-5-17 上午11:04:44
*/
@Component
public class TextExport {

	private String text;
	private String fileName;

	public void export() throws Exception {
		FileOutputStream fOut = null;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			fOut = new FileOutputStream(fileName);
			fOut.write(text.getBytes());
			fOut.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new RuntimeException(" 写入Excel文件出错! ", e);
		} finally {
			if (fOut != null) {
				fOut.close();
			}
		}

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
