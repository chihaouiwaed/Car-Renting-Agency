package Model;

public interface RegisterDAO {
    boolean registerUser(String username, String password, String role);
}
