package com.example;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	XSSFWorkbook workbook;
	XSSFSheet sheet;

    /**
     * Rigorous Test :-)
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String filePath = "C:/src/demo/src/resources/data.xlsx";
        importFile(filePath);
        importData();
    }

    public void importFile(String filePath) throws IOException {
        workbook = new XSSFWorkbook(filePath);
        sheet = workbook.getSheetAt(0);
    }

    private void importData() {
        int stNum = 0;
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            System.out.println(row.getCell(3).getStringCellValue());
            stNum++;
        }
        System.out.println(stNum);
    }
}
