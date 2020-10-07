package com.carrizane.mvppersonorm.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="persons")
public class Person extends Model {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "address")
    private String address;

    public Person(){
        super();
    }

    public Person(String name, String last_name, String address) {
        super();
        this.name = name;
        this.last_name = last_name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
