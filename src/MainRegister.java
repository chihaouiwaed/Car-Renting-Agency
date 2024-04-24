

import View.RegisterPanel;
import View.SimpleCarsPanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;  // Import ActionEvent here
import java.awt.event.ActionListener;

public class MainRegister extends JFrame {

    public MainRegister() {
        setTitle("Car Rental Application - Registration");  // Set the title of the window
        setSize(800, 600);                                  // Set the size of the frame
        setLocationRelativeTo(null);                        // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // Define the default operation when the frame closes

        // Add the registration panel to this frame
        RegisterPanel registerPanel = new RegisterPanel(this::switchToLogin);
        getContentPane().add(registerPanel);

        // Show the frame
        setVisible(true);
    }

    private void switchToLogin(ActionEvent e) {
        // This method can switch to the Login panel if integrated within the same application context
        this.dispose();  // Dispose the current frame
        new MainLogin(); // Open the login frame (assuming you have a separate frame class for login called MainLogin)
    }

    public static void main(String[] args) {
        // Ensure the application is run on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainRegister();  // Create and display the main register frame
            }
        });
    }
}
