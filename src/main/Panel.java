/*
 * I used perlin noise for cloud, landingBooster movement, and recursion for steam.
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private float altitude = 100;

    // keyboard control
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

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
    private Object blurredStarship;
    private Object boosterEngines;
    private Object spacecraftEngines;
    private Object aim;
    private Object landingBooster;
    private Object altitudeBar;
    
    private JFrame frame;
    private Timer timer;

    private Minim minim;
    private AudioPlayer launchAudio, ding, fail;

    private StarshipInterface starship;

    public Panel(JFrame frame) {
        this.frame = frame;
        this.setBackground(Color.WHITE);
        setPreferredSize(getPanelDimension());

        state = 0;
        launchTimer = 0;
        altitude = 100;

        up = false;
        down = false;
        left = false;
        right = false;

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
        blurredStarship = of.create("blurredStarship");
        boosterEngines = of.create("boosterEngines");
        spacecraftEngines = of.create("spacecraftEngines");
        Engine.init(this);
        aim = of.create("aim");
        landingBooster = of.create("landingBooster");
        altitudeBar = of.create("altitudeBar");

        starship = sf.create("basic");

        minim = new Minim(new MinimHelper());
        launchAudio = minim.loadFile("assets/Launch.mp3");
        ding = minim.loadFile("assets/Ding.mp3");
        fail = minim.loadFile("assets/Fail.mp3");

        timer = new Timer(getFPS(), this);
        timer.start();

        addMouseListener(new MyMouseAdapter());
        addMouseMotionListener(new MyMouseMotionAdapter());
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        
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
                instruction.paint(g);
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
            
            case 6: // Flying
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
                sky.paint(g);
                blurredStarship.paint(g);
                boosterEngines.paint(g);
                spacecraftEngines.paint(g);
                Engine.paintAll(g);
                instruction.paint(g);
                break;

            case 8: // separating
                sky.paint(g);
                spacecraft.paint(g);
                booster.paint(g);
                instruction.paint(g);
                break;

            case 9: // aiming
                sky.paint(g);
                aim.paint(g);
                landingBooster.paint(g);
                altitudeBar.paint(g);
                instruction.paint(g);
                break;

            case 10: // landing booster
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                booster.paint(g);
                instruction.paint(g);
                break;

            case 11:
                sky.paint(g);
                spacecraft.paint(g);
                satellite.paint(g);
                instruction.paint(g);
                break;

            case 12:
                sky.paint(g);
                cloud.paint(g);
                ground.paint(g);
                tower.paint(g);
                booster.paint(g);
                spacecraft.paint(g);
                instruction.paint(g);
                break;


            case 13:
                restart.paint(g);
                break;
        }
        // drawGrid(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (state == 5) {
            launchButton.update();
        }

        if (state == 6) {
            ground.move(0, 5);
            cloud.move(0, 5);
            tower.move(0, 5);
            block.move(0, 5);
            steam.move(0, 5);
            launchTimer ++;
            if (launchTimer > 10*Setting.FPS()) {
                cloud.move(0, 5);
                starship.rotate(0.1);
                if (starship.getRotation() >= 45) {
                    state = 7;
                }
            }
        }

        if (state == 8) {
            spacecraft.move(2, -2);
            booster.rotate(-0.5);
            if (booster.getRotation() <= -90) {
                state = 9;
            }
        }

        if (state == 9) {
            altitude -= 0.2;
            landingBooster.update();
        }

        if (state == 10) {
            booster.move(0, 4);
            if (booster.getPos().y >= getPanelHeight()-230) {
                try {
                    Thread.sleep(1000);
                } catch (Exception exception) {}
                state = 11;
                spacecraft.setPos(getPanelCenter());
                spacecraft.setRotation(90);                
            }
        }

        if (state == 11) {
            satellite.setAtPosition(false);
        }

        if (state == 12) {
            spacecraft.move(0, 4);
            if (spacecraft.getPos().y >= 220) {
                try {
                    Thread.sleep(1000);
                } catch (Exception exception) {}
                state = 13;
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

    public void playDing() {
        ding.play(0);
    }

    public void playFail() {
        fail.play(0);
    }

    public int getLaunchTimer() {
        return this.launchTimer;
    }

    public int getState() {
        return this.state;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setState(int state) {
        this.state = state;

        if (state == 8) {
            spacecraft.setPos(getPanelCenter().x + (float) Math.sqrt(5000), getPanelCenter().y - (float) Math.sqrt(5000));
            spacecraft.setRotation(45);
            booster.setPos(getPanelCenter().x - (float) Math.sqrt(4050), getPanelCenter().y + (float) Math.sqrt(4050));
            booster.setRotation(45);
        }

        if (state == 10) {
            ground.setPos(new PVector(getPanelCenter().x, getPanelHeight()/4*3));
            tower.setPos(new PVector(700, 300));
            cloud.setPos(new PVector(1000, 100));
            booster.setRotation(0);
            booster.setPos(new PVector(getPanelCenter().x, -200));
        }
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    private class MyMouseAdapter extends MouseAdapter {
  
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

            if (state == 7) {
                Engine.checkClicking(e);
            }

            if (state == 13 && restart.clicked(e)) {
                frame.dispose();
                frame = new App("Starlink Mission w/Starship");
            }

            // System.out.printf("{%d, %d},\n", e.getX(), e.getY());
        }

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
        
            if (state == 11 && PVector.dist(satellite.getPos(), satellite.getTargetPosition()) > 200) {
                ding.play(0);
                state = 12;
                spacecraft.setRotation(0);
                spacecraft.setPos(new PVector(getPanelCenter().x, -200));
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

    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            
            int c = e.getKeyCode();

            if (c == KeyEvent.VK_UP || c == KeyEvent.VK_W) {
                if (state == 9 && up == false) {
                    landingBooster.move(0, -20);
                }
                up = true;
            }
            if (c == KeyEvent.VK_DOWN || c == KeyEvent.VK_S) {
                if (state == 9 && down == false) {
                    landingBooster.move(0, 20);
                }
                down = true;
            }
            if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_A) {
                if (state == 9 && left == false) {
                    landingBooster.move(-20, 0);
                }
                left = true;
            }
            if (c == KeyEvent.VK_RIGHT || c == KeyEvent.VK_D) {
                if (state == 9 && right == false) {
                    landingBooster.move(20, 0);
                }
                right = true;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            
            int c = e.getKeyCode();

            if (c == KeyEvent.VK_UP || c == KeyEvent.VK_W) {
                up = false;
            }
            if (c == KeyEvent.VK_DOWN || c == KeyEvent.VK_S) {
                down = false;
            }
            if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_A) {
                left = false;
            }
            if (c == KeyEvent.VK_RIGHT || c == KeyEvent.VK_D) {
                right = false;
            }

        }
       
    }
}
