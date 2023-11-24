package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import processing.core.PVector;

public abstract class Object {
    
    protected PVector pos;
    protected Dimension dim;

    public Object(PVector pos, Dimension dim) {
        this.pos = pos;
        this.dim = dim;
    }

    public void paint(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(pos.x - dim.width/2, pos.y - dim.height/2);
        draw(g);
        g.setTransform(at);
    }

    protected abstract void draw(Graphics2D g);
}
