/*
 * A background when separating
 */

package simulation.environment;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import util.ImageLoader;

public class BlurredStarship extends simulation.Object{
    
    BufferedImage img;

    private static final PVector default_pos = new PVector(550, 350);
    private static final Dimension default_dim = new Dimension(1148, 881);

    public BlurredStarship() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {
        img = ImageLoader.loadImage("assets/Blurred_Starship.jpg");
    }

    @Override
    protected void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }
}
