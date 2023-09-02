package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {}

    @Override
    public void createUsersTable()  {

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
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM `user`";

        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            int counter = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                list.add(new User((long) id, name, lastName, (byte) age));
                counter++;

            }
            System.out.printf("извлечено пользователей из базы данных: %d%n", counter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
    String sql = "TRUNCATE TABLE `pre_project`.`user`";
    execute(sql, "очищена таблица user");
    }

    private void execute(String sql, String message) {
        try (Connection connection = Util.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
