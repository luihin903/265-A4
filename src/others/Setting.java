package others;

import java.awt.Dimension;

public class Setting {

    private static final Dimension size = new Dimension(1200, 640);
    private static final int FPS = 30;
    private static final int positionTolerance = 10;



    public static int getPanelWidth() {
        return size.width;
    }

    public static int getPanelHeight() {
        return size.height;
    }

    public static Dimension getPanelDimension() {
        return size;
    }

    public static Dimension getPanelCenter() {
        return new Dimension(size.width/2, size.height/2);
    }

    public static int getFPS() {
        return FPS;
    }

    public static int getPositionTolerance() {
        return positionTolerance;
    }
}