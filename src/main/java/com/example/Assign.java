package com.example;

public class Assign { // TODO - 학년별 assign 방법 고려하기
    public static double score_now;
    public static double score_swapped;
    public static Student[][] assignedList;
    public static Student temp;
    public static double dif;

    public static Assigned freshmanAssign(Data data) {
        Assigned assigned = firstAssign(data);
        simulatedAnnealing(assigned);

        return assigned;
    }

    public static Assigned commonAssign(Data data) {
        Assigned assigned = firstAssign(data);
        simulatedAnnealing(assigned);
        considerBlack(assigned);

        return assigned;
    }

    public static Assigned thirdGradeAssign(Data data) {
        ///*
        Assigned assigned = firstAssign(data);
        simulatedAnnealing(assigned);
        considerBlack(assigned);
        //*/
        // considerWhite();

        return assigned;
    }

    public static Assigned firstAssign(Data data) { // TODO - 재현이가 짠 assign 메서드 옮기기
        Student[] list = data.getList();
        int roomNum = list.length/3;
        Student[][] assigned = new Student[roomNum][3];
        int[][] room=new int[roomNum][3];

        QuickSort.desSort(list);

        double limit2=0.9;
        double limit3=0.9;

        int roomcount=0;
        room[0][0]=0;
        int[] pcount=new int[roomNum];
        pcount[0]=1;
        double errors[]=new double[roomNum];
        int bestroom=roomNum+1;
        double besterror=999.9;
        for(int i=1; i<list.length; i++){
            bestroom=roomNum+1;
            besterror=999.9;
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
        }
        for(int i=0; i<roomNum; i++){
            for(int j=0; j<3; j++){
                assigned[i][j]=list[room[i][j]];
            }
        }
        /*
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                System.out.print(room[i][j]+" ");
            }
            System.out.println();
        }
        //*/

        return new Assigned(assigned, data);
    }
    
    public static void simulatedAnnealing(Assigned assigned) {
        int n = assigned.getList().length;
        int idx1, idx2;
        int x1, y1, x2, y2;
        for (int i = 400; i > 0; i--) {
            for (int j = 0; j < n*n; j++) {
                idx1 = (int)(Math.random()*n);
                idx2 = (int)(Math.random()*n);
                if(idx1 != idx2){
                    x1 = idx1/3;
                    y1 = idx1%3;
                    x2 = idx2/3;
                    y2 = idx2%3;
                    assigned = maybe_swap(assigned, x1, y1, x2, y2, i/100.0);
                }
            }
            if(i%20==0) System.out.println(assigned.getError());
        }
    }
    public static void considerBlack(Assigned assigned) {
        // TODO - 블랙 리스트를 고려한 방배정 => 양한얼
    }

    public static void considerWhite() {
        // 화이트 리스트를 고려한 방배정
    }
    
    private static Assigned maybe_swap(Assigned assigned, int x1, int y1, int x2, int y2, double T){
        score_now = assigned.getError();
        assigned.swap(x1, y1, x2, y2);
        score_swapped = assigned.getError();
        dif = score_swapped - score_now;
        if (Math.random() < Math.exp( dif / T ) || dif < 0)
            return assigned;
        else{
            assigned.swap(x1, y1, x2, y2);
            return assigned;
        }
    }
}