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
public final class SearchFloor implements Search {

    public static final String LARGE_FLOOR = "Sprites\\largeFloor.png";
    public static final String MEDIUML_FLOOR = "Sprites\\mediumLFloor.png";
    public static final String MEDIUM_FLOOR = "Sprites\\mediumFloor.png";
    public static final String SMALL_FLOOR = "Sprites\\smallFloor.png";
    private ReaderImg floorReader;
    private final BufferedImage mapImg;
    private int floorImgColor[][];
    private int mapImgColor[][];
    private int pixelSize;

    public SearchFloor(BufferedImage mapImg, int size) {
        this.mapImg = mapImg;
        switch (size) {
            case 1:
                this.floorReader = new ReaderImg(SMALL_FLOOR);
                this.pixelSize = 36;
                floorImgColor = new int[pixelSize][pixelSize];
                break;
            case 2:
                this.floorReader = new ReaderImg(MEDIUM_FLOOR);
                this.pixelSize = 40;
                floorImgColor = new int[pixelSize][pixelSize];
                break;
            case 3:
                this.floorReader = new ReaderImg(LARGE_FLOOR);
                this.pixelSize = 50;
                floorImgColor = new int[pixelSize][pixelSize];
                break;
            case 4:
                this.floorReader = new ReaderImg(MEDIUML_FLOOR);
                this.pixelSize = 44;
                floorImgColor = new int[pixelSize][pixelSize];
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
                this.floorImgColor[i][j] = floorReader.getImage().getRGB(i, j);
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
                if (mapImgColor[i + x][j + y] == floorImgColor[i][j]) {
                    cont += 1;
                }
            }
        }
        if ((double) cont/(pixelSize*pixelSize) >= 0.9) {
            ans = true;
        } else {
            //System.out.println(cont/2500.0);
        }
        return ans;
    }

}
