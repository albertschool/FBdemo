package com.videxedge.fbdemo;

public class StuGrade {

    private String StuID;
    private String ClassSubject;
    private String GradeType;
    private int FinalGrade;

    public StuGrade (String StuID, String ClassSubject, String GradeType, int FinalGrade) {
        this.StuID=StuID;
        this.ClassSubject=ClassSubject;
        this.GradeType=GradeType;
        this.FinalGrade=FinalGrade;
    }

    public String getStuID() {
        return StuID;
    }

    public void setStuID(String StuID) {
        this.StuID=StuID;
    }

    public String getClassSubject() {
        return ClassSubject;
    }

    public void setClassSubject(String ClassSubject) {
        this.ClassSubject=ClassSubject;
    }

    public String getGradeType() {
        return GradeType;
    }

    public void setGradeType(String GradeType) {
        this.GradeType=GradeType;
    }

    public int getFinalGrade() {
        return FinalGrade;
    }

    public void setFinalGrade(int FinalGrade) {
        this.FinalGrade=FinalGrade;
    }
}
