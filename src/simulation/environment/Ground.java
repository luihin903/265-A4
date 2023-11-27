/*
 * The ground on Earth at Starbase
 */

package simulation.environment;

import static util.Colors.SAND;
import static util.Setting.*;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;

public class Ground extends simulation.Object {
    
    private static final PVector default_pos = new PVector(getPanelCenter().x, getPanelHeight()/4*3);
    private static final Dimension default_dim = new Dimension(getPanelWidth(), getPanelHeight()/2);

    private Rectangle2D.Double body;

    public Ground() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {
        body = new Rectangle2D.Double(0, 0, dim.width*scale, dim.height*scale);
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(SAND.get());
        g.fill(body);
    }
}
