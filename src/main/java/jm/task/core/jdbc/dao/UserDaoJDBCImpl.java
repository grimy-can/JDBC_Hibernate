package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    static Logger logger = Logger.getLogger("dao");
    Connection connection;
    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable()  {
        PreparedStatement ps;
        String sql = """
                CREATE TABLE `users` (
                  `id` int NOT NULL AUTO_INCREMENT,
                  `name` varchar(45) NOT NULL,
                  `lastName` varchar(45) NOT NULL,
                  `age` int DEFAULT NULL,
                  PRIMARY KEY (`id`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;""";

        try {
            ps = connection.prepareStatement(sql);
            ps.execute();
            logger.warning("создана таблица users");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

    public void dropUsersTable() {
        PreparedStatement ps;
        String sql = "DROP TABLE `pre_project`.`users`";

        try {
            ps = connection.prepareStatement(sql);
            ps.execute();
            logger.warning("удалена таблица users");
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
