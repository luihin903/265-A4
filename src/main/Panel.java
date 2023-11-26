package main;

import static others.Colors.*;
import static others.Setting.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import javax.swing.Timer;

import processing.core.PVector;
import simulation.*;
import simulation.environment.Sky;

public class Panel extends JPanel implements ActionListener {
    
    private Sky sky;
    private Spacecraft spacecraft;
    private Booster booster;

    private Timer timer;

    public Panel() {
        this.setBackground(SKY_BLUE.get());
        setPreferredSize(getPanelDimension());



        sky = new Sky();
        spacecraft = new Spacecraft();
        booster = new Booster();


        timer = new Timer(getFPS(), this);
        timer.start();

        addMouseListener(new MyMouseAdapter());
        addMouseMotionListener(new MyMouseMotionAdapter());
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;

        drawGrid(g);
        
        sky.paint(g);
        // spacecraft.paint(g);
        booster.paint(g);

        if (!booster.atPosition()) {
            booster.drawPosition(g);
        }
        else if (!spacecraft.atPosition()) {
            spacecraft.drawPosition(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void drawGrid(Graphics2D g) {
        for (int i = 0; i < getPanelWidth(); i += 100) {
            g.drawLine(i, 0, i, getPanelHeight());
        }
        for (int i = 0; i < getPanelHeight(); i += 100) {
            g.drawLine(0, i, getPanelWidth(), i);
        }
    }

    private class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            if (!booster.atPosition()) {
                if (PVector.dist(booster.getPos(), booster.getTargetPosition()) < getPositionTolerance()) {
                    booster.setAtPosition(true);
                    booster.setPos(booster.getTargetPosition());
                }
            }
        }
    }

    private class MyMouseMotionAdapter extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!booster.atPosition() && booster.clicked(e)) {
                booster.setPos(e);
            }
        }
    }
}
