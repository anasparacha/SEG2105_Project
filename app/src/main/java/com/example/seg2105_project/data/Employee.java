package com.example.seg2105_project.data;

import java.io.Serializable;

public class Employee extends UserProfile implements Serializable {
    private String userName;
    private String pass;


    public Employee(String userName,String pass){
        super();
        this.userName=userName;
        this.pass=pass;

    }
    public String getUserName(){
        return userName;
    }
    public String getPass(){
        return pass;
    }

}
