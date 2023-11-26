package others;

import java.awt.Dimension;

import processing.core.PVector;

public class Util {
    
    // Change from Dimension to PVector
    public static PVector toPVector(Dimension dim) {
        return new PVector(dim.width, dim.height);
    }

}
