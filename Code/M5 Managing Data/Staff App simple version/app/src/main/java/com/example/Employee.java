package com.example;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Employee implements Serializable {
    @Exclude
    private String key;
    private String name,position;


    public Employee(){
        //empty constructor needed by Firebase to get data
    }
    public Employee(String name, String position) {
        this.key = key;
        this.name = name;
        this.position = position;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}
