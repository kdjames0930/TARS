package com.example;

/**
 * SimulatedAnnealing
 */
public class Assign { // TODO - 학년별 assign 방법 고려하기
    public static void freshmanAssign(Data data) {
        firstAssign(data);
        simulatedAnnealing();
    }

    public static void commonAssign(Data data) {
        firstAssign(data);
        simulatedAnnealing();
        considerBlack();
    }

    public static void thirdGradeAssign(Data data) {
        ///*
        firstAssign(data);
        simulatedAnnealing();
        considerBlack();
        //*/
        // considerWhite();
    }

    public static void firstAssign(Data data) { // TODO - 재현이가 짠 assign 메서드 옮기기
        Student[] list = data.getList();
        Student[][] assigned = new Student[list.length/3][3];
        
        QuickSort.desSort(list);
    }

    public static void simulatedAnnealing() {
        // TODO - 담금질 기법 메서드 구현 => 김재현
    }
    
    public static void considerBlack() {
        // TODO - 블랙 리스트를 고려한 방배정 => 양한얼
    }

    private static void considerWhite() {
        // 화이트 리스트를 고려한 방배정
    }
}