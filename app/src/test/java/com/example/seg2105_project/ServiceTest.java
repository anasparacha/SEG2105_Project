package com.example.seg2105_project;

import com.example.seg2105_project.data.Admin;
import com.example.seg2105_project.data.Service;
import com.example.seg2105_project.data.UserProfile;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {
    @Test
    public void checkServiceName(){
        Service s = new Service("Check Up", "nurse");
        assertEquals("Check the name of the service", "Check Up", s.getName());
    }

    @Test
    public void checkServiceRole(){
        Service s = new Service("Check Up", "nurse");
        assertEquals("Check the role of the service", "nurse", s.getRole());
    }
    @Test
    public void checkUserRole(){
        UserProfile u= new UserProfile("Admin");
        assertEquals("Check the role of the user", "Admin", u.getRole());
    }

    @Test
    public void checkUserName(){
        Admin a= new Admin("apple","orange");
        assertEquals("Check the username of the user", "apple", a.getUserName());
    }
    @Test
    public void checkUserPassword(){
        Admin a= new Admin("apple","orange");
        assertEquals("Check the password of the user", "orange", a.getPass());
    }

}
