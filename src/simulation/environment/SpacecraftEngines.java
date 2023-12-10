package simulation.environment;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import simulation.Object;
import util.ImageLoader;

public class SpacecraftEngines extends Object {
    
    BufferedImage img;

    private static final PVector default_pos = new PVector(900, 300);
    private static final Dimension default_dim = new Dimension(443, 490);

    public SpacecraftEngines() {
        super(default_pos, default_dim);
        scale = 1;
    }

    @Override
    protected void setShape() {
        img = ImageLoader.loadImage("assets/Spacecraft_Engines.jpg");
    }

    @Override
    protected void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }
}
