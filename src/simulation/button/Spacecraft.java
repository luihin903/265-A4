/*
 * The Straship spacecraft
 */

package simulation.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;
import simulation.MovingTask;

import static util.Colors.*;
import static util.Setting.*;

public class Spacecraft extends Button implements MovingTask {
    
    private boolean atPosition = false;
    private PVector targetPosition = new PVector(getPanelCenter().x, 220);

    private static final PVector default_pos = new PVector(250, 410);
    private static final Dimension default_dim = new Dimension(300, 720);

    private GeneralPath head;
    private Rectangle2D.Double body;
    private GeneralPath topLeft;
    private GeneralPath topRight;
    private GeneralPath bottomLeft;
    private GeneralPath bottomRight;

    public Spacecraft() {
        super(default_pos, default_dim);
        scale = 0.25;
    }

    @Override
    protected void setShape() {
        
        head = new GeneralPath();
        topLeft = new GeneralPath();
        topRight = new GeneralPath();
        bottomLeft = new GeneralPath();
        bottomRight = new GeneralPath();

        // head
        int xPoints[] = {70, 150, 230};
        int yPoints[] = {160, 0, 160};
        head.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i ++) {
            head.lineTo(xPoints[i], yPoints[i]);
        }
        head.closePath();

        // body
        body = new Rectangle2D.Double(70, 160, 160, 560);

        // topLeft
        xPoints = new int[] {150, 0, 0, 150};
        yPoints = new int[] {60, 140, 220, 220};
        topLeft.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i ++) {
            topLeft.lineTo(xPoints[i], yPoints[i]);
        }
        topLeft.closePath();

        // topRight
        xPoints = new int[] {150, 300, 300, 150};
        yPoints = new int[] {60, 140, 220, 220};
        topRight.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i ++) {
            topRight.lineTo(xPoints[i], yPoints[i]);
        }
        topRight.closePath();

        // bottomLeft
        xPoints = new int[] {100, 0, 0, 100};
        yPoints = new int[] {450, 550, 720, 720};
        bottomLeft.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i ++) {
            bottomLeft.lineTo(xPoints[i], yPoints[i]);
        }
        bottomLeft.closePath();

        // bottomRight
        xPoints = new int[] {200, 300, 300, 200};
        yPoints = new int[] {450, 550, 720, 720};
        bottomRight.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i ++) {
            bottomRight.lineTo(xPoints[i], yPoints[i]);
        }
        bottomRight.closePath();
    }

    @Override
    protected void draw(Graphics2D g) {
        

        g.setColor(STEEL.get());
        g.fill(topLeft);
        g.fill(topRight);
        g.fill(bottomLeft);
        g.fill(bottomRight);

        g.setColor(Color.BLACK);
        g.draw(topLeft);
        g.draw(topRight);
        g.draw(bottomLeft);
        g.draw(bottomRight);

        g.setColor(STEEL.get());
        g.fill(head);
        g.fill(body);

        g.setColor(Color.BLACK);
        g.draw(head);
        g.draw(body);

        g.setColor(STEEL.get());
        g.drawLine(72, 160, 229, 160);
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
