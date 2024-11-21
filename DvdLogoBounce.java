import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class DvdLogoBounce extends JPanel implements ActionListener {
	private Image roadImage;
    private BufferedImage logoImage;
     int x = 200;
     int y = 450;
     int dx = 3;  
     int dy = 2;  
    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 600;

    private Timer timer;

    private final int LOGO_WIDTH = 100;  
    private final int LOGO_HEIGHT = 100;  

    public DvdLogoBounce() {
       
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.GREEN);
        
        loadLogoImage();
        
      
        timer = new Timer(10, this);
        timer.start();
    }


    private void loadLogoImage() {
        try {
        	  roadImage = ImageIO.read(getClass().getResource("road.jpg"));
            logoImage = ImageIO.read(getClass().getResource("car.gif"));
            if (logoImage == null) {
                System.out.println("Image not loaded.");
            }
        } catch (IOException e) {
            System.out.println("No image found");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        x += dx;
        y += dy;

        
        if (x <= 0 || x + LOGO_WIDTH >= getWidth()) {
            dx = -dx;  
        }

        if (y <= 0 || y + LOGO_HEIGHT >= getHeight()) {
            dy = -dy;  
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (roadImage != null) {
            g.drawImage(roadImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (logoImage != null) {
          
            g.drawImage(logoImage, x, y, LOGO_WIDTH, LOGO_HEIGHT, this);
        } else {
      
            g.drawString("Image not found", 200, 250);
        }
    }

   
    public static void main(String[] args) {
     
        JFrame frame = new JFrame("DVD_Logo_Bounce");
        DvdLogoBounce panel = new DvdLogoBounce();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}