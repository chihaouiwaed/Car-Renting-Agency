package View;

import Model.CarTableModel;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class CustomerRentCarPanel extends JPanel {
    private CarTableModel carModel;
    private JTable carsTable;
    private JTextField nameField, cinField;  // Fields for customer name and CIN
    private JFormattedTextField rentalDateField, returnDateField;  // Fields for dates
    private JButton rentCarButton;  // Button to submit the rental

    public CustomerRentCarPanel() {
        setLayout(new BorderLayout(10, 10));

        carModel = new CarTableModel();  // Assuming the CarTableModel exists and is set up correctly
        carsTable = new JTable(carModel);
        JScrollPane scrollPane = new JScrollPane(carsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Form panel for customer input and date selection
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField(10);
        formPanel.add(nameField);

        formPanel.add(new JLabel("CIN:"));
        cinField = new JTextField(10);
        formPanel.add(cinField);

        formPanel.add(new JLabel("Rental Date (yyyy-MM-dd):"));
        rentalDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        formPanel.add(rentalDateField);

        formPanel.add(new JLabel("Return Date (yyyy-MM-dd):"));
        returnDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        formPanel.add(returnDateField);

        rentCarButton = new JButton("Rent Car");
        formPanel.add(new JLabel());  // Placeholder
        formPanel.add(rentCarButton);

        add(formPanel, BorderLayout.SOUTH);
    }

    // Getters for the controller to use
    public CarTableModel getCarModel() { return carModel; }
    public JTable getCarsTable() { return carsTable; }
    public JTextField getNameField() { return nameField; }
    public JTextField getCinField() { return cinField; }
    public JFormattedTextField getRentalDateField() { return rentalDateField; }
    public JFormattedTextField getReturnDateField() { return returnDateField; }
    public JButton getRentCarButton() { return rentCarButton; }
}
