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

    @Test
    public void test2() {
        int x = 1;
        System.out.println(x/2.0);
    }

    private static Assigned maybe_swap(Assigned assigned, int x, int y, double T){
        double score_now = assigned.getError();
        int a, b, c, d;
        a=x/(assigned.getAssignedList().length);
        b=x%3;
        c=y/(assigned.getAssignedList().length);
        d=y%3;
        Assigned swapped = new Assigned(assigned);
        swap(swapped, a, b, c, d);
        double score_swapped=swapped.getError();
        if (Math.random() < Math.exp( (score_swapped - score_now) / T ))
            return swapped;
        else
            return assigned;
    }

    private static void swap(Assigned assigned, int a, int b, int c, int d) {
        Student[][] students = assigned.getAssignedList();
        Student temp = students[a][b];
        students[a][b]=students[c][d];
        students[c][d]=temp;
    }
}
