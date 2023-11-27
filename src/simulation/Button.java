/*
 * The superclass of clickable objects:
 * Booster, Spacecraft, Satellite, Launch
 */

package simulation;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import processing.core.PVector;

public abstract class Button extends Object {
    
    protected Button(PVector pos, Dimension dim) {
        super(pos, dim);
    }

    public boolean clicked(MouseEvent e) {
        return  (e.getX() >= pos.x - dim.width/2*scale) &&
                    (e.getX() <= pos.x + dim.width/2*scale) &&
                    (e.getY() >= pos.y - dim.height/2*scale) &&
                    (e.getY() <= pos.y + dim.height/2*scale);
    }

}
