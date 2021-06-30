package com.example;

public class App {
    public static void main(String[] args) throws Exception {
        String filePath = "C:/src/demo/src/resources/data.xlsx";
        Data data = new Data(filePath);
        Assigned assigned = Assign.firstAssign(data);
        System.out.println(assigned);
        System.out.println("전체 에러: "+assigned.getError());
        errorTest(assigned);
        // Assign.simulatedAnnealing(assigned);
        // System.out.println(assigned);
        // System.out.println("전체 에러: "+assigned.getError());
    }

    public static void errorTest(Assigned assigned) {
        double error_now = assigned.getError();
        double error_swapped;
        int idx1, idx2;
        int x1, y1, x2, y2;
        double totalError = 0;
        int n = 0;
        for (int i = 0; i < 100; i++) {
            idx1 = (int)(Math.random()*30);
            idx2 = (int)(Math.random()*30);
            if(idx1 != idx2){
                x1 = idx1/3;
                y1 = idx1%3;
                x2 = idx2/3;
                y2 = idx2%3;
                assigned.swap(x1, y1, x2, y2);
                error_swapped = assigned.getError();
                totalError += error_swapped - error_now;
                n++;
                System.out.println(error_swapped - error_now);
                error_now = error_swapped;
            }
        }
        System.out.println(totalError/n);
        System.out.println(assigned.getError());
    }
}

