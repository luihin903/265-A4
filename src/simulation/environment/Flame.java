/*
 * The flame when the Starship launch
 */

package simulation.environment;

import static util.Setting.getPanelCenter;
import static util.Setting.getPanelHeight;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import simulation.Object;
import util.ImageLoader;

public class Flame extends Object {
    
    private static final PVector default_pos = new PVector(getPanelCenter().x, getPanelHeight()-130+1725/2/2);
    private static final Dimension default_dim = new Dimension(140, 1725);

    private BufferedImage img;

    public Flame() {
        super(default_pos, default_dim);
        scale = 0.5;
    }

    @Override
    protected void setShape() {
        img = ImageLoader.loadImage("assets/Flame.png");
    }

    @Override
    protected void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }

    public void move(float x, float y) {
        pos.add(new PVector(x, y));
    }
}
