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
public class Threshold {
    private final Gradient imgGradient;
    private final PImage outImg;
    private final int minThreshold;
    private final int maxThreshold;
    public Threshold(Gradient imgGradient,int minThreshold,int maxThreshold){
        this.imgGradient = imgGradient;
        this.outImg = imgGradient.getOutImgNorm().copy();
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
    }
    public void thresholdize(){
        for (int i = 0; i < outImg.width; i++) {
            for (int j = 0; j < outImg.height; j++) {
                if (outImg.get(i, j)<minThreshold) {
                    outImg.set(i, j, -16777216);
                }else if(outImg.get(i, j)>maxThreshold){
                    outImg.set(i, j, -2);
                }else{
                    if (neighbourIsB(i, j)) {
                        outImg.set(i, j, -2);
                    }else{
                        outImg.set(i, j, -16777216);
                    }
                }
            }
        }
    }
    /*execute first*/
    public void noMax(){
        int lefth,right,center,theta,indexIL=0,indexJL=0,indexIR=0,indexJR=0;
        for (int i = 1; i < outImg.width; i++) {
            for (int j = 1; j < outImg.height; j++) {
                theta = imgGradient.getTheta()[i][j];
                switch(theta){
                    case 1:
                        indexIL = i;
                        indexJL = j-1;
                        indexIR = i;
                        indexJR = j+1;
                        break;
                    case 2:
                        indexIL = i+1;
                        indexJL = j-1;
                        indexIR = i-1;
                        indexJR = j+1;
                        break;
                    case 3:
                        indexIL = i-1;
                        indexJL = j;
                        indexIR = i+1;
                        indexJR = j;
                        break;
                    case 4:
                        indexIL = i+1;
                        indexJL = j+1;
                        indexIR = i-1;
                        indexJR = j-1;
                        break;
                    default:
                        System.out.println("Error");
                }
                center = imgGradient.getOutImgNorm().get(i, j);
                lefth = imgGradient.getOutImgNorm().get(indexIL, indexJL);
                right = imgGradient.getOutImgNorm().get(indexIR, indexJR);
                if (center < lefth || center <right) {
                    outImg.set(i, j, 0);
                }
            }
        }
    }
    
    public void printMatrix(){
        for (int i = 0; i < outImg.width; i++) {
            for (int j = 0; j < outImg.height; j++) {
                System.out.print(outImg.get(i, j)+" ");
            }
            System.out.println("");
        }
    }
    private boolean neighbourIsB(int x,int y){
        boolean ans=false;
        int val;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i==1&&j==1) {
                    continue;
                }
                val = outImg.get(x+(i-1),y+(j-1));
                if (val == -2) {
                    ans = true;
                    return ans;
                }
            }
        }
        return ans;
    }

    public PImage getOutImg() {
        return outImg;
    }
    
}
