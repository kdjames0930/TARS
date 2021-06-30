package com.example;

public class Error {

    public static double couple (Student s1, Student s2) {
        Student[] s = new Student[]{s1, s2};
        int [] a = new int[5];
        double [] error = new double[2];
        for(int i=0;i<5;i++) {
            if(s1.getSelf()[i]!=s2.getSelf()[i]) a[i]=1;
        }
        for(int i=0;i<2;i++) {
            for (int j=0;j<5;j++) {
                error[i]+=a[j]*s[i].getRoomie()[j];
            }
            error[i]=error[i]/5;
        }
        return (error[0]+error[1])/2;
    }

    public static double room (Student s1, Student s2, Student s3) {
        double c12 = couple(s1, s2);
        double c23 = couple(s2,s3);
        double c31 = couple(s3, s1);
        return (c12 + c23 + c31)/3;
    }

    public static double assigned (Student[][] assigned) {
        double assignedError = .0;
        for (int i = 0; i < assigned.length; i++) {
            assignedError += room(assigned[i][0], assigned[i][1], assigned[i][2]);
        }
        return assignedError/assigned.length;
    }
}
