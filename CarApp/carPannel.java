import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class carPannel extends JPanel {
    ImageIcon gif;
    BufferedImage car;
    List<Car> hudleCars;
    Timer timer;
    boolean isGameOver=false;
    int xAxis=255;
    int yAxis=400;
    int passCount=0;
    Random random = new Random();
    int time=random.nextInt(1501)+500;
    carPannel(){
        setSize(500, 500);
        hudleCars=new ArrayList<>();
        showBg();
        loopPaint();
        keyboard();
        setFocusable(true);
    }
    void  showBg() {
        try{
            car=ImageIO.read(carPannel.class.getResource("upCAR.jpg"));
            gif=new ImageIcon(carPannel.class.getResource("road.gif"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(gif.getImage(),20,0,500,500,null);
        g.drawImage(car,xAxis,yAxis,50,100,null);
        for(Car hurdelcar:hudleCars){
            g.drawImage(hurdelcar.carImage, hurdelcar.carXaxis, hurdelcar.carYaxis, 50, 70, hurdelcar);   
        }
        g.setColor(Color.WHITE); 
        g.setFont(new Font("Arial", Font.BOLD, 20));
         g.drawString("SCORE: " + passCount, 20, 20);
    }
    void loopPaint() {
        timer = new Timer(50, (a) -> {
            if(isCollide(xAxis, yAxis, hudleCars)){
                timer.stop();
                isGameOver=true;
                JOptionPane.showMessageDialog(this, "Game Over!!");
            }
            moveCars();
            repaint();
        });
        timer.start();
        Timer hurdleTimer = new Timer(time, (e) -> { 
            hudleCars.add(new Car()); 
        }); 
        hurdleTimer.start(); 
       }
    
    public void keyboard() {
        System.out.println("CAR GAME");
        

        addKeyListener(new KeyListener() {
        
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if(isGameOver){
                    return;
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if( xAxis<400){
                            xAxis += 10;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(xAxis>90){
                            xAxis -= 10;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(yAxis>0){
                            yAxis -= 10;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(yAxis<400){
                            yAxis += 10;
                        }
                        break;        
                }
                repaint();
                // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                xAxis+=0;
                // throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }

        });
    }
    
    public boolean isCollide(int xAxis,int yAxis, List<Car> hurdleCars) { 
        int carWidth = 50; 
        int carHeight = 72; 
         int carX1 = xAxis; 
         int carX2 = xAxis + carWidth; 
         int carY1 = yAxis; 
         int carY2 = yAxis + carHeight; 
         for (Car hurdleCar : hurdleCars) { 
            int otherCarX1 = hurdleCar.carXaxis; 
            int otherCarX2 = hurdleCar.carXaxis+ carWidth; 
            int otherCarY1 = hurdleCar.carYaxis; 
            int otherCarY2 = hurdleCar.carYaxis + carHeight; 
            if (carX1 < otherCarX2 && carX2 > otherCarX1 && carY1 < otherCarY2 && carY2 > otherCarY1) { 
                return true; 
            } 
        }
         return false; 
        }
    
    public void moveCars() { 
        int carSpeed = 5; 
        List<Car> toRemove = new ArrayList<>(); 
        for (Car hurdleCar : hudleCars) { 
            hurdleCar.carYaxis += carSpeed; 
            if (hurdleCar.carYaxis > 450) { 
                toRemove.add(hurdleCar);
                passCount++; 
            } 
        } 
        hudleCars.removeAll(toRemove);
     }
}