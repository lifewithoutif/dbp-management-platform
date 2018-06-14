package com.hna.dbp.test;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
* 创建一个excel
* */
public class PoiWriteDemo {

    public static void main(String[] args) throws IOException {
        //创建工作簿
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("sheet1");
        for (int row = 0; row < 10; row++) {
            HSSFRow rows = sheet.createRow(row);
            for (int col = 0; col < 10; col++) {
                // 向工作表中添加数据
                rows.createCell(col).setCellValue("data" + row + col);
            }
        }
        File file = new File("c:\\poi.xls");
        FileOutputStream fs = new FileOutputStream(file);
        hssfWorkbook.write(fs);
    }
}
