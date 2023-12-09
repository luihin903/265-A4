/*
 * The launching button
 */

package simulation.button;

import static util.Colors.STEEL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;
import util.Setting;

public class Launch extends Button {
    
    private static final PVector default_pos = new PVector(200, 400);
    private static final Dimension default_dim = new Dimension(150, 150);

    private Rectangle2D.Double base;
    private Ellipse2D.Double button;

    private int counter;

    public Launch() {
        super(default_pos, default_dim);
        scale = 1;
        counter = Setting.FPS() * 12;
    }

    @Override
    protected void setShape() {
        base = new Rectangle2D.Double(0, 0, (int) (dim.width*scale), (int) (dim.height*scale));
        button = new Ellipse2D.Double(20, 20, dim.width-40, dim.height-40);
    }

    @Override
    protected void draw(Graphics2D g) {

        g.setColor(STEEL.get());
        g.fill(base);

        g.setColor(Color.BLACK);
        g.draw(base);

        g.setColor(Color.RED);
        g.fill(button);

        g.setColor(Color.BLACK);
        g.draw(button);
    }

    public void update() {
        this.count();
    }

    public void count() {
        counter --;
        if (counter == 0) {
            panel.setState(6);
        }
    }
}
