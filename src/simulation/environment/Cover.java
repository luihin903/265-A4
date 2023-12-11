/*
 * A cover image
 */

package simulation.environment;

import static util.Setting.getPanelCenter;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import processing.core.PVector;
import util.ImageLoader;
import util.Setting;

public class Cover extends simulation.Object {
    
    private static final PVector default_pos = getPanelCenter();
    private static final Dimension default_dim = new Dimension(960, 540);

    private BufferedImage img;

    public Cover() {
        super(default_pos, default_dim);
    }

    @Override
    protected void setShape() {
        img = ImageLoader.loadImage("assets/Cover.png");
    }

    @Override
    protected void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, null);

        Font font = Setting.font();
        font = font.deriveFont((float) 32);
        g.setFont(font);
        g.drawString("A simulation of Starlink Mission w/Starship.", 0, -16);
        g.drawString("Follow the instructions and interact with your mouse/keyboard!", 0, dim.height + 32);
    }
}
