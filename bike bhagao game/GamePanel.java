import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    private List<Car> cars;      
    private Track track;         
    private Timer timer;         
    private int score;           
    private boolean gameOver;    
    private boolean accelerating; 
    private boolean braking;     
    private Random random; 
    private int normalSpeed = 6;
    private int accelerationRate = 3; 
    private int currentSpeed = normalSpeed;
    private int decelerationRate = 1;  
    private int playerOriginalYPosition;
    private int trackWidth = 300;
    private int laneWidth = 100; 

    public GamePanel() {
        setFocusable(true);
        requestFocusInWindow(); 
        setPreferredSize(new Dimension(400, 600));
        score = 0;
        gameOver = false;
        accelerating = false;
        braking = false;
        random = new Random();
        track = new Track();
        cars = new ArrayList<>();
        Car playerCar = new Car(100, 520, 70, 80, "a1.jpg");
        cars.add(playerCar);
        playerOriginalYPosition = playerCar.getY();
        for (int i = 0; i < 3; i++) {
            int lane = random.nextInt(3) * 100;
            int yPosition = random.nextInt(200)+100;
            String randomCarImagePath = Car.getRandomCarImage();
            cars.add(new Car(lane, yPosition, 50, 80, randomCarImagePath));
        }

        setupKeyBindings();
        timer = new Timer(20, e -> {
            if (!gameOver) {
                updateCarPositions();
                checkCollisions();
                repaint();
            }
        });
        timer.start();
    }

    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    cars.get(0).changeLane(-1);
                    if (cars.get(0).getX() < 0) {
                        cars.get(0).setX(0);  
                    }
                    repaint();
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    cars.get(0).changeLane(1); 
                    if (cars.get(0).getX() > trackWidth - cars.get(0).getWidth()) {
                        cars.get(0).setX(trackWidth - cars.get(0).getWidth()); 
                    }
                    repaint();
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("S"), "brake");
        actionMap.put("brake", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    braking = true;
                    currentSpeed = 0 ;
                    accelerating = false;
                    repaint();
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("W"), "accelerate");
        inputMap.put(KeyStroke.getKeyStroke("released W"), "releaseAccelerate");
        actionMap.put("accelerate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    accelerating = true;
                    currentSpeed = Math.min(currentSpeed + accelerationRate, 20);
                    repaint();
                }
            }
        });

        actionMap.put("releaseAccelerate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    accelerating = false;
                    repaint();
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("R"), "restartGame");
        actionMap.put("restartGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver) {
                    restartGame();
                    repaint();
                }
            }
        });
    }
    private void updateCarPositions() {
        for (int i = 1; i < cars.size(); i++) {
            Car car = cars.get(i);
            car.moveUp(normalSpeed);
            if (car.getY() < -car.getHeight()) {
                car.setY(415 + random.nextInt(155));
                car.setX(random.nextInt(3) * 100); 
                score += 10;
            }
        }
        Car playerCar = cars.get(0);
        if (accelerating) {
            playerCar.moveUp(currentSpeed);
        } else {
            if (currentSpeed > normalSpeed) {
                currentSpeed -= decelerationRate;
                if (currentSpeed < normalSpeed) {
                    currentSpeed = normalSpeed;  
                }
            }
            playerCar.moveUp(currentSpeed);
        }
        if (playerCar.getY() < 0) {
            playerCar.setY(playerOriginalYPosition);  
        }
    }


    private void checkCollisions() {
        Car playerCar = cars.get(0);
        for (int i = 1; i < cars.size(); i++) {
            Car otherCar = cars.get(i);
            if (playerCar.getY() < otherCar.getY() + otherCar.getHeight()
                    && playerCar.getY() + playerCar.getHeight() > otherCar.getY()
                    && playerCar.getX() == otherCar.getX()) {
                gameOver = true;
                timer.stop();
            }
        }
    }

    private void restartGame() {
        score = 0;
        gameOver = false;
        for (int i = 1; i < cars.size(); i++) {
            cars.get(i).resetPosition(random.nextInt(3) * 100);
            cars.get(i).setY(200 + random.nextInt(200));
        }
        cars.get(0).resetPosition(100);
        cars.get(0).setY(525);
        playerOriginalYPosition = cars.get(0).getY();
        currentSpeed = normalSpeed;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        track.draw(g);
        for (Car car : cars) {
            car.draw(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score : " + score, 10, 30);
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 50, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Final Score: " + score, 100, 350);
            g.drawString("Press R to Restart", 90, 400);
        }
    }
}

