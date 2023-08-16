package com.example.models;

public class BookingService {
    private int bookingId;
    private int serviceId;
    private int serviceQuantity;

    public BookingService() {
    }

    public BookingService(int bookingId, int serviceId, int serviceQuantity) {
        this.bookingId = bookingId;
        this.serviceId = serviceId;
        this.serviceQuantity = serviceQuantity;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceQuantity() {
        return serviceQuantity;
    }

    public void setServiceQuantity(int serviceQuantity) {
        this.serviceQuantity = serviceQuantity;
    }
}
