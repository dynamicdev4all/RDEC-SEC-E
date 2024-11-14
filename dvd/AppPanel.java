import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.*;

import javax.swing.*;


public class AppPanel extends JPanel {
    BufferedImage bufferImage;
    Timer timer;
    int xAxis= 0;
    int yAxis= 0;
    int a,b=0;
    AppPanel() {
        setSize(570, 570);
        // setBackground(Color.GREEN);
        showBg();


        loopPaint();
        
    }

    void showBg() {
        try {
            bufferImage = ImageIO.read(AppPanel.class.getResource("obito.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(bufferImage, xAxis,yAxis,50,50, null);

    }


    void loopPaint(){
      timer = new Timer(30, (abcd)->{
       
                if(b<=480){
            yAxis= yAxis + 12;
            b= b + 12;
        }
        else if(b<=960 ){
            yAxis= yAxis - 12;
            b= b + 12;
        }
        if(a<=500 ){
            xAxis= xAxis + 5;
            a= a + 5;
        }
        else if(a<=1000 ){
            xAxis= xAxis - 5;
            a= a + 5;
        }
        if(a>=1000){
            a=0;
            xAxis=0;
        }
        if(b>=960){
          b=0;
          yAxis=0;
        }
       repaint();
      });  

      timer.start();
    }
}