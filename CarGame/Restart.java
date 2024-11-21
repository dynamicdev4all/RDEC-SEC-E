// package ROAD;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Restart extends JButton {

    public Restart(String text, ActionListener listener) {
        super(text);
        setBounds(250, 400, 200, 50); // Set the position and size of the button
        addActionListener(listener); // Add the action listener for restart
    }
}