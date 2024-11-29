import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Track {
    private Image backgroundImage;
    public Track() {
        backgroundImage = loadBackgroundImage("/Users/ice/JAVA IDE PROGRAMS/CarGame/WhatsAppe 2024-11-14 at 09.13.42.jpeg");
    }
    private Image loadBackgroundImage(String imagePath) {
        try {
            URL imageURL = getClass().getResource(imagePath);
            if (imageURL != null) {
                return new ImageIcon(imageURL).getImage();
            } else {
                System.err.println("Background image not found: " + imagePath);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            return null;
        }
    }
    public void draw(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, 300, 600);
        }
    }
}
