package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
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
            System.out.printf("подключено к %s%n", connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("нет соединения");
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public static SessionFactory getFactory() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.url", DB_URL)
                    .setProperty("hibernate.connection.driver.class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.username", USER)
                    .setProperty("hibernate.connection.password", PASS)
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("show_sql", "true")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());;
        }
        return sessionFactory;
    }


}
