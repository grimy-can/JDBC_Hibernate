package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDao dao = new UserDaoJDBCImpl();

        dao.createUsersTable();

        dao.saveUser("Уолтер", "Уайт ", (byte) 50);
        dao.saveUser("Джесси", "Пинкмана", (byte) 25);
        dao.saveUser("Гектор", "Саламанка", (byte) 62);
        dao.saveUser("Сол", "Гудман", (byte) 41);

        List<User> list = dao.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }

        dao.cleanUsersTable();

        dao.dropUsersTable();



    }
}
