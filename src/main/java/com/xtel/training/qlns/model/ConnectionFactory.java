package com.xtel.training.qlns.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String URL = "jdbc:mysql://localhost:3306/db_employee";
    private static String USER = "root";
    private static String PASSWORD = "";

    public static Connection createConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
