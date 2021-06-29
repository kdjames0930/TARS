package com.example;

public class App {
    public static void main(String[] args) throws Exception {
        String filePath = "C:/src/demo/src/resources/data.xlsx";
        Data data = new Data(filePath);
        System.out.println(data);
        Assigned assigned = new Assigned(data.assign());
        System.out.println(assigned);
        System.out.println("전체 에러: "+assigned.getError());
    }
}

