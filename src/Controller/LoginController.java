package Controller;

import Model.UserDAO;
import Model.UserDAOImpl;
import Model.User;

public class LoginController {
    private UserDAO userDAO = new UserDAOImpl();

    public boolean authenticate(String username, String password) {
        return userDAO.validateUser(username, password);
    }

    public User getUserDetails(String username) {
        return userDAO.findUserByUsername(username);
    }

    public String getUserRole(String username) {
        return userDAO.getUserRole(username);  // Delegate call to DAO
    }
}
