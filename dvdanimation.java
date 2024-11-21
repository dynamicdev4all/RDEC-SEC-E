import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class dvdanimation extends JPanel implements ActionListener {

    private int x = 50, y = 50; // Initial position
    private int xVelocity = 2, yVelocity = 2; // Movement speed
    private final int WIDTH = 600, HEIGHT = 400; // Screen size
    private final int DVD_WIDTH = 100, DVD_HEIGHT = 50; // DVD logo size
    private final Timer timer;

    public dvdanimation() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        timer = new Timer(10, this); // Timer to control animation speed
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the "DVD" rectangle
        g.setColor(Color.CYAN);
        g.fillRect(x, y, DVD_WIDTH, DVD_HEIGHT);

        // Draw "DVD" text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("DVD", x + 20, y + 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update position
        x += xVelocity;
        y += yVelocity;

        // Check for collisions with edges and reverse direction if needed
        if (x < 0 || x + DVD_WIDTH > WIDTH) {
            xVelocity = -xVelocity; // Reverse horizontal direction
        }
        if (y < 0 || y + DVD_HEIGHT > HEIGHT) {
            yVelocity = -yVelocity; // Reverse vertical direction
        }

        repaint(); // Redraw with new position
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DVD Animation");
        dvdanimation animation = new dvdanimation();

        frame.add(animation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}