import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingDVDLogo extends JPanel implements ActionListener {

    
    private Image logoImage;
    private static final int LOGO_WIDTH = 100;
    private static final int LOGO_HEIGHT = 50;
    private int x = 0;  
    private int y = 0;  
    private int dx = 3; 
    private int dy = 3; 

    private Timer timer;

    public BouncingDVDLogo() {
        
        ImageIcon icon = new ImageIcon("DVD.png"); // Change path to your image file
        logoImage = icon.getImage();

        
        timer = new Timer(10, this);  
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        
        g.drawImage(logoImage, x, y, LOGO_WIDTH, LOGO_HEIGHT, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        x += dx;
        y += dy;

        
        if (x + LOGO_WIDTH > getWidth() || x < 0) {
            dx = -dx;  
        }
        if (y + LOGO_HEIGHT > getHeight() || y < 0) {
            dy = -dy;  
        }

       
        repaint();
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Bouncing DVD Logo");
        BouncingDVDLogo panel = new BouncingDVDLogo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); 
        frame.add(panel);
        frame.setVisible(true);
    }
}