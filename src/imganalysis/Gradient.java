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
public class Gradient {
    private final byte [][] sobelX = {{-1,0,1},{-2,0,2},{-1,0,1}};
    private final byte [][] sobelY = {{1,2,1},{0,0,0},{-1,-2,-1}};
    private final PImage intImg;
    private PImage outImgSbX;
    private PImage outImgSbY;
    private PImage outImgNorm;
    private byte [][] theta;
    /*Constructor*/
    public Gradient(PImage intImg) {
        this.intImg = intImg;
        this.theta = new byte[intImg.width][this.intImg.height];
    }
    /*=========main methods===========*/
    public void applyMaskSbX(){
        this.outImgSbX = this.intImg.copy();
        for (int i = 1; i < this.intImg.width-1; i++) {
            for (int j = 1; j < this.intImg.height-1; j++) {
                int [][] neighborhood = obtainNeighborhood(i,j);
                int value = calValSobel(true, neighborhood);
                this.outImgSbX.set(i,j,value);
            }
        }
    }
    public void applyMaskSbY(){
        this.outImgSbY = this.intImg.copy();
        for (int i = 1; i < this.intImg.width-1; i++) {
            for (int j = 1; j < this.intImg.height-1; j++) {
                int [][] neighborhood = obtainNeighborhood(i,j);
                int value = calValSobel(false, neighborhood);
                this.outImgSbY.set(i,j,value);
            }
        }
    }
        
    public void gradientNorm(){
        this.outImgNorm = this.intImg.copy();
        int value = 0;
        for (int i = 0; i < this.intImg.width; i++) {
            for (int j = 0; j < this.intImg.height; j++) {
                value = Math.abs(outImgSbX.get(i, j))+Math.abs(outImgSbY.get(i, j));
                outImgNorm.set(i, j, value);
            }
        }
    }
    
    public void calTheta(){
        int x,y;
        for (int i = 0; i < theta.length; i++) {
            for (int j = 0; j < theta[0].length; j++) {
                x=Math.abs(outImgSbX.get(i,j));
                y=Math.abs(outImgSbY.get(i,j));
                theta[i][j]=clasify(x, y);
            }
        }
    }
    
    /*==========secondary methods===========*/
    private int [][] obtainNeighborhood(int x, int y){
        int [][] neighborhood = new int [3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                neighborhood[i][j] = this.intImg.get(x+(i-1), y+(j-1));
            }
        }
        return neighborhood;
    }
    private int calValSobel(boolean isX, int [][] neighborhood){
        int value = 0;
        byte [][] sobal;
        if (isX) {
            sobal = this.sobelX;
        }else{
            sobal = this.sobelY;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                value += sobal[i][j]*neighborhood[i][j];
            }
        }
            
        return value;
    }
    private byte clasify(int x,int y){
        /*
        1 --> A1=[0-pi/8) U [7pi/8-pi]
        2 --> A2=[pi/8,3pi/8)
        3 --> A3=[3pi/8,5pi/8)
        4 --> A4=[5pi/8,7pi/8)
        */
        byte area=0;
        if (x==0) {
            if (y==0) {
                area = 1;
            }else{
                area = 3;
            }
        }else{
            double arcTan= Math.atan((double)y/x);
            if ((7*Math.PI/8<=arcTan && arcTan<=Math.PI)||(0<arcTan && arcTan<Math.PI/8)){
                area =1;
            }else if((Math.PI/8<=arcTan && arcTan<3*Math.PI)){
                area =2;
            }else if((3*Math.PI/8<=arcTan && arcTan<5*Math.PI/8)){
                area =3;
            }else if((5*Math.PI/8<=arcTan && arcTan<7*Math.PI/8)){
                area =4;
            }
        }
        return area;
    }

    public PImage getOutImgSbX() {
        return outImgSbX;
    }

    public void setOutImgSbX(PImage outImgSbX) {
        this.outImgSbX = outImgSbX;
    }

    public PImage getOutImgSbY() {
        return outImgSbY;
    }

    public void setOutImgSbY(PImage outImgSbY) {
        this.outImgSbY = outImgSbY;
    }

    public PImage getOutImgNorm() {
        return outImgNorm;
    }

    public void setOutImgNorm(PImage outImgNorm) {
        this.outImgNorm = outImgNorm;
    }

    public byte[][] getTheta() {
        return theta;
    }

    public void setTheta(byte[][] theta) {
        this.theta = theta;
    }

    public PImage getIntImg() {
        return intImg;
    }
    
    public void printMatrix(){
        int minC=0,maxC=0;
        for (int i = 0; i < outImgNorm.width; i++) {
            for (int j = 0; j < outImgNorm.height; j++) {
                if (outImgNorm.get(i, j)==-2) {
                    maxC++;                   
                }
                if (outImgNorm.get(i,j)== -16777216){
                    minC++;
                }
            }
            
        }
        System.out.println("min: "+minC+" max: "+maxC);
    }
    
}
