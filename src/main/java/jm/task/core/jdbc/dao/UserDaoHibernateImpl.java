package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS `user` (
                  `id` int NOT NULL AUTO_INCREMENT,
                  `name` varchar(45) NOT NULL,
                  `lastName` varchar(45) NOT NULL,
                  `age` int NOT NULL,
                  PRIMARY KEY (`id`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;""";

        execute(sql, "создана таблица user");
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS `pre_project`.`user`";
        execute(sql, "удалена таблица user");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = String
                .format("INSERT INTO user (name, lastName, age) VALUES ('%s', '%s', '%d')", name, lastName, age);
        execute(sql, String
                .format("User %s добавлен в базу данных", name));
    }

    @Override
    public void removeUserById(long id) {
        String sql = String
                .format("DELETE FROM user WHERE id = %d;", id);
        execute(sql, String
                .format("пользователь с id %d удалён", id));
    }

    @Override
    public List<User> getAllUsers() {

        SessionFactory sessionFactory = Util.getFactory();

        try  {
            String sql = "SELECT * FROM user";
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> list = session.createNativeQuery(sql, User.class).getResultList();
            session.getTransaction().commit();
            System.out.printf("извлечено пользователей из базы данных: %d%n", list.size());
            return list;

        } finally {
            sessionFactory.close();
        }

    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE `pre_project`.`user`";
        execute(sql, "очищена таблица user");
    }

    private void execute(String sql, String message) {
        SessionFactory sessionFactory = Util.getFactory();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            System.out.println(message);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
