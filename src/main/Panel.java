/*
 * I used perlin noise for cloud and recursion for steam.
 * See README.md for image resources,
 * images without resources are created my me.
 */


/*
 * The panel
 */

package main;

import static util.Setting.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import processing.core.PVector;
import simulation.*;
import simulation.button.*;
import simulation.environment.*;
import util.Setting;

public class Panel extends JPanel implements ActionListener {
    
    private int state = 0;
    private int launchTimer = 0;

    private Cover cover;
    private Start start;
    private Sky sky;
    private Cloud cloud;
    private Ground ground;
    private Tower tower;
    private Flame flame;
    private Block block;
    private Spacecraft spacecraft;
    private Booster booster;
    private Satellite satellite;
    private Launch launch;
    private Steam steam;
    private Instruction instruction;
    private Restart restart;
    
    private JFrame frame;
    private Timer timer;

    public Panel(JFrame frame) {
        this.frame = frame;
        this.setBackground(Color.WHITE);
        setPreferredSize(getPanelDimension());

        state = 0;
        launchTimer = 0;

        cover = new Cover();
        start = new Start();
        sky = new Sky();
        cloud = new Cloud();
        ground = new Ground();
        tower = new Tower();
        flame = new Flame();
        block = new Block();
        spacecraft = new Spacecraft();
        booster = new Booster();
        satellite = new Satellite();
        launch = new Launch();
        steam = new Steam();
        instruction = new Instruction();
        restart = new Restart();


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
                cover.paint(g);
                start.paint(g);
                break;

            case 1: // moving booster
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                satellite.paint(g);
                booster.drawPosition(g);
                instruction.paint(g);
                break;
            
            case 2: // moving spacecraft
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                satellite.paint(g);
                spacecraft.drawPosition(g);
                instruction.paint(g);
                break;

            case 3: // moving satellite
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                satellite.paint(g);
                satellite.drawPosition(g);
                break;

            case 4: // ready to launch
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                launch.paint(g);
                instruction.paint(g);
                break;

            case 5: // launching
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                flame.paint(g);
                block.paint(g);
                steam.paint(g);
                instruction.paint(g);
                break;

            case 6:
                restart.paint(g);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (state == 5) {
            booster.move(0, -5);
            spacecraft.move(0, -5);
            flame.move(0, -5);
            steam.setTimer(launchTimer ++);
            if (launchTimer > 10*Setting.FPS()) {
                state = 6;
            }
        }

        instruction.setState(state);
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

            if (state == 6 && restart.clicked(e)) {
                frame.dispose();
                frame = new App("Starlink Mission w/Starship");
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
