/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import myapp.dbConfig.DbConnection;

/**
 *
 * @author HP
 */
public class CrudUtil {

    // Helper method to set parameters on a PreparedStatement
    private static void setParameters(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
    }

    /*
        Note :
        In here method overloading is used for define same method "with transactions" and "without transaction".
        Multiple methods with the same name but with different parameter lists (different types, number, or order of parameters).
        The compiler decides which one to call based on the arguments provided.
    */
    
    //------------- [Start : without transactions] -----------------------------
    // Get a PreparedStatement without setting parameters
    private static PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Unable to establish a valid database connection.");
        }
        return connection.prepareStatement(sql);
    }

    // Executing SQL statements that change data, like INSERT, UPDATE, DELETE.
    public static boolean executeUpdate(String sql, Object... args) throws SQLException, ClassNotFoundException {
        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            setParameters(preparedStatement, args);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    // Executing SQL queries that return data, like SELECT
    public static ResultSet executeQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        setParameters(preparedStatement, args);
        return preparedStatement.executeQuery();
    }

    /*
     Note : 
     Batch processing in SQL refers to executing multiple SQL statements (like INSERTs or UPDATEs) 
     as a single operation using one database round-trip.
    */
    // Executing SQL batch operations : bulkSave, bulkUpdate 
    public static int[] executeBatchUpdate(String sql, List<Object[]> paramList) throws SQLException, ClassNotFoundException {
        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            for (Object[] params : paramList) {
                setParameters(preparedStatement, params);
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        }
    }
    //------------- [End : without transactions] -------------------------------

    
    //------------- [Start : with transactions : (Connection connection) parameter] --------------------------------
    //use external connection 
    
    /*
    Note :
     Use transactions whenever multiple related DB operations must succeed or fail together 
    */
    
    // Get a PreparedStatement without setting parameters
    private static PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Provided connection is invalid or closed.");
        }
        return connection.prepareStatement(sql);
    }

    // Executing SQL statements that change data, like INSERT, UPDATE, DELETE.
    public static boolean executeUpdate(Connection connection, String sql, Object... args) throws SQLException {
        try (PreparedStatement preparedStatement = getPreparedStatement(connection, sql)) {
            setParameters(preparedStatement, args);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    // Executing SQL queries that return data, like SELECT
    public static ResultSet executeQuery(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        setParameters(preparedStatement, args);
        return preparedStatement.executeQuery();
    }
    
     /*
     Note : 
     Batch processing in SQL refers to executing multiple SQL statements (like INSERTs or UPDATEs) 
     as a single operation using one database round-trip.
    */
    public static int[] executeBatchUpdate(Connection connection, String sql, List<Object[]> paramList) throws SQLException {
        try (PreparedStatement preparedStatement = getPreparedStatement(connection, sql)) {
            for (Object[] params : paramList) {
                setParameters(preparedStatement, params);
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch();
        }
    }
    //------------- [End : with transactions : (Connection connection) parameter] ----------------------------------
}
