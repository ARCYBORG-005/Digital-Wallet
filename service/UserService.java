package service;

import dao.UserDAO;
import model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    public User loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }

    public boolean updateBalance(int userId, double newBalance) {
        return userDAO.updateBalance(userId, newBalance);
    }
    public User loginUserById(int userId)
    {
        return userDAO.getUserById(userId);
    }
}