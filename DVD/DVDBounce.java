import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DVDBounce extends JPanel implements ActionListener {
    private int x = 100, y = 100;
    private int xSpeed = 2, ySpeed = 3;
    private final int LOGO_WIDTH = 80;
    private final int LOGO_HEIGHT = 40;
    private Timer timer;
    public DVDBounce() {
        timer = new Timer(10, this); 
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.CYAN);
        g.fillRect(x, y, LOGO_WIDTH, LOGO_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("DVD", x + 15, y + 28);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += xSpeed;
        y += ySpeed;
        if (x <= 0 || x + LOGO_WIDTH >= getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y <= 0 || y + LOGO_HEIGHT >= getHeight()) {
            ySpeed = -ySpeed;
        }
        repaint();
    }
}