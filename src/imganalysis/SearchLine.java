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
public final class SearchLine implements Search {
    public static final String LINE = "Sprites\\line.png";
    private final ReaderImg lineReader;
    private final BufferedImage mapImg;
    private final int lineImgColor [][];
    private int mapImgColor  [][];

    public SearchLine(BufferedImage mapImg) {
        this.mapImg = mapImg;
        this.lineReader = new ReaderImg(LINE);
        this.lineImgColor = new int[2][300];
        referenceImageColorToMatrix();
        mapColorToMatrix();
    }
    
    
    @Override
    public void referenceImageColorToMatrix() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 300; j++) {
                this.lineImgColor [i][j] = lineReader.getImage().getRGB(i, j);
            }
        }
    }

    @Override
    public void mapColorToMatrix() {
        this.mapImgColor = new int[mapImg.getWidth()][mapImg.getHeight()];
        for (int i = 0; i < mapImg.getWidth(); i++) {
            for (int j = 0; j < mapImg.getHeight(); j++) {
                this.mapImgColor [i][j]= mapImg.getRGB(i,j);
            }
            
        }
    }

    @Override
    public boolean compareImg(int x, int y) {
                boolean ans = false;
        int cont=0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 300; j++) {
                if (mapImgColor[i+x][j+y] == lineImgColor[i][j]) {
                    cont+=1;
                }
            }
        }
        if (cont/600.0>=0.9) {
            ans = true;
        }else{
            //System.out.println(cont/600.0);
        }
        return ans;
        
    }
    public int [] position(){
        int [] pos = new int [2];
        for (int i = 0; i < mapImgColor.length-lineImgColor.length; i++) {
            for (int j = 0; j < mapImgColor[0].length-lineImgColor[0].length; j++) {
                if (compareImg(i, j)) {
                    pos[0]=i;
                    pos[1]=j;
                    return pos;
                }
            }
            
        }
        return pos;
    }
}
