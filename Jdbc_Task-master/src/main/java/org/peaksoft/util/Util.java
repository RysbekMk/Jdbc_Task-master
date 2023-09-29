package org.peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // TODO: 27.09.2023   реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:postgresql://localhost:5433/Data_base_of_project";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "post";

    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
