import javax.swing.*;
public class DVDRestart {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DVD Bounce Animation");
        DVDBounce dvdBounce = new DVDBounce();
        
        frame.add(dvdBounce);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}
