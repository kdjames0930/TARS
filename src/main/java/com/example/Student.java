package com.example;

public class Student {
    private String sex;
    private int grade;
	private String name;
	private int[] q;
    private int [] self;
    private int [] roomie;
    private double strictness;

    public Student() { // 3의 배수 맞추기 위한 투명 학생(공석)
        sex = "-";
        grade = 0;
        name = "공 석";
        q = new int[]{0,0,0,0,0,0,0,0,0,0};
        setSelf(q);
        setRoomie(q);
        setStrictness();
    }

    public Student(String[] cellArr){ //한 row의 cell들로 이루어진 array을 인자로 받고 sex, grade, name, q, self, roomie에 각각 값을 대입
        cellArr = refineCellArr(cellArr);
        setSex(cellArr);
        setGrade(cellArr);
        setName(cellArr);
        setQ(cellArr);
        setSelf(q);
        setRoomie(q);
        setStrictness();
    }

    public String getSex() {
        return sex;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public int[] getQ() {
        return q;
    }

    public int[] getRoomie() {
        return roomie;
    }

    public int[] getSelf() {
        return self;
    }

    public double getStrictness() {
        return strictness;
    }

    public void setSex(String[] cellArr) {
        this.sex = cellArr[0].equals("ㄴ")?"남":"여";
    }

    public void setGrade(String[] cellArr) {
        this.grade = Integer.parseInt(cellArr[1]);
    }

    public void setName(String[] cellArr) {
        this.name = cellArr[2];
    }

    public void setQ(String[] cellArr) {
        int[] q = new int[10];
        for (int i = 0; i < q.length; i++) {
            q[i] = Integer.parseInt(cellArr[i+3]);
        }
        this.q = q;
    }

    public void setSelf(int[] q) {
        int[] self = new int[5];
        for (int i = 0; i < self.length; i++) {
            self[i] = q[i];
        }
        this.self = self;
    }
    
    public void setRoomie(int[] q) {
        int[] roomie = new int[5];
        for (int i = 0; i < roomie.length; i++) {
            roomie[i] = q[i+5];
        }
        this.roomie = roomie;
    }

    private void setStrictness() {
        double strictness = .0;
        for (int i = 0; i < roomie.length; i++) {
            strictness += roomie[i];
        }
        this.strictness = strictness/5;
    }

    private String[] refineCellArr(String[] cellArr) { // cellArr의 String을 int로 바꿈
        cellArr[3] = cellArr[3].equals("조용한 분위기")?"0":"1";
        cellArr[4] = cellArr[4].equals("2자 중")?"0":cellArr[4].equals("12시 이후 1시 전")?"1"
        :cellArr[4].equals("1시 이후 2시 이전")?"2"
        :"3";
        cellArr[5] = cellArr[5].equals("모든 노래 및 게임소리는 이어폰을 사용함. 생활 소음 뿐임.")?"0":"1";
        cellArr[6] = cellArr[6].equals("3명 중 한 명이라도 잔다면 소등을 해야함")?"0"
        :cellArr[6].equals("3명 중 두 명이 잔다면 소등을 해야함")?"1"
        :cellArr[6].equals("한 명이라도 자지 않으면 불을 끄지 말아야함.")?"2"
        :"3";
        cellArr[7] = cellArr[7].equals("꽤 하는 편")?"0":cellArr[7].equals("평균")?"1":"2";

        return cellArr;
    }

    @Override
    public String toString() { // student의 정보를 (학생 이름): (데이터) 형식의 string으로 반환
        String qString = new String("");
        for (int i = 0; i < this.q.length-1; i++) {
            qString += this.q[i]+", ";
        }
        qString += this.q[this.q.length-1];
        return name+" => "+grade+"학년, "+sex+", "+"q: "+qString;
    }
}