package com.example.taifa.last;

import java.io.Serializable;

public class TimetableContract implements Serializable {
    String unitName;
    String unitCode;
    String unitVenue;
    String unitDay;

    public TimetableContract() {

    }

    public TimetableContract(String unitName, String unitCode, String unitVenue, String unitTime, String lectureName,String unitDay) {
        this.unitName = unitName;
        this.unitCode = unitCode;
        this.unitVenue = unitVenue;
        this.unitTime = unitTime;
        this.lectureName = lectureName;
        this.unitDay = unitDay;
    }

    public String getUnitDay() {
        return unitDay;
    }

    public void setUnitDay(String unitDay) {
        this.unitDay = unitDay;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitVenue() {
        return unitVenue;
    }

    public void setUnitVenue(String unitVenue) {
        this.unitVenue = unitVenue;
    }

    public String getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(String unitTime) {
        this.unitTime = unitTime;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    String unitTime;
    String lectureName;
}
