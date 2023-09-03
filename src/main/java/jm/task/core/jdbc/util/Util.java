package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


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


    public static SessionFactory getFactory() {

        Configuration config = new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pre_project")
                .setProperty("hibernate.connection.driver.class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "Pre-project.1.1.4")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("show_sql", "true");


        SessionFactory sessionFactory = config
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        return sessionFactory;
    }


}
