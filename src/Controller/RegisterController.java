package Controller;

import Model.RegisterDAO;
import Model.RegisterDAOImpl;

public class RegisterController {
    private RegisterDAO registerDAO = new RegisterDAOImpl();

    public boolean register(String username, String password, String role) {
        return registerDAO.registerUser(username, password, role);
    }
}
