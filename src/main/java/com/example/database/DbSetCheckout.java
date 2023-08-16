package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.Checkout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DbSetCheckout extends DbSet implements IDbSet<Checkout> {
    AlertMessage alert = new AlertMessage();
    PreparedStatement ps;
    ResultSet rs;

    public ObservableList<Checkout> findByCustomerId(int customerId) {
        ObservableList listData = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT b.check_in_date, b.check_out_date, b.room_id, r.room_type, r.base_price\n" +
                "FROM bookings b\n" +
                "JOIN rooms r ON b.room_id = r.room_id\n" +
                "WHERE b.customer_id = ? AND b.checked_out = 'Not Checked-out';");
        try {
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            Checkout checkout;
            while (rs.next()) {
                LocalDate checkinDate = rs.getDate("check_in_date").toLocalDate();
                LocalDate checkoutDate = rs.getDate("check_out_date").toLocalDate();

                long dayDif = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
                checkout = new Checkout(rs.getString("room_type"), dayDif, rs.getDouble("base_price"), dayDif * rs.getDouble("base_price"));
                listData.add(checkout);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listData;
    }

    @Override
    public void add(Checkout model) {

    }

    @Override
    public void update(Checkout model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ObservableList<Checkout> findAll() {
        return null;
    }
}
