package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.util.List;


public class UserServiceImpl implements UserService {

    private Connection connection = Util.getConnection();
    private final UserDaoJDBCImpl usered = new UserDaoJDBCImpl();
    public void createUsersTable() {
        usered.createUsersTable();
    }

    public void dropUsersTable() {
        usered.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usered.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usered.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return  usered.getAllUsers();
    }

    public void cleanUsersTable() {
        usered.cleanUsersTable();
    }
}
