package others;

import java.awt.Color;

public enum Colors {
    SKY_BLUE(new Color(164, 209, 228));

    private final Color color;
    private Colors(Color color) {
        this.color = color;
    }
    public Color get() {
        return color;
    }
}
