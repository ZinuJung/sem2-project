package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.hotelmanagementsystem.RunApplication;
import com.example.models.Employee;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class DbSetEmployee extends DbSet implements IDbSet<Employee> {
    AlertMessage alert = new AlertMessage();
    ArrayList<Employee> listItems = new ArrayList<Employee>();
    PreparedStatement ps;
    ResultSet rs;
    Scene scene;
    public ArrayList<Employee> register(String username, String email, String password) {
        try {
            ps = PrepareStatement("SELECT * FROM accounts WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                alert.errorMessage(username + " is already exists!");
            } else {
                ps = PrepareStatement("INSERT INTO admin (email, username, password) VALUES (?, ?, ?)");
                ps.setString(1, email);
                ps.setString(2, username);
                ps.setString(3, password);

                ps.executeUpdate();

                alert.successMessage("Registered Successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listItems;
    }

    public boolean login(String username, String password) {
        try {
            ps = PrepareStatement("SELECT * FROM accounts WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("is_admin");
                if (isAdmin) {
                    FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource(""));
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Admin");
                    stage.setScene(scene);
                    stage.show();
                    return true;
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("dashboard-view.fxml"));
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = new Stage();
                    stage.setMinHeight(640);
                    stage.setMinWidth(1120);
                    stage.setTitle("Hotel Management System");
                    stage.setScene(scene);
                    stage.show();
                    return true;
                }
            } else {
                alert.errorMessage("Incorrect Username/Password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void logout(Button btn) {
        Optional<ButtonType> option = alert.confirmationMessage("Are you sure you want to logout?");
        if (option.get().equals(ButtonType.OK)) {
            FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("login-view.fxml"));
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Hotel Management System");
            stage.setScene(scene);
            stage.show();
            btn.getScene().getWindow().hide();
        } else {

        }
    }

    @Override
    public void add(Employee model) {

    }

    @Override
    public void update(Employee model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ObservableList<Employee> findAll() {
        return null;
    }
}
