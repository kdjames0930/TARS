package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import java.util.*;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data {
    private Student[] list;
    private int studentNum = 0;
    private Scanner sc = new Scanner(System.in);

    FileInputStream file;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

    public Data(Data data) {
        setList(data.getList());
        setStudentNum(data.getStudentNum());
        setFile(data.getFile());
        setWorkbook((data.getWorkbook()));
        setSheet(data.getSheet());
    }

    public Data(String filePath) throws IOException{
        setList(filePath);
    }
    
    
    private void importFile(String filePath) throws IOException {
        // System.out.print("password: ");
        workbook = (XSSFWorkbook) WorkbookFactory.create(new File(filePath), sc.next(), true);
        sheet = workbook.getSheetAt(0);
    }
    
    private void importData() {
        int stNum = 0;
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()){
            rowIterator.next();
            stNum++;
        }
        stNum--;
        setStudentNum(stNum);

        list = new Student[(stNum/3)*3+3];
        String[] cellArr = new String[13];
        String val;
        DataFormatter formatter = new DataFormatter();

        for(int i=0 ; i<stNum ; i++) {
            for(int j=0 ; j<13 ; j++) {
                val = formatter.formatCellValue(sheet.getRow(i+1).getCell(j));
                cellArr[j] = val;
            }
            list[i] = new Student(cellArr);
        }
        
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null)
                list[i] = new Student();
        }
    }

    public void setFile(FileInputStream file) {
        this.file = file;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public void setList(Student[] list) {
        this.list = list;
    }

    public void setList(String filePath) throws IOException {
        importFile(filePath);
        importData();
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public FileInputStream getFile() {
        return file;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public Student[] getList() {
        return list;
    }

    public int getStudentNum() {
        return studentNum;
    }

    @Override
    public String toString() { // 입력받은 데이터를 (학생 이름): (데이터) 형식의 string으로 반환
        String string = new String();
        Student student;
        for (int i = 0; i < list.length; i++) {
            student = list[i];
            string += i+". "+student+"\n";
        }
        return string;
    }
}
