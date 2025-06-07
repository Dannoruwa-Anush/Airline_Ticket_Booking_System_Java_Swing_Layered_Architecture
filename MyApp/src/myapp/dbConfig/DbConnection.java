/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author HP
 */
public class DbConnection {
    /**
    *   Singleton data pattern class has only one instance and provides a global point of access to that instance
    */
    
    private static volatile DbConnection dbConnection;
    private Connection connection;

    // Constants for database configuration
    private static final String DB_NAME = "java_swing_app_db";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    // Private constructor
    private DbConnection() {
        connect();
    }

    // Thread-safe lazy initialization
    public static DbConnection getInstance() {
        if (dbConnection == null) {
            synchronized (DbConnection.class) {
                if (dbConnection == null) {
                    dbConnection = new DbConnection();
                }
            }
        }
        return dbConnection;
    }

    // Connect to the database
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Public method to get the connection
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Reconnecting to the database...");
                connect();
            } else {
                System.out.println("Database connection is active.");
            }
        } catch (SQLException e) {
            System.out.println("Error checking connection status.");
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return connection;
    }
}
