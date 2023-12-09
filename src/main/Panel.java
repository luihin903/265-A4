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

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import factory.ButtonConcreteFactory;
import factory.ObjectConcreteFactory;
import factory.StarshipConcreteFactory;
import processing.core.PVector;
import simulation.Object;
import simulation.button.*;
import simulation.starship.StarshipInterface;
import util.MinimHelper;
import util.Setting;

public class Panel extends JPanel implements ActionListener {
    
    private int state = 0;
    private int launchTimer = 0;

    private ObjectConcreteFactory of;
    private ButtonConcreteFactory bf;
    private StarshipConcreteFactory sf;

    private Object cover;
    private Button start;
    private Object sky;
    private Object cloud;
    private Object ground;
    private Object tower;
    private Object block;
    private Button spacecraft;
    private Button booster;
    private Button satellite;
    private Button launchButton;
    private Object steam;
    private Object instruction;
    private Button restart;
    
    private JFrame frame;
    private Timer timer;

    private Minim minim;
    private AudioPlayer launchAudio, ding;

    private StarshipInterface starship;

    public Panel(JFrame frame) {
        this.frame = frame;
        this.setBackground(Color.WHITE);
        setPreferredSize(getPanelDimension());

        state = 0;
        launchTimer = 0;

        of = new ObjectConcreteFactory(this);
        bf = new ButtonConcreteFactory(this);
        sf = new StarshipConcreteFactory(this);

        cover = of.create("cover");
        start = bf.create("start");
        sky = of.create("sky");
        cloud = of.create("cloud");
        ground = of.create("ground");
        tower = of.create("tower");
        block = of.create("block");
        spacecraft = bf.create("spacecraft");
        booster = bf.create("booster");
        satellite = bf.create("satellite");
        launchButton = bf.create("launch");
        steam = of.create("steam");
        instruction = of.create("instruction");
        restart = bf.create("restart");

        starship = sf.create("basic");

        minim = new Minim(new MinimHelper());
        launchAudio = minim.loadFile("assets/Launch.mp3");
        ding = minim.loadFile("assets/Ding.mp3");

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
                starship.decorate(g);
                spacecraft.paint(g);
                satellite.paint(g);
                spacecraft.drawPosition(g);
                instruction.paint(g);
                break;

            case 3: // moving satellite
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                starship.decorate(g);
                satellite.paint(g);
                satellite.drawPosition(g);
                break;

            case 4: // ready to launch
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                starship.decorate(g);
                launchButton.paint(g);
                instruction.paint(g);
                break;

            case 5: // waiting to launch
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                starship.decorate(g);
                block.paint(g);
                steam.paint(g);
                instruction.paint(g);
                break;
            
            case 6: // launching
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                starship.decorate(g);
                block.paint(g);
                steam.paint(g);
                instruction.paint(g);
                break;

            case 7:
                restart.paint(g);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (state == 5) {
            launchButton.update();
        }

        if (state == 6) {
            starship.move(0, -5);
            launchTimer ++;
            if (launchTimer > 10*Setting.FPS()) {
                state = 7;
            }
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

    public int getLaunchTimer() {
        return this.launchTimer;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            
            if (!booster.atPosition()) {
                if (PVector.dist(booster.getPos(), booster.getTargetPosition()) < getPositionTolerance()) {
                    booster.setAtPosition(true);
                    booster.setPos(booster.getTargetPosition());
                    if (state == 1) state = 2;
                    ding.play(0);
                    starship = sf.create("booster");
                }
            }
            else if (!spacecraft.atPosition()) {
                if (PVector.dist(spacecraft.getPos(), spacecraft.getTargetPosition()) < getPositionTolerance()) {
                    spacecraft.setAtPosition(true);
                    spacecraft.setPos(spacecraft.getTargetPosition());
                    if (state == 2) state = 3;
                    ding.play(0);
                    starship = sf.create("spacecraft");
                }
            }
            else if (!satellite.atPosition()) {
                if (PVector.dist(satellite.getPos(), satellite.getTargetPosition()) < getPositionTolerance()) {
                    satellite.setAtPosition(true);
                    satellite.setPos(satellite.getTargetPosition());
                    if (state == 3) state = 4;
                    ding.play(0);
                    starship = sf.create("satellite");
                }
            }
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {
            if (state == 0 && start.clicked(e)) {
                state = 1;
            }
            
            if (state == 4 && launchButton.clicked(e)) {
                launchAudio.play(0);
                state = 5;
                starship = sf.create("flame");
            }

            if (state == 7 && restart.clicked(e)) {
                frame.dispose();
                frame = new App("Starlink Mission w/Starship");
            }
        }

    }

    private class MyMouseMotionAdapter extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            
            Button[] buttons = {booster, spacecraft, satellite};

            for (Button b : buttons) {
                if (!b.atPosition() && b.clicked(e)) {
                    b.setPos(e);
                }
            }
        }
    
    }

}
