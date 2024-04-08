package com.example.parfumeria2.Model.Persistence;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//java doc link: file:///D:/An2/Sem2/TP/PT2022_30223_Tivadar_Maria_Assignment_3/JavaDocs/index.html

/**
 * @author : Tivadar Maria Simona
 * @since : Apr 17, 2022
 * Usage: connection of Maven project and SQL database in order to access and manipulate the data from the database
 *        we use the DriverManager in order to recevie a connection
 *        that can only be done specifying the name of the data base, the user and the password
 */
public class Persistence {

    private static final Logger LOGGER = Logger.getLogger(Persistence.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/store";
    private static final String USER = "root";
    private static final String PASS = "1Brac-German";

    private static Persistence singleInstance = new Persistence();

    private Persistence() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    public static boolean close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                return true;
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
        return false;
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
    public static int executeUpdate(String sql) {
        int result = 0;
        try {
            Connection conn = Persistence.getConnection();
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
            Persistence.close(stmt);
            Persistence.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
