// package ROAD;

import java.awt.*;

import javax.swing.*;

public class CarGame extends JFrame {
    CarGame() {
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        AppPanel panel = new AppPanel(600, 900);

        panel.setBackground(new Color(0, 0, 0));
        add(panel);

    }

    public static void main(String[] args) {
        new CarGame().setVisible(true);
    }
}