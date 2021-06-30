package com.example;

public class Assigned {
    private Student[] list;
    private int studentNum = 0;
    private Student[][] assignedList;
    private double error;
    private Student temp;

    public Assigned(Assigned assigned) {
        setList(assigned.getList());
        setStudentNum(assigned.getStudentNum());
        setAssignedList(assigned.getAssignedList());
        setError();
    }

    public Assigned(Student[][] assignedList, Data data) {
        setList(data.getList());
        setStudentNum(data.getStudentNum());
        setAssignedList(assignedList);
        setError();
    }
    
    public void swap(int x1, int y1, int x2, int y2) {
        temp = assignedList[x1][y1];
        assignedList[x1][y1]=assignedList[x2][y2];
        assignedList[x2][y2]=temp;
    }

    public Student[] getList() {
        return list;
    }
    
    public int getStudentNum() {
        return studentNum;
    }

    public Student[][] getAssignedList() {
        return assignedList;
    }

    public double getError() {
        setError();
        return error;
    }

    public void setList(Student[] list) {
        this.list = list;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public void setAssignedList(Student[][] assignedList) {
        this.assignedList = assignedList;
    }

    public void setError() {
        this.error = Error.assigned(this);
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < assignedList.length; i++) {
            for (int j = 0; j < assignedList[i].length; j++) {
                string += assignedList[i][j].getName() + "\t";
            }
            string += "\n";
        }
        return string;
    }
}