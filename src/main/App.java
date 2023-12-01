/*
 * The main app
 */

package main;

import javax.swing.JFrame;

public class App extends JFrame {
    
    public App(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0, 0);
        Panel panel = new Panel(this);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App("Starlink Mission w/Starship");
    }
}
