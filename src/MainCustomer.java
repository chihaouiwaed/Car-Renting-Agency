
import Controller.CustomerRentCarController;
import View.CustomerRentCarPanel;
import javax.swing.*;

public class MainCustomer {

    public static void main(String[] args) {
        // Set the look and feel to system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and set up the window
        JFrame frame = new JFrame("Customer Car Rental");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Set the frame size

        // Create the panel and controller
        CustomerRentCarPanel rentCarPanel = new CustomerRentCarPanel();
        new CustomerRentCarController(rentCarPanel);  // The controller attaches itself to the panel

        // Add the panel to the frame
        frame.add(rentCarPanel);

        // Display the window
        frame.pack();  // Pack contents of the window and prefered sizes of components
        frame.setLocationRelativeTo(null);  // Center the window
        frame.setVisible(true);  // Make the window visible
    }
}
