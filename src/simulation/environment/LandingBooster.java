package simulation.environment;

import static util.Colors.STEEL;
import static util.Setting.getPanelCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PVector;
import simulation.Object;
import util.Setting;

public class LandingBooster extends Object {
    
    private static final PVector default_pos = getPanelCenter();
    private static final Dimension default_dim = new Dimension(100, 100);

    private Ellipse2D.Double body;
    private Rectangle2D.Double verticalFins;
    private Rectangle2D.Double horizontalFins;
    private PApplet pa;
    private float altitude;

    public LandingBooster() {
        super(default_pos, default_dim);
        scale = 1;
        pa = new PApplet();
        altitude = 100;
    }

    @Override
    protected void setShape() {
        body = new Ellipse2D.Double(10, 10, 80, 80);
        verticalFins = new Rectangle2D.Double(45, 0, 10, 100);
        horizontalFins = new Rectangle2D.Double(0, 45, 100, 10);
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(STEEL.get());
        g.fill(verticalFins);
        g.fill(horizontalFins);

        g.setColor(Color.BLACK);
        g.draw(verticalFins);
        g.draw(horizontalFins);

        g.setColor(STEEL.get());
        g.fill(body);
        
        g.setColor(Color.BLACK);
        g.draw(body);
    }

    @Override
    public void update() {
        altitude = panel.getAltitude();
        move((int) (Math.cos(pa.noise(altitude)*20-10)*5), (int) (Math.sin(pa.noise(altitude)*20-10)*5));
        if (altitude <= 0) {
            if (PVector.dist(pos, getPanelCenter()) <= Setting.getPositionTolerance()*4) {
                panel.playDing();
                panel.setState(10);
            }
            else {
                panel.playFail();
                panel.setAltitude(100);
                pos = getPanelCenter();
            }
        }
    }

}
