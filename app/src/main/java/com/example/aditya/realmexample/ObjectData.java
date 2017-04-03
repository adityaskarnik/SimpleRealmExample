package com.example.aditya.realmexample;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by prathvi on 3/4/17.
 */

public class ObjectData extends RealmObject {

    @PrimaryKey
    private int id;
    private String name,age,number,email;

    public ObjectData() {

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
