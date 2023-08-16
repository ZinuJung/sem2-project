package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.CheckOutService;
import com.example.models.Checkout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DbSetCheckoutService extends DbSet implements IDbSet<CheckOutService> {
    AlertMessage alert = new AlertMessage();
    PreparedStatement ps;
    ResultSet rs;

    public ObservableList<CheckOutService> findByCustomerId(int customerId) {
        ObservableList listData = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT s.service_name, s.service_price, bs.service_quantity\n" +
                "FROM Services s\n" +
                "JOIN BookingServices bs ON s.service_id = bs.service_id\n" +
                "JOIN Bookings b ON b.booking_id = bs.booking_id\n" +
                "JOIN Customers c ON c.customer_id = b.customer_id\n" +
                "WHERE c.customer_id = ?;\n");
        try {
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            CheckOutService checkOutService;
            while (rs.next()) {
                checkOutService = new CheckOutService(rs.getString("service_name"), rs.getInt("service_quantity"),
                        rs.getDouble("service_price"), rs.getInt("service_quantity") * rs.getDouble("service_price"));
                listData.add(checkOutService);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listData;
    }

    @Override
    public void add(CheckOutService model) {

    }

    @Override
    public void update(CheckOutService model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ObservableList<CheckOutService> findAll() {
        return null;
    }
}
