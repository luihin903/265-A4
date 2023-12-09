package simulation.starship;

import static util.Setting.getPanelCenter;
import static util.Setting.getPanelHeight;

import java.awt.Dimension;
import java.awt.Graphics2D;

import processing.core.PVector;
import simulation.Object;

public class BasicStarship extends Object implements StarshipInterface {
    
    private static final PVector default_pos = new PVector(getPanelCenter().x, getPanelHeight()-130-380/2);
    private static final Dimension default_dim = new Dimension(75, 380);

    public BasicStarship() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {
        
    }

    @Override
    public void draw(Graphics2D g) {}

    @Override
    public void decorate(Graphics2D g) {
        // g.drawRect((int) (pos.x-dim.width/2), (int)(pos.y-dim.height/2), dim.width, dim.height);
    }
}
