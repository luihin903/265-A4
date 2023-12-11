/*
 * A decorator of satellite added on SpacecraftDecorator
 */

package simulation.starship;

import java.awt.Graphics2D;

public class SatelliteDecorator extends StarshipDecorator {
    
    public SatelliteDecorator(StarshipInterface base) {
        super(base);
    }

    @Override
    protected void setShape() {}

    @Override
    public void decorate(Graphics2D g) {
        super.decorate(g);
    }
}
