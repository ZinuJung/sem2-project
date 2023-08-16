package com.example.models;

public class Customer {
    private int customerId;
    private String fullName;
    private String identityNumber;
    private String contactNumber;

    public Customer() {
    }

    public Customer(String fullName, String identityNumber, String contactNumber) {
        this.fullName = fullName;
        this.identityNumber = identityNumber;
        this.contactNumber = contactNumber;
    }

    public Customer(int customerId, String fullName, String identityNumber, String contactNumber) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.identityNumber = identityNumber;
        this.contactNumber = contactNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

