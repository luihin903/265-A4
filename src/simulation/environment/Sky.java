/*
 * The sky
 */

package simulation.environment;

import static util.Colors.SKY_BLUE;
import static util.Setting.*;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Sky extends simulation.Object {
    
    Rectangle2D.Double body;

    public Sky() {
        super(getPanelCenter(), getPanelDimension());
        scale = 1;
    }

    @Override
    protected void setShape() {
        body = new Rectangle2D.Double(0, 0, dim.width, dim.height);
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(SKY_BLUE.get());
    }
}
