package com.sp.net.gen;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* @author 陈嘉镇
* @version 创建时间：2015-5-17 上午10:17:20
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-site.xml" })
public class XLSExportTest {
	@Autowired
	private XLSExport e;
	@Test
	public void testExport() throws Exception {
		System.out.println( " 开始导出Excel文件 " );
		e.setXlsFileName("d:/test.xls");
        e.createRow( 0 );
        e.setCell( 0 ,  " 编号 " );
        e.setCell( 1 ,  " 名称 " );
        e.setCell( 2 ,  " 日期 " );
        e.setCell( 3 ,  " 金额 " );
        e.createRow( 1 );
        e.setCell( 0 ,  1 );
        e.setCell( 1 ,  " 工商银行 " );
        e.setCell( 2 , Calendar.getInstance());
        e.setCell( 3 ,  111123.99 );
        e.createRow( 2 );
        e.setCell( 0 ,  2 );
        e.setCell( 1 ,  " 招商银行 " );
        e.setCell( 2 , Calendar.getInstance());
        e.setCell( 3 ,  222456.88 );
         try    {
            e.exportXLS();
            System.out.println( " 导出Excel文件[成功] " );
        }   catch  (Exception e1)   {
            System.out.println( " 导出Excel文件[失败] " );
            e1.printStackTrace();
        } 

	}

}
