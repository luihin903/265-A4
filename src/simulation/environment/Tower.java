/*
 * The integration tower at Starbase
 */

package simulation.environment;

import static util.Colors.STEEL;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;

import processing.core.PVector;
import simulation.Object;

public class Tower extends Object {
    
    private static final PVector default_pos = new PVector(700, 300);
    private static final Dimension default_dim = new Dimension(80, 400);

    public Tower() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {

    }

    @Override
    protected void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(10));
        g.setColor(STEEL.get());

        g.drawLine(0, 0, (int) (dim.width*scale), 0);
        g.drawLine(0, (int) (dim.height*scale), (int) (dim.width*scale), (int) (dim.height*scale));
        g.drawLine(0, 0, 0, (int) (dim.height*scale));
        g.drawLine((int) (dim.width*scale), 0, (int) (dim.width*scale), (int) (dim.height*scale));

        for (int i = 0; i < dim.height*scale; i += dim.height/4*scale) {
            g.drawLine(0, i, (int) (dim.width*scale), (int) (dim.height/4*scale + i));
            g.drawLine((int) (dim.width*scale), i, 0, (int) (dim.height/4*scale + i));
            g.drawLine(0, i, (int) (dim.width*scale), i);
        }

        g.setStroke(new BasicStroke(1));
    }
}
