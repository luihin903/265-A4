/*
 * The Super Heavy booster
 */

package simulation.button;

import static util.Colors.STEEL;
import static util.Setting.getPanelCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;
import simulation.MovingTask;

public class Booster extends Button implements MovingTask {
    
    private boolean atPosition = false;
    private PVector targetPosition = new PVector(getPanelCenter().x, 410);

    private static final PVector default_pos = new PVector(400, 400);
    private static final Dimension default_dim = new Dimension(300, 800);

    private Rectangle2D.Double body;
    private Rectangle2D.Double fins;

    public Booster() {
        super(default_pos, default_dim);
        scale = 0.25;
    }

    @Override
    protected void setShape() {
        body = new Rectangle2D.Double(70, 0, 160, 800);
        fins = new Rectangle2D.Double(0, 100, 300, 20);
    }

    @Override
    protected void draw(Graphics2D g) {

        g.setColor(STEEL.get());
        g.fill(fins);

        g.setColor(Color.BLACK);
        g.draw(fins);

        g.setColor(STEEL.get());
        g.fill(body);

        g.setColor(Color.BLACK);
        g.draw(body);
    }

    public void drawPosition(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawRect((int) (targetPosition.x - dim.width/2*scale), (int) (targetPosition.y - dim.height/2*scale), (int) (dim.width*scale), (int) (dim.height*scale));
    }

    public boolean atPosition() {
        return atPosition;
    }

    public PVector getTargetPosition() {
        return targetPosition.copy();
    }

    public void setAtPosition(boolean b) {
        atPosition = b;
    }

    public void move(float x, float y) {
        pos.add(new PVector(x, y));
    }

}
