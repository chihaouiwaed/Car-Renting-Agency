package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl() {
        connection = SingletonConnection.getInstance();
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getTimestamp("created_at").toLocalDateTime(), rs.getTimestamp("updated_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.err.println("Error when trying to find user: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean validateUser(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return dbPassword != null && dbPassword.equals(password);
            }
        } catch (SQLException e) {
            System.err.println("Error when trying to validate user: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String getUserRole(String username) {
        String sql = "SELECT role FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.err.println("Error when trying to retrieve user role: " + e.getMessage());
        }
        return null;  // Return null if no role found or an error occurs
    }
}
