/*
 * The panel
 */

package main;

import static util.Colors.*;
import static util.Setting.*;

import java.awt.Color;
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
import simulation.button.Booster;
import simulation.button.Launch;
import simulation.button.Satellite;
import simulation.button.Spacecraft;
import simulation.button.Start;
import simulation.environment.*;

public class Panel extends JPanel implements ActionListener {
    
    private static int state = 0;

    private Start start;
    private Sky sky;
    private Ground ground;
    private Tower tower;
    private Flame flame;
    private Block block;
    private Spacecraft spacecraft;
    private Booster booster;
    private Satellite satellite;
    private Launch launch;
    private Instruction instruction;
    

    private Timer timer;

    public Panel() {
        this.setBackground(Color.WHITE);
        setPreferredSize(getPanelDimension());


        start = new Start();
        sky = new Sky();
        ground = new Ground();
        tower = new Tower();
        flame = new Flame();
        block = new Block();
        spacecraft = new Spacecraft();
        booster = new Booster();
        satellite = new Satellite();
        launch = new Launch();
        instruction = new Instruction();
        


        timer = new Timer(getFPS(), this);
        timer.start();

        addMouseListener(new MyMouseAdapter());
        addMouseMotionListener(new MyMouseMotionAdapter());
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;

        // drawGrid(g);

        switch (state) {

            case 0: // starting
                start.paint(g);
                break;

            case 1: // moving booster
                sky.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                satellite.paint(g);
                booster.drawPosition(g);
                break;
            
            case 2: // moving spacecraft
                sky.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                satellite.paint(g);
                spacecraft.drawPosition(g);
                break;

            case 3: // moving satellite
                sky.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                satellite.paint(g);
                satellite.drawPosition(g);
                break;

            case 4: // ready to launch
                sky.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                launch.paint(g);
                break;

            case 5: // launching
                sky.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                flame.paint(g);
                block.paint(g);
                break;
        }
        if (state > 0) instruction.paint(g);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (state == 5) {
            booster.move(0, -5);
            spacecraft.move(0, -5);
            flame.move(0, -5);
        }
        
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

    public static int getState() {
        return state;
    }

    private class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            
            if (!booster.atPosition()) {
                if (PVector.dist(booster.getPos(), booster.getTargetPosition()) < getPositionTolerance()) {
                    booster.setAtPosition(true);
                    booster.setPos(booster.getTargetPosition());
                    if (state == 1) state = 2;
                }
            }
            else if (!spacecraft.atPosition()) {
                if (PVector.dist(spacecraft.getPos(), spacecraft.getTargetPosition()) < getPositionTolerance()) {
                    spacecraft.setAtPosition(true);
                    spacecraft.setPos(spacecraft.getTargetPosition());
                    if (state == 2) state = 3;
                }
            }
            else if (!satellite.atPosition()) {
                if (PVector.dist(satellite.getPos(), satellite.getTargetPosition()) < getPositionTolerance()) {
                    satellite.setAtPosition(true);
                    satellite.setPos(satellite.getTargetPosition());
                    if (state == 3) state = 4;
                }
            }
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {
            if (state == 0 && start.clicked(e)) {
                state = 1;
            }
            
            if (state == 4 && launch.clicked(e)) {
                state = 5;
            }
        }

    }

    private class MyMouseMotionAdapter extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            
            MovingTask[] objects = {booster, spacecraft, satellite};

            for (MovingTask o : objects) {
                if (!o.atPosition() && o.clicked(e)) {
                    o.setPos(e);
                }
            }

            // if (!booster.atPosition() && booster.clicked(e)) {
            //     booster.setPos(e);
            // }
            // if (!spacecraft.atPosition() && spacecraft.clicked(e)) {
            //     spacecraft.setPos(e);
            // }
        }
    }
}
