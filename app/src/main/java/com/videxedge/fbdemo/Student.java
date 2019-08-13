package com.videxedge.fbdemo;

public class Student {

    private int gradeClass, stuClass;
    private String stuName, stuID;

    public Student (){}
    public Student (int gradeClass, int stuClass, String stuName, String stuID) {
        this.gradeClass=gradeClass;
        this.stuClass=stuClass;
        this.stuName=stuName;
        this.stuID=stuID;
    }

    public int getgradeClass() {
        return gradeClass;
    }

    public void setgradeClass(int gradeClass) {
        this.gradeClass=gradeClass;
    }

    public int getstuClass() {
        return stuClass;
    }

    public void setstuClass(int stuClass) {
        this.stuClass=stuClass;
    }

    public String getstuName() {
        return stuName;
    }

    public void setstuName(String stuName) {
        this.stuName=stuName;
    }

    public String getstuID() {
        return stuID;
    }

    public void setstuID(String stuID) {
        this.stuID=stuID;
    }
}
