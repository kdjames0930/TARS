package com.example;

public class Assigned {
    private Student[][] assigned;
    private double error;

    public Assigned(Student[][] assigned) {
        setStudents(assigned);
        setError();
    }

    public Student[][] getStudents() {
        return assigned;
    }

    public double getError() {
        return error;
    }

    public void setStudents(Student[][] assigned) {
        this.assigned = assigned;
    }

    public void setError() {
        this.error = Error.assigned(assigned);
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < assigned.length; i++) {
            for (int j = 0; j < assigned[i].length; j++) {
                string += assigned[i][j].getName() + "\t";
            }
            string += "\n";
        }
        return string;
    }
}