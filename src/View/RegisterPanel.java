package View;

import Controller.RegisterController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegisterPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleComboBox;
    private JButton registerButton;
    private JButton switchToLoginButton;
    private RegisterController registerController = new RegisterController();

    public RegisterPanel(ActionListener switchListener) {
        loadBackgroundImage();
        setupUI(switchListener);
    }



    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("png/login_background.png"));
        } catch (IOException e) {
            System.err.println("Unable to load background image");
            backgroundImage = null;
        }
    }

    private void setupUI(ActionListener switchListener) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 70, 130), 2),
                "User Register",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 70, 130)));
        add(createRegisterPanel(switchListener), BorderLayout.CENTER);
    }


    private JPanel createRegisterPanel(ActionListener switchListener) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        roleComboBox = new JComboBox<>(new String[]{"User", "Admin"});
        registerButton = new JButton("Register");
        switchToLoginButton = new JButton("Back to Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridy++;
        panel.add(usernameField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridy++;
        panel.add(passwordField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Confirm Password:"), gbc);

        gbc.gridy++;
        panel.add(confirmPasswordField, gbc);

        gbc.gridy++;
        registerButton.addActionListener(this::performRegistration);
        panel.add(registerButton, gbc);

        gbc.gridy++;
        switchToLoginButton.addActionListener(switchListener);
        panel.add(switchToLoginButton, gbc);

        panel.setOpaque(false);
        return panel;
    }

    private void performRegistration(ActionEvent event) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Registration Error", JOptionPane.ERROR_MESSAGE);
            //return;

        }

        if (registerController.register(username, password, role)) {
            JOptionPane.showMessageDialog(this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            try {
                Util.EmailUtility.sendEmail(username, "Registration Confirmation",
                        "Hello, " + username + "! Your account has been successfully created.");
                JOptionPane.showMessageDialog(this, "confirmation email sent. Please check your email.", "Email Sent", JOptionPane.INFORMATION_MESSAGE);
            } catch ( javax.mail.MessagingException e ) {
                JOptionPane.showMessageDialog(this, "Failed to send confirmation email. Please check your email settings.", "Email Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed - User may already exist", "Error", JOptionPane.ERROR_MESSAGE);
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
