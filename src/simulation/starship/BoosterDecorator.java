package simulation.starship;

import static util.Colors.STEEL;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class BoosterDecorator extends StarshipDecorator {
    
    private Rectangle2D.Double body;
    private Rectangle2D.Double fins;

    public BoosterDecorator(StarshipInterface starship) {
        super(starship);
    }

    @Override
    protected void setShape() {
        body = new Rectangle2D.Double(70/4, 0, 160/4, 800/4);
        fins = new Rectangle2D.Double(0, 100/4, 300/4, 20/4);
    }

    @Override
    public void decorate(Graphics2D g) {
        super.decorate(g);
        decorateWithBooster(g);
    }

    private void decorateWithBooster(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(pos.x-dim.width/2, pos.y-dim.height/2);
        g.translate(0, 180);
        g.scale(scale, scale);
        
        g.setColor(STEEL.get());
        g.fill(fins);

        g.setColor(Color.BLACK);
        g.draw(fins);

        g.setColor(STEEL.get());
        g.fill(body);

        g.setColor(Color.BLACK);
        g.draw(body);

        g.setTransform(at);
    }
}
