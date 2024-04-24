package View;

import Controller.LoginController;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private LoginController loginController = new LoginController();

    // Interface for navigation callbacks
    private Runnable onAdminLoginSuccess;
    private Runnable onUserLoginSuccess;
    private Runnable onGoToRegister;

    public LoginPanel(Runnable onUserLoginSuccess, Runnable onAdminLoginSuccess, Runnable onGoToRegister) {
        this.onUserLoginSuccess = onUserLoginSuccess;
        this.onAdminLoginSuccess = onAdminLoginSuccess;
        this.onGoToRegister = onGoToRegister;
        loadBackgroundImage();
        setupUI();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("png/login_background.png"));
        } catch (IOException e) {
            System.err.println("Unable to load background image");
            backgroundImage = null;
        }
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 70, 130), 2),
                "User Login",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 70, 130)));
        add(createLoginPanel(), BorderLayout.CENTER);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Set components to be transparent
        usernameField.setOpaque(false);
        passwordField.setOpaque(false);
        loginButton.setOpaque(false);
        registerButton.setOpaque(false);

        gbc.gridx = 0; // Align components in a single column
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridy++;
        panel.add(usernameField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridy++;
        panel.add(passwordField, gbc);

        gbc.gridy++;
        loginButton.addActionListener(this::performLogin);
        panel.add(loginButton, gbc);

        gbc.gridy++; // Move the register button below the login button
        registerButton.addActionListener(e -> onGoToRegister.run());
        panel.add(registerButton, gbc);

        panel.setOpaque(false); // Make the panel transparent to show the background image
        return panel;
    }

    private void performLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (loginController.authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            String role = loginController.getUserRole(username);
            if ("admin".equals(role)) {
                onUserLoginSuccess.run();
            } else {
                onAdminLoginSuccess.run();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Ensure the image covers the entire panel
        }
    }
}
