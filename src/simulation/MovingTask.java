/*
 * Implemented by objects need to drag by users
 */

package simulation;

import java.awt.event.MouseEvent;

public interface MovingTask {
    
    public abstract boolean atPosition();
    public abstract boolean clicked(MouseEvent e);
    public abstract void setPos(MouseEvent e);
}
