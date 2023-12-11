/*
 * A button to start the simulation
 */

package simulation.button;

import static util.Setting.getPanelCenter;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import util.ImageLoader;

public class Start extends Button {
    
    private static final PVector default_pos = getPanelCenter();
    private static final Dimension default_dim = new Dimension(256, 256);

    private BufferedImage img;

    public Start() {
        super(default_pos, default_dim);
    }

    @Override
    protected void setShape() {
        img = ImageLoader.loadImage("assets/Start.png");
    }

    @Override
    protected void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }
}
