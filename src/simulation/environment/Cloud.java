/*
 * Cloud using Perlin noise
 */

package simulation.environment;

import static util.Setting.getPanelCenter;
import static util.Util.map;
import static util.Util.random;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import processing.core.PApplet;
import processing.core.PVector;

public class Cloud extends simulation.Object {
    
    private double xStart, xSeed, ySeed;
    private PApplet pa;

    private static final PVector default_pos = new PVector(1000, 100);
    private static final Dimension default_dim = new Dimension(200, 200);

    public Cloud() {
        super(default_pos, default_dim);

        xStart = random(10);
        xSeed = xStart;
        ySeed = random(10);
        pa = new PApplet();
    }

    @Override
    protected void setShape() {

    }

    @Override
    protected void draw(Graphics2D g) {
        double noiseFactor;

        for (int y = 0; y <= dim.height; y += 15) {
            ySeed += 0.1;
            xSeed = xStart;

            for (double x = Math.cos(map(y, 0, dim.height, 0, Math.PI*2)) * dim.width/2 - dim.height/2; x <= -Math.cos(map(y, 0, dim.height, 0, Math.PI*2)) * dim.width/2 - dim.height/2; x += 15) {
                xSeed += 0.1;
                noiseFactor = pa.noise((float) xSeed, (float) ySeed);
                
                AffineTransform at = g.getTransform();
                g.translate(x + dim.width, y);
                g.rotate(noiseFactor * Math.PI*3);

                double diameter = noiseFactor * 75;
                int grey = (int) (150 + noiseFactor*105);
                int alpha = grey;
                g.setColor(new Color(grey, grey, grey, alpha));
                g.fill(new Ellipse2D.Double(-diameter/2, -diameter/4, diameter, diameter/2));

                g.setTransform(at);

            }
        }
    }

    @Override
    public void move(int x, int y) {
        pos.add(new PVector(x, y));
        if (pos.y > 800) {
            pos.add(new PVector((getPanelCenter().x-pos.x)*2, -1000));
        }
    }
}
