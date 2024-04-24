package Model;

import Model.User;

public interface UserDAO {
    User findUserByUsername(String username);
    boolean validateUser(String username, String password);
    String getUserRole(String username);  // Method to get the user's role
}
