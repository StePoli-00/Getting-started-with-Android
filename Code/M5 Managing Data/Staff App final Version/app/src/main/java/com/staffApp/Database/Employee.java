package com.staffApp.Database;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
/**Model class**/
public class Employee  implements Serializable {

    @Exclude
    private String idEmployee;
    private String name,position,idBoss;

    public Employee(){

    }

    public Employee(String name, String position,String idBoss) {
        this.name = name;
        this.position = position;
        this.idBoss=idBoss;
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

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdBoss() {
        return idBoss;
    }

    public void setIdBoss(String idBoss) {
        idBoss = idBoss;
    }
}
