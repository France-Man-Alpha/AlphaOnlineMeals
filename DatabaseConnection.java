package com.example.online.orderings.alphaonlinemeal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/alphaonlinemealsdb";
    private static final String USER = "root";
    private static final String PASSWORD = "alphaman99@0233";

    // Static block to load the JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.");
        }
    }

    public static Connection initializeDatabase() throws SQLException {
        // Establish a connection to the database
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        
        // Print a message if the connection is successful
        System.out.println("Successfully connected to the database.");
        
        return connection;
    }
}
