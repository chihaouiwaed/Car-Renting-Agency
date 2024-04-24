import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPayment extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private PaymentPanel paymentPanel;
    private ConfirmationPanel confirmationPanel;
    private CancellationPanel cancellationPanel;

    public MainPayment() {
        setTitle("Payment Processing Application");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize panels with appropriate navigation logic
        paymentPanel = new PaymentPanel(this::switchToConfirmationPanel);
        confirmationPanel = new ConfirmationPanel(this::switchToPaymentPanel);
        cancellationPanel = new CancellationPanel(this::switchToPaymentPanel);

        cardPanel.add(paymentPanel, "Payment");
        cardPanel.add(confirmationPanel, "Confirmation");
        cardPanel.add(cancellationPanel, "Cancellation");

        getContentPane().add(cardPanel);
        setVisible(true);
    }

    private void switchToConfirmationPanel(ActionEvent e) {
        cardLayout.show(cardPanel, "Confirmation");
    }

    private void switchToCancellationPanel(ActionEvent e) {
        cardLayout.show(cardPanel, "Cancellation");
    }

    private void switchToPaymentPanel(ActionEvent e) {
        cardLayout.show(cardPanel, "Payment");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainPayment::new);
    }
}
