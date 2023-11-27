/*
 * To load images
 */

package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
    
    public static BufferedImage loadImage(String imgFile) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return img;
    }
}
