package com.example.demo.service;

import java.util.Calendar;

public class People {
    private String name;
    private int birthYear;

    public People(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);

        return thisYear - birthYear + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
