package com.example.vnpt_v3.Model;

import java.util.Date;

public class User {
    public  int id;
    public String name;
    public Date datetime;

    public User(int id, String name, Date datetime) {
        this.id = id;
        this.name = name;
        this.datetime = datetime;
    }
}
