package View;

import javax.swing.*;

public class UI extends JFrame{
    private JTabbedPane tabbedPane;

    public UI(ClientsPanel clients, CarsPanel cars, RentalsPanel rentals, AddRantingPanel AddRanting, HomePanel home) {
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        tabbedPane.add("", home);
        tabbedPane.add("", clients);
        tabbedPane.add("", cars);
        tabbedPane.add("", rentals);
        tabbedPane.add("", AddRanting);

        //icons
        ImageIcon homeIcon = new ImageIcon("png/home.gif");
        ImageIcon clientIcon = new ImageIcon("png/client.gif");
        ImageIcon carIcon = new ImageIcon("png/car.gif");
        ImageIcon rantingIcon = new ImageIcon("png/ranting.PNG");
        ImageIcon addRantIcon = new ImageIcon("png/bag.PNG");

        tabbedPane.setIconAt(0, homeIcon);
        tabbedPane.setIconAt(1, clientIcon);
        tabbedPane.setIconAt(2, carIcon);
        tabbedPane.setIconAt(3, rantingIcon);
        tabbedPane.setIconAt(4, addRantIcon);


        this.getContentPane().add(tabbedPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Location Voiture");
        this.setSize(1140, 600);
        ImageIcon img = new ImageIcon("png/racing.PNG");
        this.setIconImage(img.getImage());
        this.setResizable(false);

    }
}
