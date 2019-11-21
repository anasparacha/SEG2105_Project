package com.example.seg2105_project.data;

public class Service {
    private String name; //any name really but should think of a way to put them into categories
    private String role; //doctor, staff, nurse

    public Service(String n, String r){
        name=n;
        role=r;
    }
    public String getName(){
        return this.name;
    }
    public String getRole(){
        return this.role;
    }
}
