package simulation.environment;

import static others.Setting.*;

import java.awt.Dimension;
import java.awt.Graphics2D;

import processing.core.PVector;

public class Sky extends simulation.Object {
    
    public Sky() {
        super(new PVector(0, 0), getPanelDimension());
    }

    @Override
    protected void draw(Graphics2D g) {
        g.fillRect(0, 0, dim.width, dim.height);
    }
}
