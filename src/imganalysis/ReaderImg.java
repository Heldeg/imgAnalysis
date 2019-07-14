/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *Sprites
 * @author EDDER
 */
public class ReaderImg {
    private File imgFile;
    private BufferedImage image;
    private final String directory;
    public ReaderImg(String directory){
        this.directory = directory;
        openAndRead();
    }
    private void openAndRead(){
        try{
            this.imgFile = new File(this.directory);
            this.image = ImageIO.read(imgFile);
            //System.out.println("complete");
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        
    }

    public BufferedImage getImage() {
        return image;
    }
   
    
}
