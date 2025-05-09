import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class Car {
    private int x, y, width, height;
    private Image carImage; 
    public Car(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.carImage = loadImage(imagePath);
    }

    private Image loadImage(String imagePath) {
        try {
            URL imageURL = getClass().getResource(imagePath); 
            if (imageURL != null) {
                System.out.println("Image URL: " + imageURL);
                return new ImageIcon(imageURL).getImage();
            } else {
                System.err.println("Image not found: " + imagePath);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            return null;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics g) {
            g.drawImage(carImage, x, y, width, height, null);
    }
    public void changeLane(int direction) {
        int newX = x + direction * 100; 
        if (newX >= 0 && newX <= 200) {
            x = newX;
        }
    }
    public void moveUp(int speed) {
        this.y -= speed;
    }

    public void resetPosition(int lane) {
        this.x = lane;  
        this.y = 500;   
    }
    public static String getRandomCarImage() {
        String[] carImages = {
            "/Users/ice/JAVA IDE PROGRAMS/CarGame/WhatsApp Ima2024-11-14 at 09.13.42.jpeg",
            "/Users/ice/JAVA IDE PROGRAMS/CarGame/WhatsApp Image 202-11-14 at 09.13.41.jpeg",
            "/Users/ice/JAVA IDE PROGRAMS/CarGame/WhatsApp Image 2024-11-14 at 09.13.41.jpeg",
            "/Users/ice/JAVA IDE PROGRAMS/CarGame/WhatsApp Image 2024-11-14 at 09.13.41.jpeg",
            "/Users/ice/JAVA IDE PROGRAMS/CarGame/WhatsApp Image 2024-11-14 at 09.13.42.jpeg",
            "/Users/ice/JAVA IDE PROGRAMS/CarGame/WhatsApp Image 2024-11-14 at 09.13.43.jpeg",
            "/Users/ice/JAVA IDE PROGRAMS/CarGame/Whatsmage 2024-11-14 at 09.13.42.jpeg"};
        int randomIndex = (int) (Math.random() * carImages.length);
        return carImages[randomIndex];
    }
}
