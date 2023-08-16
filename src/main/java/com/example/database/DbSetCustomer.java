package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSetCustomer extends DbSet implements IDbSet<Customer> {
    PreparedStatement ps;
    ResultSet rs;
    AlertMessage alert = new AlertMessage();

    @Override
    public void add(Customer model) {
        try {
            ps = PrepareStatement("SELECT identity_number FROM customers WHERE identity_number = ?");
            ps.setString(1, model.getIdentityNumber());
            rs = ps.executeQuery();
            if (rs.next()) {
                alert.successMessage("Invalid Identity!");
            } else {
                ps = PrepareStatement("INSERT INTO customers (full_name, identity_number, contact_number) VALUES (?, ?, ?)");
                ps.setString(1, model.getFullName());
                ps.setString(2, model.getIdentityNumber());
                ps.setString(3, model.getContactNumber());
                if (ps.executeUpdate() > 0) {
                    alert.successMessage("Added successfully to the database!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer model) {
        ps = PrepareStatement("SELECT identity_number FROM customers WHERE identity_number = ?");
        try {
            ps.setString(1, model.getIdentityNumber());
            rs = ps.executeQuery();
            if (rs.next()) {
                alert.successMessage("Invalid Identity!");
            } else {
                ps = PrepareStatement("UPDATE customers SET full_name = ?, identity_number = ?, contact_number = ? WHERE customer_id = ?");

                ps.setString(1, model.getFullName());
                ps.setString(2, model.getIdentityNumber());
                ps.setString(3, model.getContactNumber());
                ps.setInt(4, model.getCustomerId());
                ps.executeUpdate();

                alert.successMessage("Updated successfully to the database!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public ObservableList<Customer> findAll() {
        ObservableList<Customer> listData = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT * FROM customers");
        try {
            rs = ps.executeQuery();
            Customer customer;
            while (rs.next()) {
                customer = new Customer(rs.getInt("customer_id"), rs.getString("full_name"), rs.getString("identity_number"),
                        rs.getString("contact_number"));
                listData.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
}
