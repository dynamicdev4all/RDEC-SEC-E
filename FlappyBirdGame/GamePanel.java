import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener {
    Image backgroundImg;
    Image topPipeImg;
    Image bottomPipeImg;
    Image baseImg;
    Bird bird = new Bird(this);
    Timer gameloop;
    int bgVelocity = 3;
    int bgx1 = 0;
    

    int bgx2 = 360;
    ArrayList<Pipe> pipes;
    Random random = new Random();
    Timer placePipeTimer;

    int pipeX = GameConstants.WIDTH;
    int pipeY = 0;
    int pipeWidth = 64; // scaled by 1/6
    int pipeHeight = 512;
    int velocityX = -4;
   public  boolean gameover=false;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img) {
            this.img = img;

        }
    }

    GamePanel() {
        setBounds(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
        loadImages();
        moveThings();
        setFocusable(true);
        addKeyListener(this);
        pipes = new ArrayList<Pipe>();
        placePipeTimer = new Timer(1500, (e) -> {
            placePipes();

        });
        placePipeTimer.start();
    }

    void moveThings() {
        gameloop = new Timer(1000 / 60, (e) -> {
            bird.moveFlap();
            bird.move();
            this.bgMove();
            this.pipeMove();
            repaint();
            if(gameover){
                gameloop.stop();
                placePipeTimer.stop();
            }

        });
        gameloop.start();
    }
    boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
               a.x + a.width > b.x &&   //a's top right corner passes b's top left corner
               a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
               a.y + a.height > b.y;    //a's bottom left corner passes b's top left corner
    }

    void bgMove() {
        if (bgx1 == -360)
            bgx1 = 360;
        bgx1 -= bgVelocity;
        if (bgx2 == -360)
            bgx2 = 360;
        bgx2 -= bgVelocity;

    }

    void pipeMove() {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
            if (collision(bird, pipe)) {
                gameover = true;
            }
        }
    }

    void loadImages() {
        backgroundImg = new ImageIcon(getClass().getResource("./Images/background-night.png")).getImage();
        baseImg = new ImageIcon(getClass().getResource("./Images/base.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./Images/pipeU-red.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./Images/pipe-red.png")).getImage();
    }

    void placePipes() {
        // (0-1) * pipeHeight/2.
        // 0 -> -128 (pipeHeight/4)
        // 1 -> -128 - 256 (pipeHeight/4 - pipeHeight/2) = -3/4 pipeHeight
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = GameConstants.HEIGHT / 4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // BackGround
        g.drawImage(backgroundImg, bgx1, 0, GameConstants.WIDTH, GameConstants.HEIGHT, null);
        g.drawImage(backgroundImg, bgx2, 0, GameConstants.WIDTH, GameConstants.HEIGHT, null);
        g.drawImage(baseImg, bgx1, GameConstants.HEIGHT - 50, GameConstants.WIDTH, 50, null);
        g.drawImage(baseImg, bgx2, GameConstants.HEIGHT - 50, GameConstants.WIDTH, 50, null);

        // Bird
        bird.paintImage(g);
        // Pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.velocityY = -9;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
