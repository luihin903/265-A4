package simulation.environment;

import static others.Colors.SKY_BLUE;
import static others.Setting.*;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;

public class Sky extends simulation.Object {
    
    Rectangle2D.Double body;

    public Sky() {
        super(new PVector(0, 0), getPanelDimension());
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
