package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    // Create a Logger with class name GFG
    static Logger logger = Logger.getLogger("connector");


    private final static String DB_URL = "jdbc:mysql://localhost:3306/pre_project";
    private final static String USER = "root";
    private final static String PASS = "JavaPre-project.1.1.4";

    public static Connection getConnection()  {
        logger.setLevel(Level.INFO);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.warning(connection.getCatalog());
        } catch (SQLException e) {
            logger.warning(e.getMessage());
            logger.warning("no connection");
        }

        return connection;
    }
}
