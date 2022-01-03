package com.schedulerbot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        String DB_URL = System.getenv("JDBC_DATABASE_URL");
        try{
        System.out.println(DB_URL);
            return DriverManager.getConnection(DB_URL + "&stringtype=unspecified");
        } catch (SQLException exception){
            throw new RuntimeException("Error connecting to the databse");
        }
    }
}
