package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/pre_project";
    private final static String USER = "root";
    private final static String PASS = "root";

    public static Connection getConnection()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println(String.format("подключено к %s", connection.getCatalog()));
        } catch (SQLException e) {
            System.out.println("нет соединения");
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
