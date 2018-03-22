package com.example.dell.DTUhack.models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey
    int id;
    String name;
    String dob;
    int gender;
    int lifestyle;
    double bmi;

    public Person() {

    }

    public Person(int id, String name, String dob, int gender, int lifestyle, float bmi) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.lifestyle = lifestyle;
        this.bmi = bmi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(int lifestyle) {
        this.lifestyle = lifestyle;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
