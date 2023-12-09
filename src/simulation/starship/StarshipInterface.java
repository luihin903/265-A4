package simulation.starship;

import java.awt.Graphics2D;

public interface StarshipInterface {
    void decorate(Graphics2D g);
    void move(int x, int y);
    void rotate(double theta);
    double getRotation();
}
