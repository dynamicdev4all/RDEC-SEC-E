import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int birdY = 250;
    private int birdVelocity = 0;
    private int gravity = 1;
    private int score = 0;
    private boolean gameOver = false;
    private ArrayList<Rectangle> pipes = new ArrayList<>();
    private Timer timer;
    private Random random = new Random();

    public GamePanel() {
        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        addPipe(true);
        addPipe(true);
    }

    private void addPipe(boolean start) {
        int gap = 300;
        int width = 100;
        int height = 50 + random.nextInt(300);

        if (start) {
            pipes.add(new Rectangle(800 + width + pipes.size() * 300, 600 - height - 120, width, height));
            pipes.add(new Rectangle(800 + width + (pipes.size() - 1) * 300, 0, width, 600 - height - gap));
        } else {
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x + 600, 600 - height - 120, width, height));
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, width, 600 - height - gap));
        }
    }

    private void movePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            pipe.x -= 5;
        }

        if (pipes.get(0).x + pipes.get(0).width < 0) {
            pipes.remove(0);
            pipes.remove(0);
            addPipe(false);
            score++;
        }
    }

    private void checkCollision() {
        for (Rectangle pipe : pipes) {
            if (pipe.intersects(new Rectangle(100, birdY, 30, 30))) {
                gameOver = true;
            }
        }

        if (birdY > 570 || birdY < 0) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        birdY += birdVelocity;
        birdVelocity += gravity;

        if (!gameOver) {
            movePipes();
            checkCollision();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.orange);
        g.fillRect(0, 600 - 120, 800, 120);

        g.setColor(Color.green);
        g.fillRect(0, 600 - 150, 800, 30);

        g.setColor(Color.red);
        g.fillRect(100, birdY, 30, 30);

        for (Rectangle pipe : pipes) {
            g.setColor(Color.green.darker());
            g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        if (gameOver) {
            g.drawString("Game Over", 300, 300);
        } else {
            g.drawString(String.valueOf(score), 750, 50);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            birdVelocity = -10;
        } else if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            birdY = 250;
            pipes.clear();
            birdVelocity = 0;
            score = 0;
            gameOver = false;
            addPipe(true);
            addPipe(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}