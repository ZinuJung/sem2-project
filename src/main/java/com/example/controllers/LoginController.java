package com.example.controllers;

import com.example.database.DbSetEmployee;
import com.example.exceptions.AlertMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox checkBoxShow;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    private AlertMessage alert = new AlertMessage();

    DbSetEmployee e = new DbSetEmployee();

    public void showPassword() {
        if (checkBoxShow.isSelected()) {
            txtPassword.setText(pfPassword.getText());
            pfPassword.setVisible(false);
            txtPassword.setVisible(true);
        } else {
            pfPassword.setText(txtPassword.getText());
            pfPassword.setVisible(true);
            txtPassword.setVisible(false);
        }
    }

    public void login() {
        if (txtUsername.getText().isEmpty() || pfPassword.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {
            if (checkBoxShow.isSelected()) {
                pfPassword.setText(txtPassword.getText());
            }
            boolean loginSuccess = e.login(txtUsername.getText(), pfPassword.getText());
            if (loginSuccess) {
                btnLogin.getScene().getWindow().hide();
            }
        }
    }
}
