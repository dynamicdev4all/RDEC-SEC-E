// package ROAD;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AppPanel extends JPanel implements KeyListener {

    Restart restartButton;
    int x = 150;
    int y = 0;
    int y1 = 362;
    int y2 = 725;
    int width = 15;
    int height = 125;
    int stepsize = 10;
    int xAxis = 100;
    int yAxis = 550;
    int max_x, max_y;
    ArrayList<Car> cars;
    Random random;
    boolean gameOver = false;
    int score = 0;
    int[] lanePositions = { 60, 170, 260, 340, 450, 560, 650, 790 };
    int minGap = 400;
    Image icon;

    AppPanel(int w, int h) {

        this.max_x = w;
        this.max_y = h;

        random = new Random();
        icon = new ImageIcon("car.png").getImage();
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        cars = new ArrayList<>();
        addRandomCars(5);

        restartButton = new Restart("Restart", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame(); // Call the restart method when clicked
            }
        });
        restartButton.setBounds(max_x / 2 + 20, 500, 200, 50);
    }

    private void addRandomCars(int count) {
        for (int i = 0; i < count; i++) {
            int lane = 0;
            int y = -random.nextInt(max_y) - minGap;
            int speed = 5 + random.nextInt(10);
            boolean positionFound = false;

            // Try to place the new car in a non-overlapping position
            while (!positionFound) {
                lane = lanePositions[random.nextInt(lanePositions.length)];
                y = -random.nextInt(max_y); // Random initial y position above the screen
                positionFound = isLaneAvailable(lane, y, speed);
            }

            cars.add(new Car(lane, y, 100, 180, 5 + speed));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("Game Over!", max_x / 2 + 20, 350);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, max_x / 2 + 80, 420);

            if (!isAncestorOf(restartButton)) {
                add(restartButton);
                revalidate(); // Revalidate to ensure the button is displayed
                repaint();
            }
            return; // Stop drawing other components
        }

        g.setColor(Color.YELLOW);

        g.fillRect(x, y, width, height);
        g.fillRect(x, y1, width, height);
        g.fillRect(x, y2, width, height);

        g.fillRect(x + 300, y, width, height);
        g.fillRect(x + 300, y1, width, height);
        g.fillRect(x + 300, y2, width, height);

        g.fillRect(x + 600, y, width, height);
        g.fillRect(x + 600, y1, width, height);
        g.fillRect(x + 600, y2, width, height);

        if (y > max_y) {
            y = -200;
        }
        if (y1 > max_y) {
            y1 = -200;
        }
        if (y2 > max_y) {
            y2 = -200;
        }

        y += stepsize;
        y1 += stepsize;
        y2 += stepsize;

        g.drawImage(icon, xAxis, yAxis, 100, 200, this);

        for (Car car : cars) {
            car.draw(g);
            car.moveDown();
            if (car.y > max_y) {

                resetCarPosition(car);
                score += 10;
            }

            if (isCollision(car)) {
                gameOver = true;
                repaint();
                return;
            }

        }
        // g.setColor(Color.BLUE);
        // g.setFont(new Font("Arial", Font.BOLD, 30));
        // g.drawString("Score: " + score, 20, 30);

        call();
    }

    private boolean isCollision(Car car) {
        Rectangle playerCar = new Rectangle(xAxis, yAxis, 50, 150);
        Rectangle movingCar = new Rectangle(car.x, car.y + 5, car.width - 50, car.height - 30);

        return playerCar.intersects(movingCar);
    }

    private void resetCarPosition(Car car) {
        int lane = 0, y = -car.height;

        boolean positionFound = false;

        // Find a lane and y position that doesn't overlap with any other car
        while (!positionFound) {
            lane = lanePositions[random.nextInt(lanePositions.length)];
            y = -car.height - minGap; // Reset to the top of the screen

            positionFound = isLaneAvailable(lane, y, car.speed); // Only reset when position is valid
        }

        car.x = lane;
        car.y = y;
    }

    private boolean isLaneAvailable(int lane, int y, int speed) {
        for (Car otherCar : cars) {
            if (otherCar.x == lane) {
                // Calculate the distance between the two cars
                int distance = otherCar.y - y;
                if (distance < minGap) {
                    return false;
                }
                // Calculate the time it would take for the current car to reach the other car's
                // position
                double timeToCollision = distance / speed;
                // Calculate the other car's position at that time
                int otherCarFutureY = (int) (otherCar.y + otherCar.speed * timeToCollision);
                if (y + height > otherCarFutureY && y < otherCarFutureY + otherCar.height) {
                    return false; // Overlap detected
                }
            }
        }
        return true;
    }

    private void call() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        repaint();

    }

    private void restartGame() {
        gameOver = false;
        score = 0;
        xAxis = 100;
        yAxis = 550;

        // Reset cars' positions
        cars.clear();
        addRandomCars(3);

        // Remove the restart button from the panel
        remove(restartButton);

        // Refresh the panel to reflect the reset state
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (gameOver)
            return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                if (xAxis < 150 + max_x) { // Ensure it doesn't move out of bounds
                    xAxis += stepsize + 10;

                    if (yAxis > 70) {
                        yAxis -= stepsize - 8;
                    }

                }
            }
            case KeyEvent.VK_LEFT -> {
                if (xAxis >= 20) {
                    xAxis -= stepsize + 10;

                    if (yAxis > 70) {
                        yAxis -= stepsize - 8;
                    }

                }
            }
            case KeyEvent.VK_UP -> {
                if (yAxis - 50 >= 0) {
                    yAxis -= 20;

                }
            }
            case KeyEvent.VK_DOWN -> {
                if (yAxis + 300 < max_y) {
                    yAxis += 20;

                }
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    class Car {
        int x, y, width, height, speed;
        Image image;

        Car(int x, int y, int width, int height, int speed) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.speed = speed;

            image = new ImageIcon("opponent_car1.png").getImage();

        }

        void draw(Graphics g) {
            if (image != null) {
                g.drawImage(image, x, y, width, height, null);
            }

            else {
                g.setColor(Color.RED);
                g.fillRect(x, y, width, height);
            }
        }

        void moveDown() {
            y += speed;
        }
    }

}