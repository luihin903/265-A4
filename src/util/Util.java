/*
 * Some global methods
 */

package util;

import java.awt.Color;
import java.awt.Dimension;

import processing.core.PVector;

public class Util {
    
    /**
     * Change from Dimension to PVector
     */
    public static PVector toPVector(Dimension dim) {
        return new PVector(dim.width, dim.height);
    }
    
    /**
     * Return a random value from 0 to a
     */
    public static double random(double a) {
        return Math.random() * a;
    }

    /**
     * Return mapped result
     */
    public static double map(double value, double inputMin, double inputMax, double outputMin, double outputMax) {
		double inputDiff = inputMax - inputMin;
		double percentile = (value-inputMin) / inputDiff;
		double outputDiff = outputMax - outputMin;
		return outputMin + outputDiff * percentile;
	}

    /**
     * Add rgb to color
     */
    public static Color add(Color base, double r, double g, double b) {
        int red = (int) (base.getRed() + r);
        if (red < 0) red = 0;
        if (red > 255) red = 255;

        int green = (int) (base.getGreen() + g);
        if (green < 0) green = 0;
        if (green > 255) green = 255;

        int blue = (int) (base.getBlue() + b);
        if (blue < 0) blue = 0;
        if (blue > 255) blue = 255;

        return new Color(red, green, blue);
    }

    /**
     * Add change to color
     */
    public static Color add(Color base, double change) {
        return add(base, change, change, change);
    }

    /**
     * add scalar to x and y of PVector
     */
    public static PVector add(PVector base, float scalar) {
        return new PVector(base.x + scalar, base.y + scalar);
    }
}
