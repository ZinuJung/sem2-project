package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.Booking;
import com.example.models.BookingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DbSetBookingService extends DbSet implements IDbSet<BookingService> {
    AlertMessage alert = new AlertMessage();
    ObservableList listData = FXCollections.observableArrayList();
    PreparedStatement ps;
    ResultSet rs;

    public ObservableList<BookingService> findByBookingId(int bookingId) {
        ps = PrepareStatement("SELECT * FROM bookingservices WHERE booking_id = ?");
        try {
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();
            BookingService bookingService;
            while (rs.next()) {
                bookingService = new BookingService(rs.getInt("booking_id"), rs.getInt("service_id"), rs.getInt("service_quantity"));
                listData.add(bookingService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    @Override
    public void add(BookingService model) {
        ps = PrepareStatement("INSERT INTO bookingservices (booking_id, service_id, service_quantity)" +
                "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE service_quantity = service_quantity + ?");
        try {
            ps.setInt(1, model.getBookingId());
            ps.setInt(2, model.getServiceId());
            ps.setInt(3, model.getServiceQuantity());
            ps.setInt(4, model.getServiceQuantity());

            ps.executeUpdate();

            alert.successMessage("Ordered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BookingService model) {

    }

    @Override
    public void delete(int customerId) {
        ps = PrepareStatement("DELETE FROM bookingservices\n" +
                "WHERE booking_id IN (\n" +
                "    SELECT booking_id\n" +
                "    FROM bookings\n" +
                "    WHERE customer_id = ?\n" +
                ");");
        try {
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<BookingService> findAll() {
        return null;
    }
}
