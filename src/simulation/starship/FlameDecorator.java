package simulation.starship;

import static util.Setting.getPanelCenter;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import simulation.environment.Flame;

public class FlameDecorator extends StarshipDecorator {
    
    Flame flame;

    public FlameDecorator(StarshipInterface base) {
        super(base);
    }

    @Override
    protected void setShape() {
        flame = new Flame();
    }

    @Override
    public void decorate(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(getPanelCenter().x, getPanelCenter().y);
        g.rotate(rotation*Math.PI/180);
        g.translate(-getPanelCenter().x, -getPanelCenter().y);
        super.decorate(g);
        decorateWithFlame(g);
        g.setTransform(at);
    }

    private void decorateWithFlame(Graphics2D g) {
        flame.paint(g);
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        flame.move(x, y);
    }
}
