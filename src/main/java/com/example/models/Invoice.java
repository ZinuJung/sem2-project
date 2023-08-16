package com.example.models;

import java.sql.Date;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private double totalRoomFee;
    private double totalServiceFee;
    private double discount;
    private double totalAmount;
    private Date invoiceDate;

    public Invoice() {
    }

    public Invoice(int customerId, double totalRoomFee, double totalServiceFee, double totalAmount, Date invoiceDate) {
        this.customerId = customerId;
        this.totalRoomFee = totalRoomFee;
        this.totalServiceFee = totalServiceFee;
        this.totalAmount = totalAmount;
        this.invoiceDate = invoiceDate;
    }

    public Invoice(int customerId, double totalRoomFee, double totalServiceFee, double discount, double totalAmount, Date invoiceDate) {
        this.customerId = customerId;
        this.totalRoomFee = totalRoomFee;
        this.totalServiceFee = totalServiceFee;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.invoiceDate = invoiceDate;
    }

    public Invoice(int invoiceId, int customerId, double totalRoomFee, double totalServiceFee, double discount, double totalAmount, Date invoiceDate) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.totalRoomFee = totalRoomFee;
        this.totalServiceFee = totalServiceFee;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int bookingId) {
        this.customerId = bookingId;
    }

    public double getTotalRoomFee() {
        return totalRoomFee;
    }

    public void setTotalRoomFee(double totalRoomFee) {
        this.totalRoomFee = totalRoomFee;
    }

    public double getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(double totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
