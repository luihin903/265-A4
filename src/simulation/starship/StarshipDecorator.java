/*
 * The abstract decorator
 */

package simulation.starship;

import static util.Setting.getPanelCenter;
import static util.Setting.getPanelHeight;

import processing.core.PVector;
import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class StarshipDecorator implements StarshipInterface {
    
    private static final PVector default_pos = new PVector(getPanelCenter().x, getPanelHeight()-130-380/2);
    private static final Dimension default_dim = new Dimension(75, 380);

    StarshipInterface baseStarship;
    protected PVector pos;
    protected Dimension dim;
    protected double scale;
    protected double rotation;

    public StarshipDecorator(StarshipInterface _baseStarship) {
        baseStarship = _baseStarship;
        pos = default_pos.copy();
        dim = default_dim;
        scale = 1;
        setShape();
    }

    protected abstract void setShape();

    public void decorate(Graphics2D g) {
        baseStarship.decorate(g);
    }

    @Override
    public void move(int x, int y) {
        pos.add(new PVector(x, y));
        baseStarship.move(x, y);
    }

    @Override
    public void rotate(double theta) {
        rotation += theta;
    }

    @Override
    public double getRotation() {
        return rotation;
    }
}
