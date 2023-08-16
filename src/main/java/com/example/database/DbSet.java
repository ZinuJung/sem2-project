package com.example.database;

import java.sql.*;

public class DbSet {
    protected Connection CreateConnection() {
        Connection conn;
        try {
            ConnectionString cs = new ConnectionString();
            conn = DriverManager.getConnection(cs.url, cs.user, cs.password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PreparedStatement PrepareStatement(String query) {
        try {
            var conn = CreateConnection();
            var stmt = conn.prepareStatement(query);
            return stmt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
