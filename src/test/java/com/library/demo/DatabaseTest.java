package com.library.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        String url = "jdbc:mysql://localhost:3306/webLibrary";
        String user = "root";
        String password = "1234";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            fail("Unable to connect to database");
        }
    }

    @After
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testConnection() throws Exception {
        assertTrue(connection.isValid(5));
    }
}
