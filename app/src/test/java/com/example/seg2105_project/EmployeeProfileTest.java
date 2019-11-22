package com.example.seg2105_project;

import com.example.seg2105_project.data.EmployeeProfile;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeProfileTest {
    @Test
    public void checkEmployeeName(){
        EmployeeProfile e = new EmployeeProfile("My Clinic", "160 Chapel,Ottawa,ON", "3435678989");
        assertEquals("Check the name of the clinic", "My Clinic", e.getClinicName());
    }
    @Test
    public void checkEmployeeNumber(){
        EmployeeProfile e = new EmployeeProfile("My Clinic", "160 Chapel,Ottawa,ON", "3435678989");
        assertEquals("Check the number of the clinic", "3435678989", e.getNumber());
    }
}
