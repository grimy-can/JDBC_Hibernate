package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.util.List;


public class UserServiceImpl implements UserService {

    private final UserDaoJDBCImpl usered = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        usered.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        usered.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        usered.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) {
        usered.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return  usered.getAllUsers();
    }
    @Override
    public void cleanUsersTable() {
        usered.cleanUsersTable();
    }
}
