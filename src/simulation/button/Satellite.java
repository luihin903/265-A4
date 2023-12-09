/*
 * The Starlink satellite
 */

package simulation.button;

import static util.Setting.getPanelCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import util.ImageLoader;

public class Satellite extends Button {

    private BufferedImage img;

    private static final PVector default_pos = new PVector(100, 400);
    private static final Dimension default_dim = new Dimension(152, 144);

    public Satellite() {
        super(default_pos, default_dim);
        scale = 1;
        targetPosition = new PVector(getPanelCenter().x, 220);
    }

    @Override
    protected void setShape() {
        img = ImageLoader.loadImage("assets/Starlink_Satellite.png");
    }

    @Override
    protected void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void drawPosition(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawRect((int) (targetPosition.x - dim.width/2*scale), (int) (targetPosition.y - dim.height/2*scale), (int) (dim.width*scale), (int) (dim.height*scale));
    }

}
