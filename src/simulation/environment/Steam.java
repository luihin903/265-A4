package simulation.environment;

import static util.Setting.getPanelCenter;
import static util.Setting.getPanelHeight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import processing.core.PVector;

public class Steam extends simulation.Object {
    
    private int diameter;

    private static final PVector default_pos = new PVector(getPanelCenter().x, getPanelHeight() - 130);
    private static final Dimension default_dim = new Dimension(400, 200);

    public Steam() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {}

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        draw(g, diameter);
    }

    private void draw(Graphics2D g, int d) {
        if (d > 1) {
            AffineTransform at = g.getTransform();
            g.translate(dim.width/2-d/2, dim.height/2-d/2/2);
            g.drawOval(0, 0, d, d/2);
            g.setTransform(at);
            draw(g, d-2);
        }
    }

    public void setTimer(int t) {
        diameter = t;
    }
}
