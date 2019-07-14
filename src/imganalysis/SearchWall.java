 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import java.awt.image.BufferedImage;

/**
 *
 * @author EDDER
 */
public final class SearchWall implements Search{
    public static final String LARGE_WALL = "Sprites\\largeWall.png";
    public static final String MEDIUML_WALL = "Sprites\\mediumLWall.png";
    public static final String MEDIUM_WALL = "Sprites\\mediumWall.png";
    public static final String SMALL_WALL = "Sprites\\smallWall.png";
    private ReaderImg wallReader;
    private final BufferedImage mapImg;
    private int wallImgColor [][];
    private int mapImgColor  [][];
    private int pixelSize;
    
    public SearchWall(BufferedImage map, int size){
        this.mapImg =map;
        switch(size){
            case 1:
                this.wallReader = new ReaderImg(SMALL_WALL);
                this.pixelSize = 36;
                wallImgColor = new int [pixelSize][pixelSize];
                break;
            case 2:
                this.wallReader = new ReaderImg(MEDIUM_WALL);
                this.pixelSize = 40;
                wallImgColor = new int [pixelSize][pixelSize];
                break;
            case 3:
                this.wallReader = new ReaderImg(LARGE_WALL);
                this.pixelSize = 50;
                wallImgColor = new int [pixelSize][pixelSize];
                break;
            case 4:
                this.wallReader = new ReaderImg(MEDIUML_WALL);
                this.pixelSize = 44;
                wallImgColor = new int[pixelSize][pixelSize];
                break;
            default:
                System.out.println("Error");
        }
                
        referenceImageColorToMatrix();
        mapColorToMatrix();
    }
    
    @Override
    public void mapColorToMatrix(){
        this.mapImgColor = new int [mapImg.getWidth()][mapImg.getHeight()];
        for (int i = 0; i < mapImg.getWidth(); i++) {
            for (int j = 0; j < mapImg.getHeight(); j++) {
                this.mapImgColor [i][j]= mapImg.getRGB(i,j);
            }
        }
    }
    public boolean prueba(){
        boolean flag=false;
        for (int i = 0; i < mapImg.getWidth()-pixelSize; i++) {
            for (int j = 0; j < mapImg.getHeight()-pixelSize; j++) {
                if (compareImg(i, j)) {
                    System.out.println("i: "+i+" j: "+j);                    
                }
            }
        }
        return flag;
    }
    @Override
    public boolean compareImg(int x, int y){
        boolean ans = false;
        int cont=0;
        for (int i = 0; i < pixelSize; i++) {
            for (int j = 0; j < pixelSize; j++) {
                if (mapImgColor[i+x][j+y] == wallImgColor[i][j]) {
                    cont+=1;
                }
            }
        }
        if ((double) cont/(pixelSize*pixelSize)>=0.95) {
            ans = true;
        }else{
            //System.out.println(cont/2500.0);
        }
        return ans;
        
    }

    @Override
    public void referenceImageColorToMatrix() {
        for (int i = 0; i < pixelSize; i++) {
            for (int j = 0; j < pixelSize; j++) {
                this.wallImgColor [i][j]= wallReader.getImage().getRGB(i,j);
            }
        }
    }
}
