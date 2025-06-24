package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                // Load PostgreSQL JDBC Driver
                Class.forName("org.postgresql.Driver");

                // Create Connection
                con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/ebook", // DB name = ebook
                    "postgres",                               // Username
                    "060105"                                  // Password
                );
                System.out.println("✅ PostgreSQL Connected Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error in DBConnection: " + e.getMessage());
        }

        return con;
    }
}
