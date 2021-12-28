package com.schedulerbot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String DB_URL = System.getenv("spring.datasource.urlpostgres");

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException exception){
            throw new RuntimeException("Error connecting to the databse");
        }
    }
}
