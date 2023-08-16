package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DbSetInvoice extends DbSet implements IDbSet<Invoice> {
    AlertMessage alert = new AlertMessage();
    PreparedStatement ps;
    ResultSet rs;

    public double incomeToday(Date invoiceDate) {
        double income = 0;
        ps = PrepareStatement("SELECT SUM(total_amount) FROM invoices WHERE invoice_date = ?");
        try {
            ps.setDate(1, invoiceDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                income = rs.getInt("SUM(total_amount)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return income;
    }

    public XYChart.Series getChart() {
        XYChart.Series chart = new XYChart.Series();
        ps = PrepareStatement("SELECT invoice_date, SUM(total_amount) AS total_amount_sum\n" +
                "FROM invoices\n" +
                "GROUP BY invoice_date\n" +
                "ORDER BY TIMESTAMP(invoice_date) ASC\n" +
                "LIMIT 8;\n");
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                chart.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chart;
    }

    public double totalIncome() {
        double income = 0;
        ps = PrepareStatement("SELECT SUM(total_amount) FROM invoices");
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                income = rs.getInt("SUM(total_amount)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return income;
    }

    @Override
    public void add(Invoice model) {
        ps = PrepareStatement("INSERT INTO invoices (customer_id, total_room_fee, total_service_fee, discount, total_amount, invoice_date)"
                + "VALUES(?, ?, ?, ?, ?, ?)");
        try {
            Optional<ButtonType> option = alert.confirmationMessage("Confirmation of Payment");
            if (option.get().equals(ButtonType.OK)) {
                ps.setInt(1, model.getCustomerId());
                ps.setDouble(2, model.getTotalRoomFee());
                ps.setDouble(3, model.getTotalServiceFee());
                ps.setDouble(4, model.getDiscount());
                ps.setDouble(5, model.getTotalAmount());
                ps.setDate(6, model.getInvoiceDate());

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Invoice model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ObservableList<Invoice> findAll() {
        ObservableList<Invoice> invoicesDataList = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT * FROM invoices");
        try {
            rs = ps.executeQuery();
            Invoice invoice;
            while (rs.next()) {
                invoice = new Invoice(rs.getInt("invoice_id"), rs.getInt("customer_id"), rs.getDouble("total_room_fee"),
                        rs.getDouble("total_service_fee"), rs.getDouble("discount"), rs.getDouble("total_amount"),
                        rs.getDate("invoice_date"));
                invoicesDataList.add(invoice);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoicesDataList;
    }
}
