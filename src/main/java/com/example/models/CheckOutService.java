package com.example.models;

public class CheckOutService {
    private String serviceName;
    private int serviceQuantity;
    private double servicePrice;
    private double amount;

    public CheckOutService() {
    }

    public CheckOutService(String serviceName, int serviceQuantity, double servicePrice, double amount) {
        this.serviceName = serviceName;
        this.serviceQuantity = serviceQuantity;
        this.servicePrice = servicePrice;
        this.amount = amount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceQuantity() {
        return serviceQuantity;
    }

    public void setServiceQuantity(int serviceQuantity) {
        this.serviceQuantity = serviceQuantity;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
