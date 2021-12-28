package com.schedulerbot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "postgres://ymoulficmdkscs:65a0867e749c3fe0993183700e491ad9b402409a6bc50f9b15f2172a12d2993b@ec2-3-213-76-170.compute-1.amazonaws.com:5432/df3tukoigq4ivc";
    public static final String USER = "root";
    public static final String PASS = "OTIyOTcxMDYxMDQwMDY2NTYw.YcJNyA.bXeYOBJ9cF4WJpCL-zTyfbZjD74";

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException exception){
            throw new RuntimeException("Error connecting to the databse");
        }
    }
}
