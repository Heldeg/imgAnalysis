/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author EDDER
 */
public class SavePicture {
    public static void savePicture(String directory,String format,BufferedImage bf){
        try {
            File file = new File(directory);
            ImageIO.write(bf,format, file);
        } catch (IOException ex) {
            Logger.getLogger(SavePicture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
