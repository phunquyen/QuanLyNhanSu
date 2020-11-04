package com.xtel.training.qlns.entities;

public class Employee {
    private int id;
    private int code;
    private String name;
    private int gender;
    private String address;

    public Employee(int id, int code, String name, int gender, String address) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
