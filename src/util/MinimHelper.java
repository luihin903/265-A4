package util;

import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelper {
    
    public InputStream createInput(String fileName) {
        
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return is;
    }

}
