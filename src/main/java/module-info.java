module com.example.hotelmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.fasterxml.jackson.annotation;

    opens com.example.hotelmanagementsystem to javafx.fxml;
    exports com.example.hotelmanagementsystem;
    exports com.example.controllers;
    opens com.example.controllers to javafx.fxml;
    exports com.example.database;
    opens com.example.database to javafx.fxml;
    opens com.example.models;
}