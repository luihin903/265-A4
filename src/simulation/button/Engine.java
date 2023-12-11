/*
 * Rapter engines
 */

package simulation.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import main.Panel;
import processing.core.PVector;
import util.Setting;

public class Engine extends Button {
    
    private static int[][] coords = {
        {364, 310}, // 0
        {346, 380}, // 1
        {416, 360}, // 2
        {337, 239}, // 3
        {408, 235}, // 4
        {470, 275}, // 5
        {494, 344}, // 6
        {473, 415}, // 7
        {417, 460}, // 8
        {342, 464}, // 9
        {283, 424}, // 10
        {257, 354}, // 11
        {276, 280}, // 12
        {272, 176}, // 13
        {330, 150}, // 14
        {393, 148}, // 15
        {454, 163}, // 16
        {509, 195}, // 17
        {550, 243}, // 18
        {572, 300}, // 19
        {576, 364}, // 20
        {562, 426}, // 21
        {530, 480}, // 22
        {480, 520}, // 23
        {422, 544}, // 24
        {356, 550}, // 25
        {296, 535}, // 26
        {240, 500}, // 27
        {198, 450}, // 28
        {174, 393}, // 29
        {172, 328}, // 30
        {188, 266}, // 31
        {222, 212}, // 32
        {924, 280}, // 33 (spacecraft)
        {924, 356}, // 34
        {857, 317}, // 35
        {1052, 234}, // 36
        {1046, 398}, // 37
        {904, 474}, // 38
        {758, 394}, // 39
        {760, 232}, // 40
        {900, 140} // 41
    };

    private static Engine[] engines = new Engine[coords.length];
    private static Panel panel;

    private Ellipse2D.Double body;
    private double diameter;
    private boolean working;

    public Engine(float x, float y, int diameter, boolean working) {
        super(new PVector(x, y), new Dimension(diameter, diameter));
        this.diameter = diameter;
        scale = 1;
        body = new Ellipse2D.Double(0, 0, diameter, diameter);
        this.working = working;
    }

    public static void init(Panel panel) {
        Engine.panel = panel;

        for (int i = 0; i < 33; i ++) {
            engines[i] = new Engine(coords[i][0], coords[i][1], 64, true);
        }
        for (int i = 33; i < 36; i ++) {
            engines[i] = new Engine(coords[i][0], coords[i][1], 70, false);
        }
        for (int i = 36; i < 42; i ++) {
            engines[i] = new Engine(coords[i][0], coords[i][1], 132, false);
        }
    }

    public static void paintAll(Graphics2D g) {
        for (Engine e : engines) {
            e.paint(g);
        }
    }

    public static void checkClicking(MouseEvent evt) {
        for (Engine e : engines) {
            if (e.clicked(evt)) {
                panel.playDing();
                e.working = !e.working;
            }
        }

        if (engines[0].working == true && engines[1].working == true && engines[2].working == true && engines[3].working == false && engines[4].working == false && engines[5].working == false && engines[6].working == false && engines[7].working == false && engines[8].working == false && engines[9].working == false && engines[10].working == false && engines[11].working == false && engines[12].working == false && engines[13].working == false && engines[14].working == false && engines[15].working == false && engines[16].working == false && engines[17].working == false && engines[18].working == false && engines[19].working == false && engines[20].working == false && engines[21].working == false && engines[22].working == false && engines[23].working == false && engines[24].working == false && engines[25].working == false && engines[26].working == false && engines[27].working == false && engines[28].working == false && engines[29].working == false && engines[30].working == false && engines[31].working == false && engines[32].working == false && engines[33].working == true && engines[34].working == true && engines[35].working == true && engines[36].working == true && engines[37].working == true && engines[38].working == true && engines[39].working == true && engines[40].working == true && engines[41].working == true) {
            panel.setState(8);
        }

    }

    @Override
    protected void setShape() {
        body = new Ellipse2D.Double(0, 0, diameter, diameter);
    }

    @Override
    protected void draw(Graphics2D g) {
        
        if (working) {
            g.setColor(Color.ORANGE);
            g.fill(body);
        }
    }
}
