package com.example.seg2105_project.data;

import java.io.Serializable;

public class UserProfile implements Serializable {

    private String role;
    public UserProfile(String r){
        this.role=r;
    }

    public String getRole(){
        return role;
    }
    public UserProfile(){
        this.role=null;
    }

}
