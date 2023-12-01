/*
 * The instructions of interactions for users
 */

package simulation;

import static util.Setting.getFont;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;

public class Instruction extends Object {
    
    private int state;

    private static String[][] texts = {
        {"1. Move the Super Heavy", "booster to the correct", "location"},
        {"2. Move the Starship", "spacecraft to the correct", "location"},
        {"3. Load the Starlink", "satellites for our mission"},
        {"4. Press the button to", "launch the Starship"},
        {"Success"}
    };

    private static final PVector default_pos = new PVector(150, 100);
    private static final Dimension default_dim = new Dimension(200, 100);

    private Rectangle2D.Double body;

    public Instruction() {
        super(default_pos, default_dim);
        scale = 1;
        state = 0;
    }

    @Override
    protected void setShape() {
        body = new Rectangle2D.Double(0, 0, dim.width*scale, dim.height*scale);
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fill(body);

        g.setColor(Color.BLACK);
        g.draw(body);
        g.setFont(getFont());

        String[] text = texts[state-1];
        for (int i = 0; i < text.length; i ++) {
            g.drawString(text[i], 0, (i+1)*16);
        }
    }

    public void setState(int state) {
        this.state = state;
    }
}
