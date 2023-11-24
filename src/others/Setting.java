package others;

import java.awt.Dimension;

public class Setting {

    private static final Dimension size = new Dimension(1280, 720);
    private static final int FPS = 30;



    public static int getPanelWidth() {
        return size.width;
    }

    public static int getPanelHeight() {
        return size.height;
    }

    public static Dimension getPanelDimension() {
        return size;
    }

    public static int getFPS() {
        return FPS;
    }
}