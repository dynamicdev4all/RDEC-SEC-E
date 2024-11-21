import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int PIPE_WIDTH = 80;
    private final int PIPE_GAP = 200;
    private final int BIRD_SIZE = 20;
    private final int GRAVITY = 2;
    private final int JUMP_STRENGTH = -15;
    
    private Timer timer;
    private int birdY = HEIGHT / 2;
    private int birdVelocity = 0;
    private ArrayList<Rectangle> pipes;
    private boolean gameOver = false;
    private int score = 0;

    public FlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
                    birdVelocity = JUMP_STRENGTH;
                } else if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    restartGame();
                }
            }
        });
        
        pipes = new ArrayList<>();
        generatePipes();
        timer = new Timer(20, this);
        timer.start();
    }

    private void generatePipes() {
        pipes.clear();
        for (int i = 0; i < 5; i++) {
            int x = WIDTH + i * 200;
            int height = (int) (Math.random() * (HEIGHT - PIPE_GAP - 100)) + 50;
            pipes.add(new Rectangle(x, 0, PIPE_WIDTH, height));
            pipes.add(new Rectangle(x, height + PIPE_GAP, PIPE_WIDTH, HEIGHT - height - PIPE_GAP));
        }
    }

    private void restartGame() {
        gameOver = false;
        birdY = HEIGHT / 2;
        birdVelocity = 0;
        score = 0;
        generatePipes();
        timer.restart();
    }

    private void checkCollision() {
        Rectangle bird = new Rectangle(WIDTH / 4 - BIRD_SIZE / 2, birdY, BIRD_SIZE, BIRD_SIZE);
        for (Rectangle pipe : pipes) {
            if (bird.intersects(pipe)) {
                gameOver = true;
                timer.stop();
            }
        }
        if (birdY <= 0 || birdY >= HEIGHT - BIRD_SIZE) {
            gameOver = true;
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        birdY += birdVelocity;
        birdVelocity += GRAVITY;

        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            pipe.x -= 5;
            if (pipe.x + PIPE_WIDTH < 0) {
                pipes.remove(i--);
                if (i % 2 == 0) {
                    score++;
                    int x = pipes.get(pipes.size() - 1).x + 200;
                    int height = (int) (Math.random() * (HEIGHT - PIPE_GAP - 100)) + 50;
                    pipes.add(new Rectangle(x, 0, PIPE_WIDTH, height));
                    pipes.add(new Rectangle(x, height + PIPE_GAP, PIPE_WIDTH, HEIGHT - height - PIPE_GAP));
                }
            }
        }

        checkCollision();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw bird
        g.setColor(Color.YELLOW);
        g.fillOval(WIDTH / 4 - BIRD_SIZE / 2, birdY, BIRD_SIZE, BIRD_SIZE);

        // Draw pipes
        g.setColor(Color.GREEN);
        for (Rectangle pipe : pipes) {
            g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", WIDTH / 2 - 150, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press Enter to Restart", WIDTH / 2 - 130, HEIGHT / 2 + 40);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}