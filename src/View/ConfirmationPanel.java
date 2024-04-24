package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConfirmationPanel extends JPanel {
    private JButton returnToPaymentButton;

    public ConfirmationPanel(ActionListener switchToPaymentPanel) {
        setLayout(new BorderLayout());
        returnToPaymentButton = new JButton("Return to Payment");
        returnToPaymentButton.addActionListener(switchToPaymentPanel);
        add(returnToPaymentButton, BorderLayout.CENTER);
    }
}