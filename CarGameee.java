import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CarGameee extends JPanel implements ActionListener, KeyListener {

    private int carX = 250; 
    private int carY = 400; 
    private final int CAR_WIDTH = 50; 
    private final int CAR_HEIGHT = 100; 
    private Timer timer;

    public CarGameee() {
        setBackground(Color.green); 
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(20, this); 
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics pen ) {
        super.paintComponent(pen);
        drawCar(pen);
    }

    private void drawCar(Graphics pen ) {
        pen.setColor(Color.RED); 
        pen.fillRect(carX, carY, CAR_WIDTH, CAR_HEIGHT); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); 
    }

    @Override//to move the car
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && carX > 0) {
            carX -= 50; 
        }
        if (key == KeyEvent.VK_RIGHT && carX < getWidth() - CAR_WIDTH) {
            carX += 50; 
        }
        if (key == KeyEvent.VK_UP && carY > 0) {
            carY -= 40; 
        }
        if (key == KeyEvent.VK_DOWN && carY < getHeight() - CAR_HEIGHT) {
            carY += 40; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple 2D Car Game");
        CarGameee game = new CarGameee();
        frame.add(game);
        frame.setSize(6000, 5000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
