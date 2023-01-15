package com.staffApp.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class User  implements Serializable {

    @Exclude
    private String key;
    private  String name,age,email,password;

    public User() {


    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User(String name, String email, String age, String password) {

        this.name=name;
        this.age=age;
        this.password=password;
        this.email = email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
