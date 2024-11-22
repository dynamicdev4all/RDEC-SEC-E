import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Track {
    private Image backgroundImage;
    public Track() {
        backgroundImage = loadBackgroundImage("track-bg.jpg");
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
