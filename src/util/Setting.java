/*
 * Settings of the application
 */

package util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import processing.core.PVector;

public class Setting {

    private static final Dimension size = new Dimension(1200, 640);
    private static final int FPS = 30;
    private static final int positionTolerance = 10;
    private static final boolean drawBoundingBox = false;
    private static String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private static Font font;

    static {
        if (Arrays.asList(fonts).contains("Comic Sans MS")) {
            font = new Font("Comic Sans MS", Font.PLAIN, 16);
        }
        else if (Arrays.asList(fonts).contains("Arial")) {
            font = new Font("Arial", Font.PLAIN, 16);
        }
        else {
            font = new Font(fonts[0], Font.PLAIN, 16);
        }
    }


    public static int getPanelWidth() {
        return size.width;
    }

    public static int getPanelHeight() {
        return size.height;
    }

    public static Dimension getPanelDimension() {
        return size;
    }

    public static PVector getPanelCenter() {
        return new PVector(size.width/2, size.height/2);
    }

    public static int getFPS() {
        return FPS;
    }

    public static int FPS() {
        return FPS;
    }

    public static int getPositionTolerance() {
        return positionTolerance;
    }

    public static Font getFont() {
        return font;
    }

    public static Font font() {
        return font;
    }

    public static boolean drawBoundingBox() {
        return drawBoundingBox;
    }
}