/*
 * The superclass of clickable objects:
 * Booster, Spacecraft, Satellite, Launch
 */

package simulation.button;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import processing.core.PVector;
import simulation.Object;

public abstract class Button extends Object {
    
    protected boolean atPosition = false;
    protected PVector targetPosition;

    protected Button(PVector pos, Dimension dim) {
        super(pos, dim);
    }

    public boolean clicked(MouseEvent e) {
        return  (e.getX() >= pos.x - dim.width/2*scale) &&
                    (e.getX() <= pos.x + dim.width/2*scale) &&
                    (e.getY() >= pos.y - dim.height/2*scale) &&
                    (e.getY() <= pos.y + dim.height/2*scale);
    }

    // overrided when needed
    public void drawPosition(Graphics2D g) {

    }

    public boolean atPosition() {
        return atPosition;
    }

    public void setAtPosition(boolean value) {
        atPosition = value;
    }

    public PVector getTargetPosition() {
        return targetPosition.copy();
    }

}
