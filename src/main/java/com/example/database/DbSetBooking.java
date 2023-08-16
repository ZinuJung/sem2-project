package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DbSetBooking extends DbSet implements IDbSet<Booking> {
    AlertMessage alert = new AlertMessage();
    PreparedStatement ps;
    ResultSet rs;

    public Date getDateByCustomerId(int customerId) {
        ps = PrepareStatement("SELECT DISTINCT check_out_date FROM bookings WHERE customer_id = ? AND checked_out = 'Not Checked-out'");
        Date date = null;
        try {
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                date = rs.getDate("check_out_date");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public int getBookingIdByRoomId(int roomId) {
        int bookingId = 0;
        ps = PrepareStatement("SELECT booking_id FROM bookings WHERE room_id = ? AND checked_out = 'Not Checked-out'");
        try {
            ps.setInt(1, roomId);
            rs = ps.executeQuery();
            if (rs.next()) {
                bookingId = rs.getInt("booking_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingId;
    }

    public int countBooking(Date checkInDate) {
        int count = 0;
        ps = PrepareStatement("SELECT COUNT(booking_id) FROM bookings WHERE check_in_date = ?");
        try {
            ps.setDate(1, checkInDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT(booking_id)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public boolean checkBooking(int customerId) {
        ps = PrepareStatement("SELECT DISTINCT customer_id FROM bookings WHERE customer_id = ?");
        try {
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean checkRegular(int customerId) {
        ps = PrepareStatement("SELECT * FROM bookings WHERE customer_id = ? AND checked_out = 'Checked-out'");
        try {
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String checkStatus(int bookingId) {
        ps = PrepareStatement("SELECT checked_out FROM bookings WHERE booking_id = ?");
        String checkOut = null;
        try {
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();
            if (rs.next()) {
                checkOut = rs.getString("checked_out");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkOut;
    }

    public void updateStatus(int customerId) {
        ps = PrepareStatement("UPDATE bookings SET checked_out = 'Checked-out' WHERE customer_id = ?");
        try {
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Booking model) {
        ps = PrepareStatement("INSERT INTO bookings (customer_id, room_id, check_in_date, check_out_date)"
                + "VALUES(?, ?, ?, ?)");
        try {
            Optional<ButtonType> option = alert.confirmationMessage("Are you sure?");
            if (option.get().equals(ButtonType.OK)) {
                ps.setInt(1, model.getCustomerId());
                ps.setInt(2, model.getRoomId());
                ps.setDate(3, (Date) model.getCheckInDate());
                ps.setDate(4, (Date) model.getCheckOutDate());

                ps.executeUpdate();

                alert.successMessage("Successfully Check-In!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Booking model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ObservableList<Booking> findAll() {
        ObservableList listData = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT * FROM bookings");
        try {
            rs = ps.executeQuery();
            Booking booking;
            while (rs.next()) {
                booking = new Booking(rs.getInt("booking_id"), rs.getInt("customer_id"), rs.getInt("room_id"),
                        rs.getDate("check_in_date"), rs.getDate("check_out_date"), rs.getString("checked_out"));
                listData.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
}
