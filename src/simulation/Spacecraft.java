package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;

import static others.Colors.*;
import static others.Setting.*;

public class Spacecraft extends Button {
    
    private static final PVector default_pos = new PVector(getPanelWidth() / 2, getPanelHeight() / 2);
    private static final Dimension default_dim = new Dimension(300, 720);

    private GeneralPath head;
    private Rectangle2D.Double body;
    private GeneralPath topLeft;
    private GeneralPath topRight;
    private GeneralPath bottomLeft;
    private GeneralPath bottomRight;

    public Spacecraft() {
        super(default_pos, default_dim);
        scale = 0.5;
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
        g.setColor(Color.RED);
        g.drawRect(0, 0, dim.width, dim.height);

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
}
