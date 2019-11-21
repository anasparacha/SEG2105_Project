package com.example.seg2105_project.data;

public class EmployeeProfile {
    private String clinicName;
    private String clinicAddress;
    private int number;
    private String insuranceType;
    private String [] payment;
    private String [] services;

    public EmployeeProfile(String name, String address, int number, String insurance, String [] pay, String [] services){
        clinicName=name;
        clinicAddress=address;
        this.number=number;
        insuranceType=insurance;
        payment=pay;
        this.services=services;
    }
    public String getClinicName(){return clinicName;}
    public String getClinicAddress(){return clinicAddress;}
    public int getNumber(){return number;}
    public String getInsuranceType(){return insuranceType;}
    public String [] getPayment(){return payment;}
    public String [] getServices(){return services;}

    public void setClinicName(String name){clinicName=name;}
    public void setClinicAddress(String address){clinicAddress=address;}
    public void setNumber(int number){this.number=number;}
    public void setInsuranceType(String insurance){insuranceType=insurance;}
    public void setPayment(String [] pay){payment=pay;}
    public void setServices(String [] services){this.services=services;}

}
