package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HomePanel extends JPanel {
    public static BufferedImage image;

    public HomePanel ()
    {
        super();
        try
        {
            image = ImageIO.read(new File("png/home.png"));
        }
        catch (IOException e)
        {
            //Not handled.
        }
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        // Updated gradient paint
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(240, 240, 240),  // light gray
                0, getHeight(), new Color(224, 240, 255),  // very light blue
                false  // non-reflective
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        Font f = new Font("Tahoma", Font.PLAIN, 20);
        Color c = new Color (193, 73, 13);
        Font ff = new Font("Tahoma", Font.PLAIN, 40);
        Color cc = new Color(66, 134, 244);
        Font fff = new Font("Tahoma", Font.PLAIN, 9);
        Color ccc = Color.GRAY;
        g.setColor(cc);
        g.setFont(ff);
        g.drawImage(image, 150, this.getHeight()/2-40, null);
        g.drawString("Admin Panel", 260,this.getHeight()/2);
        g.setColor(c);
        g.setFont(f);
        g.drawString("Location voiture", 395,this.getHeight()/2+40);
        g.setColor(ccc);
        g.setFont(fff);

    }
}
