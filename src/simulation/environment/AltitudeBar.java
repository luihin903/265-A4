/*
 * A progress bar when aiming
 */

package simulation.environment;

import static util.Colors.STEEL;
import static util.Setting.getPanelCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;
import simulation.Object;

public class AltitudeBar extends Object {
    
    private static final PVector default_pos = new PVector(1000, getPanelCenter().y);
    private static final Dimension default_dim = new Dimension(50, 200);

    private Ellipse2D.Double topEllipse;
    private Ellipse2D.Double bottomEllipse;
    private Rectangle2D.Double body;
    private double altitude;

    public AltitudeBar() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {
        topEllipse = new Ellipse2D.Double(20, 0, 10, 10);
        bottomEllipse = new Ellipse2D.Double(20, dim.height-10, 10, 10);
        body = new Rectangle2D.Double(20, 5, 10, dim.height-10);
    }

    @Override
    protected void draw(Graphics2D g) {

        altitude = panel.getAltitude() * 2;

        g.setColor(STEEL.get());
        g.fill(topEllipse);
        g.fill(bottomEllipse);

        g.setColor(Color.BLACK);
        g.draw(topEllipse);
        g.draw(bottomEllipse);

        g.setColor(STEEL.get());
        g.fill(body);

        g.setColor(Color.BLACK);
        g.drawLine(20, 5, 20, dim.height-5);
        g.drawLine(30, 5, 30, dim.height-5);

        Ellipse2D.Double left = new Ellipse2D.Double(0, 200-altitude, 5, 5);
        Ellipse2D.Double right = new Ellipse2D.Double(45, 200-altitude, 5, 5);
        Rectangle2D.Double rect = new Rectangle2D.Double(2.5, 200-altitude, 45, 5);

        g.setColor(Color.RED);
        g.fill(left);
        g.fill(right);


        g.setColor(Color.BLACK);
        g.draw(left);
        g.draw(right);

        g.setColor(Color.RED);
        g.fill(rect);

        g.setColor(Color.BLACK);
        g.drawLine((int) 2.5, (int) (200-altitude), (int) 47.5, (int) (200-altitude));
        g.drawLine((int) 2.5, (int) (200-altitude+5), (int) 47.5, (int) (200-altitude+5));
    }
}
