import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 800, HEIGHT = 600, GROUND_HEIGHT = 50;
    private final int PIPE_WIDTH = 100, PIPE_GAP = 180;
    private final int BIRD_WIDTH = 40, BIRD_HEIGHT = 40;
    private int birdX = 100, birdY = HEIGHT / 2;
    private int birdVelocity = 0, gravity = 1, jumpForce = -10;
    private ArrayList<Rectangle> pipes = new ArrayList<>();
    private boolean gameOver = false;
    private Timer gameTimer;
    private int score = 0;

    // Bird animation
    private BufferedImage birdSpriteSheet;
    private BufferedImage[] birdFrames;
    private int birdFrameIndex = 0; 

    public GamePanel() {
        setFocusable(true);
        setBackground(Color.white);
        addKeyListener(this);
        gameTimer = new Timer(20, this);
        loadBirdSpriteSheet();
        initializeGame();
    }

    private void loadBirdSpriteSheet() {
        try {
            birdSpriteSheet = ImageIO.read(new File("src/bird.jpg"));

            int frameWidth = 78;  // Frame width
            int frameHeight = 68; // Frame height
            int totalRows = 3;    // Number of rows
            int totalCols = 4;    // Number of columns
            int totalFrames = totalRows * totalCols;

            birdFrames = new BufferedImage[totalFrames];
            int index = 0;

            for (int row = 0; row < totalRows; row++) {
                for (int col = 0; col < totalCols; col++) {
                    int x = col * frameWidth;
                    int y = row * frameHeight;

                    // Debugging: Print each subimage's coordinates and dimensions
                    System.out.println("Extracting frame at: (" + x + ", " + y + "), size: " + frameWidth + "x" + frameHeight);

                    // Check bounds
                    if (x + frameWidth > birdSpriteSheet.getWidth() || y + frameHeight > birdSpriteSheet.getHeight()) {
                        throw new IllegalArgumentException("Frame dimensions exceed sprite sheet size.");
                    }

                    birdFrames[index++] = birdSpriteSheet.getSubimage(x, y, frameWidth, frameHeight);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading sprite sheet!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Frame dimensions exceed sprite sheet bounds!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeGame() {
        birdY = HEIGHT / 2;
        birdVelocity = 0;
        score = 0;
        pipes.clear();
        gameOver = false;
        addPipe(true);
        addPipe(true);
        gameTimer.start();
    }

    private void addPipe(boolean start) {
        int pipeHeight = new Random().nextInt(HEIGHT - PIPE_GAP - 200)+100;
        int pipeX = start ? WIDTH + pipes.size() * 300 : pipes.get(pipes.size() - 1).x + 300;
        pipes.add(new Rectangle(pipeX, 0, PIPE_WIDTH, pipeHeight));
        pipes.add(new Rectangle(pipeX, pipeHeight + PIPE_GAP, PIPE_WIDTH, HEIGHT - pipeHeight - PIPE_GAP - GROUND_HEIGHT));
    }

    private void movePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            pipe.x -= 5;
            if (pipe.x + PIPE_WIDTH < 0) {
                pipes.remove(i--);
                if (pipe.y == 0) addPipe(false);
            }
        }
    }

    private void checkCollision() {
        Rectangle bird = new Rectangle(birdX, birdY, BIRD_WIDTH, BIRD_HEIGHT);

        for (Rectangle pipe : pipes) {
            if (pipe.intersects(bird)) {
                gameOver = true;
                gameTimer.stop();
            }
        }
        if (birdY + BIRD_HEIGHT >= HEIGHT - GROUND_HEIGHT || birdY < 0) {
            gameOver = true;
            gameTimer.stop();
        }
    }

    private void updateScore() {
        for (Rectangle pipe : pipes) {
            if (pipe.x + PIPE_WIDTH == birdX) {
                score++;
            }
        }
    }
    private void updateBirdAnimation() {
            birdFrameIndex = (birdFrameIndex + 1) % birdFrames.length;
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            birdVelocity += gravity;
            birdY += birdVelocity;

            movePipes();
            checkCollision();
            updateScore();
            updateBirdAnimation();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(birdFrames[birdFrameIndex], birdX, birdY, BIRD_WIDTH, BIRD_HEIGHT, null);
        g.setColor(Color.GREEN);
        for (Rectangle pipe : pipes) {
            g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }
        g.setColor(Color.ORANGE);
        g.fillRect(0, HEIGHT - GROUND_HEIGHT, WIDTH, GROUND_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", WIDTH / 2 - 150, HEIGHT / 2 - 50);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press 'R' to Restart", WIDTH / 2 - 100, HEIGHT / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            birdVelocity = jumpForce;
        } else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            initializeGame();
        }
    }
}


