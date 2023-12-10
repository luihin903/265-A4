/*
 * Superclass of everything
 */

package simulation;

import static util.Setting.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import main.Panel;
import processing.core.PVector;

public abstract class Object {
    
    protected PVector pos;
    protected Dimension dim;
    protected double scale = 1;
    protected double rotation = 0;
    protected Panel panel;

    public Object(PVector pos, Dimension dim) {
        this.pos = pos.copy();
        this.dim = dim;
        setShape();
    }

    protected abstract void setShape();

    public void paint(Graphics2D g) {
        AffineTransform at = g.getTransform();

        g.translate(pos.x, pos.y);
        g.rotate(rotation * Math.PI / 180);
        g.translate(-pos.x, -pos.y);

        g.translate(pos.x - dim.width/2*scale, pos.y - dim.height/2*scale);
        g.scale(scale, scale);

        if (drawBoundingBox()) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, dim.width, dim.height);
        }

        draw(g);
        g.setTransform(at);
    }

    /**
     * (0, 0) is the top-left corner instead of center
     */
    protected abstract void draw(Graphics2D g);

    public void move(int x, int y) {
        pos.add(new PVector(x, y));
    }

    // to be overrided
    public void update() {}

    public void rotate(double alpha) {
        this.rotation += alpha;
    }

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

    public double getRotation() {
        return rotation;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public void setRotation(double theta) {
        this.rotation = theta;
    }
}
