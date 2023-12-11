/*
 * A decorator of spacecraft added on BoosterDecorator
 */

package simulation.starship;

import java.awt.Graphics2D;

import simulation.button.Spacecraft;

public class SpacecraftDecorator extends StarshipDecorator {
    
    Spacecraft spacecraft;

    public SpacecraftDecorator(StarshipInterface base) {
        super(base);
    }

    @Override
    protected void setShape() {
        spacecraft = new Spacecraft();
        spacecraft.setPos(pos.x, pos.y-100);
    }

    @Override
    public void decorate(Graphics2D g) {
        super.decorate(g);
        decorateWithSpacecraft(g);
    }

    private void decorateWithSpacecraft(Graphics2D g) {
        spacecraft.paint(g);
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        spacecraft.move(x, y);
    }
}
