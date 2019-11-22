package com.example.seg2105_project.data;

import java.util.List;

public class EmployeeProfile {
    private String clinicName;
    private String clinicAddress;
    private String number;
    private String insuranceType;
    private String payment;
    private List<Service> services;

    public EmployeeProfile(String name, String address, String number, String insurance, String pay, List<Service> services){
        clinicName=name;
        clinicAddress=address;
        this.number=number;
        insuranceType=insurance;
        payment=pay;
        this.services=services;
    }
    public EmployeeProfile(String name, String address, String number){
        clinicName=name;
        clinicAddress=address;
        this.number=number;
    }

    public String getClinicName(){return clinicName;}
    public String getClinicAddress(){return clinicAddress;}
    public String getNumber(){return number;}
    public String getInsuranceType(){return insuranceType;}
    public String getPayment(){return payment;}
    public List<Service> getServices(){return services;}

    public void setClinicName(String name){clinicName=name;}
    public void setClinicAddress(String address){clinicAddress=address;}
    public void setNumber(String number){this.number=number;}
    public void setInsuranceType(String insurance){insuranceType=insurance;}
    public void setPayment(String pay){payment=pay;}
    public void setServices(List<Service> services){this.services=services;}

}
