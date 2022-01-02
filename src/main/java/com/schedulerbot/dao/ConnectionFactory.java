package com.schedulerbot.dao;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    @Value("${spring.datasource.urlpostgres}")
    public static String DB_URL;

    public static Connection getConnection() {
        try{
        System.out.println(DB_URL);
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException exception){
            throw new RuntimeException("Error connecting to the databse");
        }
    }
}
