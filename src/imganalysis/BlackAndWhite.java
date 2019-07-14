/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;
import processing.core.*;
/**
 *
 * @author EDDER
 */
public final class BlackAndWhite extends PApplet{
    private PImage intImg;
    private PImage outImg;
    
    public BlackAndWhite(PImage intImg){
        this.intImg = intImg;
        this.outImg = this.intImg.copy();
        outImg.filter(THRESHOLD, (float) 0.7);
        outImg.filter(INVERT);
    }

    public void setIntImg(PImage intImg) {
        this.intImg = intImg;
    }

    public void setOutImg(PImage outImg) {
        this.outImg = outImg;
    }
    

    public PImage getOutImg() {
        return outImg;
    }

    public PImage getIntImg() {
        return intImg;
    }
    
}
