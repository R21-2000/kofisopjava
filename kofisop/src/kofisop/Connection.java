/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kofisop;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kofisopjava";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
