package com.example.aditya.realmexample;

/**
 * Created by prathvi on 3/4/17.
 */

public class DataObject {

    private int id;
    private String name,age,number,email;

    public DataObject() {

    }

    public DataObject(int id, String name, String age, String number, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.number = number;
        this.email = email;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
