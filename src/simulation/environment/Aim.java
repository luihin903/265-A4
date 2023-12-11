/*
 * A target for the landingBooster to aim
 */

package simulation.environment;

import static util.Setting.getPanelCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import processing.core.PVector;
import simulation.Object;

public class Aim extends Object {
    
    private static final PVector default_pos = getPanelCenter();
    private static final Dimension default_dim = new Dimension(100, 100);

    private Ellipse2D.Double body;
    private Line2D.Double vertical;
    private Line2D.Double horizontal;

    public Aim() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {
        body = new Ellipse2D.Double(0, 0, 100, 100);
        vertical = new Line2D.Double(50, 0, 50, 100);
        horizontal = new Line2D.Double(0, 50, 100, 50);
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.draw(body);
        g.draw(vertical);
        g.draw(horizontal);
    }
}
