package Controller;


import Model.*;
import  View.CustomerRentCarPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRentCarController implements ActionListener {
    private CustomerRentCarPanel rentCarPanel;
    private DAO<Car> carDAO;

    public CustomerRentCarController(CustomerRentCarPanel rentCarPanel) {
        this.rentCarPanel = rentCarPanel;
        this.carDAO = DAOFactory.getCarDAO();

        this.rentCarPanel.getRentCarButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rentCarPanel.getRentCarButton()) {
            processRentCar();
        }
    }

    private void processRentCar() {
        int selectedRow = rentCarPanel.getCarsTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rentCarPanel, "Please select a car to rent.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = rentCarPanel.getNameField().getText();
        String cin = rentCarPanel.getCinField().getText();
        String rentalDate = rentCarPanel.getRentalDateField().getText();
        String returnDate = rentCarPanel.getReturnDateField().getText();

        if (name.isEmpty() || cin.isEmpty() || rentalDate.isEmpty() || returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(rentCarPanel, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Car selectedCar = carDAO.find(rentCarPanel.getCarModel().getValueAt(selectedRow, 0).toString());
        if (selectedCar == null) {
            JOptionPane.showMessageDialog(rentCarPanel, "Selected car is not available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Ranting newRanting = new Ranting(selectedCar, new Client(cin, name, ""), false,
                java.sql.Date.valueOf(rentalDate), java.sql.Date.valueOf(returnDate));
        boolean success = DAOFactory.getRantingDAO().create(newRanting);
        if (success) {
            JOptionPane.showMessageDialog(rentCarPanel, "Car rented successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            rentCarPanel.getCarModel().removeRow(selectedRow);  // Update view to reflect the rented car is no longer available
        } else {
            JOptionPane.showMessageDialog(rentCarPanel, "Failed to rent the car.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
