package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import processing.core.PVector;

public abstract class Object {
    
    protected PVector pos;
    protected Dimension dim;
    protected double scale = 1;
    protected double rotation = 0;

    public Object(PVector pos, Dimension dim) {
        this.pos = pos;
        this.dim = dim;
        setShape();
    }

    protected abstract void setShape();

    public void paint(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(pos.x - dim.width/2*scale, pos.y - dim.height/2*scale);
        g.scale(scale, scale);
        g.rotate(rotation);

        g.setColor(Color.RED);
        g.drawRect(0, 0, dim.width, dim.height);

        draw(g);
        g.setTransform(at);
    }

    protected abstract void draw(Graphics2D g);

    public PVector getPos() {
        return pos.copy();
    }

    public void setPos(float x, float y) {
        this.pos = new PVector(x, y);
    }

    public void setPos(PVector pos) {
        this.pos = pos.copy();
    }

    public void setPos(MouseEvent e) {
        setPos(e.getX(), e.getY());
    }
}
