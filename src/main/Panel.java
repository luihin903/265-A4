package main;

import static others.Colors.*;
import static others.Setting.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import simulation.Spacecraft;
import simulation.environment.Sky;

public class Panel extends JPanel implements ActionListener {
    
    private Sky sky;
    private Spacecraft spacecraft;

    private Timer timer;

    public Panel() {
        this.setBackground(SKY_BLUE.get());
        setPreferredSize(getPanelDimension());



        sky = new Sky();
        spacecraft = new Spacecraft();



        timer = new Timer(getFPS(), this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;

        sky.paint(g);
        spacecraft.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
