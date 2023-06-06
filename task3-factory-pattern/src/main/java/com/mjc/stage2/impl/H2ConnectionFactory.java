package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    private String DB_DRIVER;


    @Override
    public Connection createConnection() {
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(H2ConnectionFactory.class.getClassLoader()
                    .getResourceAsStream("h2database.properties"));

            DB_DRIVER = properties.getProperty("jdbc_driver");
            DB_URL = properties.getProperty("db_url");
            DB_USER = properties.getProperty("user");
            DB_PASSWORD = properties.getProperty("password");
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException| IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    // Write your code here!
}

