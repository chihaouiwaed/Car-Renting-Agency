

import Controller.AddRantingController;
import Controller.CarController;
import Controller.ClientController;
import Controller.RentingController;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainLogin extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private LoginPanel loginPanel;
    private SimpleCarsPanel simpleCarsPanel;
    private RegisterPanel registerPanel;
    private UI adminUI;

    public MainLogin() {
        setTitle("Car Rental Application - Login");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize panels with appropriate navigation logic
        loginPanel = new LoginPanel(this::launchAdminUI, this::switchToSimpleCarsPanel, this::switchToRegisterPanel);
        simpleCarsPanel = new SimpleCarsPanel();
        registerPanel = new RegisterPanel(this::switchToLoginPanel);

        initAdminUI();

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(simpleCarsPanel, "SimpleCars");
        cardPanel.add(registerPanel, "Register"); // Added to card layout

        getContentPane().add(cardPanel);
        setVisible(true);
    }

    private void initAdminUI() {
        ClientsPanel clientsPanel = new ClientsPanel();
        CarsPanel carsPanel = new CarsPanel();
        RentalsPanel rentalsPanel = new RentalsPanel();
        AddRantingPanel addRantingPanel = new AddRantingPanel();
        HomePanel homePanel = new HomePanel();

        ClientController clientController = new ClientController(clientsPanel);
        CarController carController = new CarController(carsPanel);
        RentingController rentingController = new RentingController(rentalsPanel,addRantingPanel );
        AddRantingController addRantingPane1 = new AddRantingController(addRantingPanel);

        adminUI = new UI(clientsPanel, carsPanel, rentalsPanel, addRantingPanel, homePanel);
    }

    private void launchAdminUI() {
        adminUI.setVisible(true);
        this.dispose(); // Close the login window
    }

    private void switchToSimpleCarsPanel() {
        cardLayout.show(cardPanel, "SimpleCars");
    }

    private void switchToRegisterPanel() {
        cardLayout.show(cardPanel, "Register");
    }



    private void switchToLoginPanel(ActionEvent e) {
        // This method can switch to the Login panel if integrated within the same application context
        this.dispose();  // Dispose the current frame
        new MainLogin(); // Open the login frame (assuming you have a separate frame class for login called MainLogin)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainLogin::new);
    }
}
