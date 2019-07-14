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
public final class SearchBox implements Search {

    public static final String LARGE_BOX = "Sprites\\largeBox.png";
    public static final String MEDIUML_BOX = "Sprites\\mediumLBox.png";
    public static final String MEDIUM_BOX = "Sprites\\mediumBox.png";
    public static final String SMALL_BOX = "Sprites\\smallBox.png";
    private ReaderImg boxReader;
    private final BufferedImage mapImg;
    private int boxImgColor[][];
    private int mapImgColor[][];
    private int pixelSize;

    public SearchBox(BufferedImage mapImg, int size) {
        this.mapImg = mapImg;
        switch (size) {
            case 1:
                this.boxReader = new ReaderImg(SMALL_BOX);
                this.pixelSize = 36;
                boxImgColor = new int[pixelSize][pixelSize];
                break;
            case 2:
                this.boxReader = new ReaderImg(MEDIUM_BOX);
                this.pixelSize = 40;
                boxImgColor = new int[pixelSize][pixelSize];
                break;
            case 3:
                this.boxReader = new ReaderImg(LARGE_BOX);
                this.pixelSize = 50;
                boxImgColor = new int[pixelSize][pixelSize];
                break;
            case 4:
                this.boxReader = new ReaderImg(MEDIUML_BOX);
                this.pixelSize = 44;
                boxImgColor = new int[pixelSize][pixelSize];
                break;
            default:
                System.out.println("Error");
        }
        referenceImageColorToMatrix();
        mapColorToMatrix();
    }

    @Override
    public void referenceImageColorToMatrix() {
        for (int i = 0; i < pixelSize; i++) {
            for (int j = 0; j < pixelSize; j++) {
                this.boxImgColor[i][j] = boxReader.getImage().getRGB(i, j);
            }
        }
    }

    @Override
    public void mapColorToMatrix() {
        this.mapImgColor = new int[mapImg.getWidth()][mapImg.getHeight()];
        for (int i = 0; i < mapImg.getWidth(); i++) {
            for (int j = 0; j < mapImg.getHeight(); j++) {
                this.mapImgColor[i][j] = mapImg.getRGB(i, j);
            }
        }
    }

    @Override
    public boolean compareImg(int x, int y) {
        boolean ans = false;
        int cont = 0;
        for (int i = 0; i < pixelSize; i++) {
            for (int j = 0; j < pixelSize; j++) {
                if (mapImgColor[i + x][j + y] == boxImgColor[i][j]) {
                    cont += 1;
                }
            }
        }
        if ((double) cont/(pixelSize*pixelSize) >= 0.98) {
            ans = true;
        } else {
            //System.out.println(cont/pixelSize*pixelSize);
        }
        return ans;

    }

    public boolean prueba() {
        boolean flag = false;
        for (int i = 0; i < mapImg.getWidth() - pixelSize; i++) {
            for (int j = 0; j < mapImg.getHeight() - pixelSize; j++) {
                if (compareImg(i, j)) {
                    System.out.println("i: " + i + " j: " + j);

                }
            }
        }
        return flag;
    }

}
