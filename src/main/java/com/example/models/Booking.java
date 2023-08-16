package com.example.models;

import java.util.Date;

public class Booking {
    private int bookingId;
    private int customerId;
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String checkedOut;

    public Booking() {
    }

    public Booking(int roomId, Date checkOutDate) {
        this.roomId = roomId;
        this.checkOutDate = checkOutDate;
    }

    public Booking(int customerId, int roomId, Date checkInDate, Date checkOutDate) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Booking(int customerId, int roomId, Date checkInDate, Date checkOutDate, String checkedOut) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkedOut = checkedOut;
    }

    public Booking(int bookingId, int customerId, int roomId, Date checkInDate, Date checkOutDate, String checkedOut) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkedOut = checkedOut;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomNumber) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(String checkedOut) {
        this.checkedOut = checkedOut;
    }
}

