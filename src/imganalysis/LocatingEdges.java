/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import processing.core.PImage;

/**
 *
 * @author EDDER
 */
public class LocatingEdges {
    public static PImage locating(PImage myImage){
        BlackAndWhite bw = new BlackAndWhite(myImage);
        Gradient myGradient = new Gradient(bw.getOutImg());
        myGradient.applyMaskSbX();
        myGradient.applyMaskSbY();
        myGradient.gradientNorm();
        myGradient.calTheta();
        Threshold th = new Threshold(myGradient, -11184812, -5592406);
        th.noMax();
        th.thresholdize();
        return th.getOutImg();
    }
}
