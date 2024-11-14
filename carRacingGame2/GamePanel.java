import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
    ResourceBundle rb = ResourceBundle.getBundle("Car");
    
    String cars[] = { rb.getString("car0"), rb.getString("car1"), rb.getString("car2"), rb.getString("car3"),
            rb.getString("car4"), rb.getString("car5"), rb.getString("car6") };
    Image bgImage;// Left tree area width=190px ,right tree area width = 200px
    Image PlayerCar;
    Timer timer;
    Timer timer2;
    int player_x = 350;
    int player_y = 540;
    int y1 = -680;
    int y2 = 0;
    JLabel player;
    JButton back;
    GameFrame gf;
    Car car1;
    Car car2;
    Car car3;
    Random random = new Random();
    boolean gameover = false;

    GamePanel(GameFrame gf) {
        this.gf = gf;
        setSize(800, 680);
        setLayout(null);
        loadImages();
        loadPlayer();
        controlPlayer();
        moveBg();
        car1 = randomCarGenerator(car1);
        car2 = randomCarGenerator(car2);
        car3 = randomCarGenerator(car3);
        setPos();
        checkGameOver();
        car1.move();
        

        backButton();

        setFocusable(true);

    }

    void checkGameOver() {
        timer = new Timer(30, (a) -> {
            if (Math.abs(player_x - car1.x) - 100 <= -10 && Math.abs(player_y - car1.y) - 100 <= -10) {
                gameover = true;
                timer2.stop();
                car1.timer.stop();
                car2.timer.stop();
                car3.timer.stop();
                timer.stop();

            }
            if (Math.abs(player_x - car2.x) - 100 <= -10 && Math.abs(player_y - car2.y) - 100 <= -10) {
                gameover = true;
                timer2.stop();
                car1.timer.stop();
                car2.timer.stop();
                car3.timer.stop();
                timer.stop();

            }
            if (Math.abs(player_x - car3.x) - 100 <= -10 && Math.abs(player_y - car3.y) - 100 <= -10) {
                gameover = true;
                timer2.stop();
                car1.timer.stop();
                car2.timer.stop();
                car3.timer.stop();
                timer.stop();

            }

        });
        timer.start();

    }

    void setPos() {
        if (car1.x == car2.x && Math.abs((car1.y - car2.y)) - 100 <= 0) {
            if (car1.xDI != 0) {
                car1.xDI++;

            } else {
                car1.xDI--;

            }

        }
        if (car1.x == car3.x && Math.abs((car1.y - car3.y)) - 100 <= 0) {
            if (car1.xDI == 0) {
                car1.xDI++;

            } else {
                car1.xDI--;

            }

        }
        if (car2.x == car3.x && Math.abs((car2.y - car3.y)) - 100 <= 0) {
            if (car2.xDI == 0) {
                car2.xDI++;

            } else {
                car2.xDI--;

            }

        }
        // if (cxpos1 < 2 && cxpos2 < 2 && cxpos3 < 2) {
        // if (cxpos1 == 0 && cxpos2 == 0 && cxpos3 == 1) {
        // cxpos3++;
        // cxpos2++;
        // } else if (cxpos1 == 0 && cxpos2 == 1 && cxpos3 == 0) {
        // cxpos3++;
        // cxpos2++;
        // } else if (cxpos1 == 1 && cxpos2 == 0 && cxpos3 == 0) {
        // cxpos1++;
        // cxpos2++;
        // }
        // }

    }

    void moveBg() {
        timer2 = new Timer(30, (a) -> {
            if (y1 == 680)
                y1 = -680;
            y1 += 20;
            if (y2 == 680)
                y2 = -680;
            y2 += 20;
            repaint();

        });
        timer2.start();

    }

    Car randomCarGenerator(Car car) {

        int xDI = random.nextInt(4);
        int yDI = random.nextInt(5);
        car = new Car(xDI, yDI, cars[random.nextInt(7)], 10, this);
        return car;

    }

    int setSpeed(int count) {
        if (count <= 7) {
            return 5;

        } else if (count > 7 && count <= 14) {
            return 10;
        } else if (count > 14 && count <= 21) {
            return 15;
        } else if (count > 21 && count <= 40) {
            return 15;
        } else {
            return 30;
        }
    }

    void backButton() {
        back = new JButton("BACK");
        back.setBounds(680, 10, 100, 50);
        back.addActionListener(this);
        back.setBackground(Color.white);
        this.add(back);

    }

    public void actionPerformed(ActionEvent ae) {

        gf.dispose();
    }

    void controlPlayer() {

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_RIGHT &&  !gameover) {

                    if (player_x < 500) {
                        player_x = player_x + 40;
                        player.setLocation(player_x, player_y);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && !gameover) {

                    if (player_x > 190) {
                        player_x = player_x - 40;
                        player.setLocation(player_x, player_y);
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_UP && !gameover) {

                    if (player_y >= 0) {
                        player_y = player_y - 20;
                        player.setLocation(player_x, player_y);

                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && !gameover) {
                    if (player_y <= 540) {
                        player_y = player_y + 20;
                        player.setLocation(player_x, player_y);
                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
    }

    void loadPlayer() {
        player = new JLabel();
        player.setIcon(new ImageIcon(PlayerCar));
        player.setBounds(player_x, player_y, 100, 100);
        add(player);

    }

    void loadImages() {
        try {
            BufferedImage bufferBgImg = ImageIO.read(HomePanel.class.getResource("./Images/roadImg.jpg"));
            bgImage = bufferBgImg.getScaledInstance(800, 680, Image.SCALE_DEFAULT);

            BufferedImage bufferPlayerCarImg = ImageIO.read(HomePanel.class.getResource("./Images/Player1.png"));
            PlayerCar = bufferPlayerCarImg.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        } catch (Exception e) {
            System.out.println("Image can't find");
        }
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(bgImage, 0, y1, null);
        g.drawImage(bgImage, 0, y2, null);
        car1.paintImage(g);
        car2.paintImage(g);
        car3.paintImage(g);

    }
}