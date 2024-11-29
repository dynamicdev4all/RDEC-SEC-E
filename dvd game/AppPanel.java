import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AppPanel extends JPanel {

    BufferedImage bufferImage;
    Timer timer;
    int xAxis = 0;
    int yAxis = 0;
    int xDirection = 5; 
    int yDirection = 5;  
    int imgWidth =150 ;  
    int imgHeight =150; 

    AppPanel() {
        setSize(490
        , 467);
        setBackground(Color.BLACK);  
        showBgImage();
        recall();
    }
    

    void showBgImage() {
        try {
            bufferImage = ImageIO.read(AppPanel.class.getResource("dvd2.jpeg"));
        } catch (IOException e) {
            System.out.println("No Image Found");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics brush) {
        super.paintComponent(brush); 
        brush.drawImage(bufferImage, xAxis, yAxis, imgWidth, imgHeight, null);
    }

    void recall() {
        timer = new Timer(50, e -> {
            xAxis += xDirection;
            yAxis += yDirection;

            if (xAxis <= 0 || xAxis + imgWidth >= getWidth()) {
                xDirection = -xDirection; 
            }

            if (yAxis <= 0 || yAxis + imgHeight >= getHeight()) {
                yDirection = -yDirection;
            }
            repaint();
        });
        timer.start();
    }
}