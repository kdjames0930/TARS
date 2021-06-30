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

    public Data(String filePath) throws IOException{
        setList(filePath);
    }

    public void setList(String filePath) throws IOException {
        importFile(filePath);
        importData();
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public Student[] getList() {
        return list;
    }

    public int getStudentNum() {
        return studentNum;
    }
    
    
    private void importFile(String filePath) throws IOException {
        System.out.print("password: ");
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

    public Student[][] assign(){ // TODO - Assign 클래스로 옮기기
        double limit2=0.9;
        double limit3=0.9;

        QuickSort.desSort(list);

        int[][] room=new int[30][3];
        int roomcount=0;
        room[0][0]=0;
        int[] pcount=new int[30];
        pcount[0]=1;
        for(int i=1; i<list.length; i++){
            double errors[]=new double[30];
            int bestroom=31;
            double besterror=999.9;
            for(int j=0; j<=roomcount; j++){
                if(pcount[j]!=3){
                    if(pcount[j]==1){
                        errors[j]=Error.couple(list[room[j][0]], list[i]);
                        if(errors[j]<besterror) {
                            bestroom=j;
                            besterror = errors[j];
                        }
                    }
                    if(pcount[j]==2){
                        errors[j]=Error.room(list[room[j][0]], list[room[j][1]], list[i]);
                        if(errors[j]<besterror) {
                            bestroom=j;
                            besterror = errors[j];
                        }
                    }
                }
            }
            if(bestroom==31){
                room[roomcount+1][0]=i;
                pcount[roomcount+1]=1;
                roomcount++;
            }
            else if(pcount[bestroom]==1 && errors[bestroom]<limit2){
                room[bestroom][1]=i;
                pcount[bestroom]=2;
            }
            else if(pcount[bestroom]==2 && errors[bestroom]<limit3){
                room[bestroom][2]=i;
                pcount[bestroom]=3;
            }
            else{
                room[roomcount+1][0]=i;
                pcount[roomcount+1]=1;
                roomcount++;
            }
            System.out.println(besterror);
        }
        Student[][] room2=new Student[30][3];
        for(int i=0; i<30; i++){
            for(int j=0; j<pcount[i]; j++){
                room2[i][j]=list[room[i][j]];
            }
        }
        ///*
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                System.out.print(room[i][j]+" ");
            }
            System.out.println();
        }
        //*/

        return room2;
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
