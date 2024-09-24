package com.contactmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";  // Change "root" to your MySQL username
    private static final String PASSWORD = "root";  // Change to your MySQL password

    // This method returns a Connection object that can be used to interact with the database
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the MySQL JDBC driver (necessary for older versions of Java)
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the connection to the database
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // This method will handle any errors and print messages if connection fails
    public static void main(String[] args) {
        try {
            Connection connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Connection to the database was successful!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

}
