package simulation.starship;

import java.awt.Graphics2D;

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
        super.decorate(g);
        decorateWithFlame(g);
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
