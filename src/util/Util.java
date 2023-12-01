/*
 * Some global methods
 */

package util;

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

}
