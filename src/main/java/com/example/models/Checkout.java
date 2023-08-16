package com.example.models;

public class Checkout {
    private String roomType;
    private long days;
    private double price;
    private double amount;

    public Checkout() {
    }

    public Checkout(String roomType, long days, Double price, double amount) {
        this.roomType = roomType;
        this.days = days;
        this.price = price;
        this.amount = amount;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }
}
