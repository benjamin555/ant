package com.sp.net.gen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

/** */
/** 
* 生成导出Excel文件对象
* 
*  @author  John.Zhu
* 
*/
@Component
public class XLSExport {

	//  定制日期格式 
	private static String DATE_FORMAT = " m/d/yy "; //  "m/d/yy h:mm"

	//  定制浮点数格式 
	private static String NUMBER_FORMAT = " #,##0.00 ";

	private String xlsFileName;

	private HSSFWorkbook workbook;

	private HSSFSheet sheet;

	private HSSFRow row;
	
	

	public XLSExport() {
		super();
		init();
	}

	/** */
	/** 
	* 初始化Excel
	* 
	*  @param  fileName
	*            导出文件名
	*/
	public XLSExport(String fileName) {
		this.xlsFileName = fileName;
		init();
	}

	
	protected void init() {
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet();
	}

	/** */
	/** 
	* 导出Excel文件
	* 
	*  @throws  XLSException
	*/
	public void exportXLS() throws Exception {
		FileOutputStream fOut =null;
		try {
			File file = new File(xlsFileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			 fOut = new FileOutputStream(xlsFileName);
			workbook.write(fOut);
			fOut.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new RuntimeException(" 写入Excel文件出错! ", e);
		}finally{
			if (fOut!=null) {
				fOut.close();
			}
		}

	}

	/** */
	/** 
	* 增加一行
	* 
	*  @param  index
	*            行号
	*/
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}

	/** */
	/** 
	* 设置单元格
	* 
	*  @param  index
	*            列号
	*  @param  value
	*            单元格填充值
	*/
	public void setCell(int index, String value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	/** */
	/** 
	* 设置单元格
	* 
	*  @param  index
	*            列号
	*  @param  value
	*            单元格填充值
	*/
	public void setCell(int index, Calendar value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellValue(value.getTime());
		HSSFCellStyle cellStyle = workbook.createCellStyle(); //  建立新的cell样式 
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT)); //  设置cell样式为定制的日期格式 
		cell.setCellStyle(cellStyle); //  设置该cell日期的显示格式 
	}

	/** */
	/** 
	* 设置单元格
	* 
	*  @param  index
	*            列号
	*  @param  value
	*            单元格填充值
	*/
	public void setCell(int index, int value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	/** */
	/** 
	* 设置单元格
	* 
	*  @param  index
	*            列号
	*  @param  value
	*            单元格填充值
	*/
	public void setCell(int index, double value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		HSSFCellStyle cellStyle = workbook.createCellStyle(); //  建立新的cell样式 
		HSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); //  设置cell样式为定制的浮点数格式 
		cell.setCellStyle(cellStyle); //  设置该cell浮点数的显示格式 
	}

	public String getXlsFileName() {
		return xlsFileName;
	}

	public void setXlsFileName(String xlsFileName) {
		this.xlsFileName = xlsFileName;
	}

}