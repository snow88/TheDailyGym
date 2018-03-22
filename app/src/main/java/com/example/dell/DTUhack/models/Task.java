package com.example.dell.DTUhack.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey
    int id;
    String name;
    String desc;
    int imgdrawable;
    String category;
    String goal;

    public Task(int id, String name, String desc, int imgdrawable, String category, String goal) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imgdrawable = imgdrawable;
        this.category = category;
        this.goal = goal;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImgdrawable() {
        return imgdrawable;
    }

    public void setImgdrawable(int imgdrawable) {
        this.imgdrawable = imgdrawable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
