package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterDAOImpl implements RegisterDAO {
    private Connection connection;

    public RegisterDAOImpl() {
        connection = SingletonConnection.getInstance();
    }

    @Override
    public boolean registerUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setString(4, now.format(formatter));
            ps.setString(5, now.format(formatter));
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Error when trying to register user: " + e.getMessage());
            return false;
        }
    }
}
