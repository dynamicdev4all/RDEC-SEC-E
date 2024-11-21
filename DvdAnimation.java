import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DvdAnimation extends JPanel implements ActionListener {

     int x = 0, y = 0;       // Initial position of the logo
     int dx = 2, dy = 2;     // Change in position (speed and direction)
     int WIDTH = 800;  // Window width
     int HEIGHT = 600; // Window height
     int LOGO_WIDTH = 100;  // Logo width
     int LOGO_HEIGHT = 50;  // Logo height
     Timer timer;

    public DvdAnimation() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, LOGO_WIDTH, LOGO_HEIGHT);

        g2d.setColor(Color.WHITE);
        g2d.drawString("DVD1", x + 30, y + 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the logo
        x += dx;
        y += dy;

        if (x <= 0 || x + LOGO_WIDTH >= WIDTH) {
            dx *= -1; 
        }
        if (y <= 0 || y + LOGO_HEIGHT >= HEIGHT) {
            dy *= -1;
        }
        repaint();
    }

    public static void main(String[] args) {
        

        JFrame frame = new JFrame("DVD Animation");
        DvdAnimation animation = new DvdAnimation();
        frame.add(animation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
 }
}
