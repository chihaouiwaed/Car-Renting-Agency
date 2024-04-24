package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaymentPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTextField cardNumberField;
    private JTextField cardExpiryField;
    private JTextField cvvField;
    private JButton payButton;
    private JButton cancelButton;

    public PaymentPanel(ActionListener switchListener) {
        loadBackgroundImage();
        setupUI(switchListener);
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("png/payment_background.png"));
        } catch (IOException e) {
            System.err.println("Unable to load background image");
            backgroundImage = null;
        }
    }

    private void setupUI(ActionListener switchListener) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 200), 2),
                "Payment Details",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(100, 100, 200)));
        add(createPaymentPanel(switchListener), BorderLayout.CENTER);
    }

    private JPanel createPaymentPanel(ActionListener switchListener) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        cardNumberField = new JTextField(20);
        cardExpiryField = new JTextField(5);
        cvvField = new JTextField(3);
        payButton = new JButton("Pay");
        cancelButton = new JButton("Cancel");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Card Number:"), gbc);

        gbc.gridy++;
        panel.add(cardNumberField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Expiry Date (MM/YY):"), gbc);

        gbc.gridy++;
        panel.add(cardExpiryField, gbc);

        gbc.gridy++;
        panel.add(new JLabel("CVV:"), gbc);

        gbc.gridy++;
        panel.add(cvvField, gbc);

        gbc.gridy++;
        payButton.addActionListener(this::processPayment);
        panel.add(payButton, gbc);

        gbc.gridy++;
        cancelButton.addActionListener(switchListener);
        panel.add(cancelButton, gbc);

        panel.setOpaque(false);
        return panel;
    }

    private void processPayment(ActionEvent event) {
        String cardNumber = cardNumberField.getText();
        String expiryDate = cardExpiryField.getText();
        String cvv = cvvField.getText();

        if (!validateCardDetails(cardNumber, expiryDate, cvv)) {
            JOptionPane.showMessageDialog(this, "Invalid card details", "Payment Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Fake payment processing logic
        if (Math.random() > 0.2) { // Simulate a 80% success rate
            JOptionPane.showMessageDialog(this, "Payment Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Payment Failed - Please try again", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateCardDetails(String cardNumber, String expiryDate, String cvv) {
        // Simple validations for card details
        if (cardNumber == null || cardNumber.length() != 16) return false;
        if (expiryDate == null || !expiryDate.matches("\\d{2}/\\d{2}")) return false;
        if (cvv == null || cvv.length() != 3) return false;
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Ensure the image covers the entire panel
        }
    }
}
