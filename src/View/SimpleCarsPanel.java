package View;

import Model.CarTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SimpleCarsPanel extends JPanel {
    private CarTableModel model;
    private JTable table;

    public SimpleCarsPanel() {
        setLayout(new BorderLayout());
        model = new CarTableModel();
        table = new JTable(model);
        initializeUI();
    }

    private void initializeUI() {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0, 70, 130));
        table.getTableHeader().setForeground(Color.white);

        // Enhancing table look and feel
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        add(scrollPane, BorderLayout.CENTER);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 70, 130), 2),
                "Car Inventory",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(0, 70, 130)));
    }

    public JTable getTable() {
        return table;
    }

    public CarTableModel getModel() {
        return model;
    }

    public void setModel(CarTableModel model) {
        this.model = model;
        table.setModel(model);
    }
}