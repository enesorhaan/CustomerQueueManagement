package com.example.musterikuyruguyonetimi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "customerqueue";
        String databaseUser = "root";
        String databasePassword = "2644";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection to database failed.");
        }
        return databaseLink;
    }
}