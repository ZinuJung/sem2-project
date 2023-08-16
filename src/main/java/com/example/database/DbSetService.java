package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.Room;
import com.example.models.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DbSetService extends DbSet implements IDbSet<Service> {
    AlertMessage alert = new AlertMessage();
    ObservableList<Service> servicesDataList = FXCollections.observableArrayList();
    PreparedStatement ps;
    ResultSet rs;

    public ObservableList<String> getServiceName() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT service_name FROM services");
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("service_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public double getServicePriceByServiceName(String serviceName) {
        double servicePrice = 0;
        ps = PrepareStatement("SELECT service_price FROM services WHERE service_name = ?");
        try {
            ps.setString(1, serviceName);
            rs = ps.executeQuery();
            if (rs.next()) {
                servicePrice = rs.getDouble("service_price");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return servicePrice;
    }

    public int getServiceIdByServiceName(String serviceName) {
        int serviceId = 0;
        ps = PrepareStatement("SELECT service_id FROM services WHERE service_name = ?");
        try {
            ps.setString(1, serviceName);
            rs = ps.executeQuery();
            if (rs.next()) {
                serviceId = rs.getInt("service_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceId;
    }

    @Override
    public void add(Service model) {
        try {
            ps = PrepareStatement("SELECT service_name FROM services WHERE service_name = ?");
            ps.setString(1, model.getServiceName());
            rs = ps.executeQuery();
            if (rs.next()) {
                alert.errorMessage("This service is already exists!");
            } else {
                ps = PrepareStatement("INSERT INTO services (service_name, service_price) VALUES (?, ?)");
                ps.setString(1, model.getServiceName());
                ps.setDouble(2, model.getPrice());
                if (ps.executeUpdate() > 0) {
                    alert.successMessage("Added successfully to the database!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Service model) {
        ps = PrepareStatement("UPDATE services SET service_name = ?, service_price = ? WHERE service_id = ?");
        try {
            ps.setString(1, model.getServiceName());
            ps.setDouble(2, model.getPrice());
            ps.setInt(3, model.getServiceId());
            ps.executeUpdate();
            alert.successMessage("Service updated successfully to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int serviceId) {
        ps = PrepareStatement("DELETE FROM services WHERE service_id = ?");
        try {
            Optional<ButtonType> option = alert.confirmationMessage("Are you sure you want to delete this service?");
            if (option.get().equals(ButtonType.OK)) {
                ps.setInt(1, serviceId);
                ps.executeUpdate();

                alert.successMessage("Room deleted successfully to the database!");
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Service> findAll() {
        ps = PrepareStatement("SELECT * FROM services");
        try {
            rs = ps.executeQuery();
            Service service;
            while (rs.next()) {
                service = new Service(rs.getInt("service_id"), rs.getString("service_name"), rs.getDouble("service_price"));
                servicesDataList.add(service);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return servicesDataList;
    }
}
