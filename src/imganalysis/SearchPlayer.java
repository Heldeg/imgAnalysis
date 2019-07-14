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
public final class SearchPlayer implements Search {

    public static final String LARGE_PLAYER = "Sprites\\largePlayer.png";
    public static final String MEDIUML_PLAYER = "Sprites\\mediumLPlayer.png";
    public static final String MEDIUM_PLAYER = "Sprites\\mediumPlayer.png";
    public static final String SMALL_PLAYER = "Sprites\\smallPlayer.png";
    private ReaderImg playerReader;
    private final BufferedImage mapImg;
    private int playerImgColor[][];
    private int mapImgColor[][];
    private int pixelSize;

    public SearchPlayer(BufferedImage mapImg, int size) {
        this.mapImg = mapImg;
        switch (size) {
            case 1:
                this.playerReader = new ReaderImg(SMALL_PLAYER);
                this.pixelSize = 36;
                playerImgColor = new int[pixelSize][pixelSize];
                break;
            case 2:
                this.playerReader = new ReaderImg(MEDIUM_PLAYER);
                this.pixelSize = 40;
                playerImgColor = new int[pixelSize][pixelSize];
                break;
            case 3:
                this.playerReader = new ReaderImg(LARGE_PLAYER);
                this.pixelSize = 50;
                playerImgColor = new int[pixelSize][pixelSize];
                break;
            case 4:
                this.playerReader = new ReaderImg(MEDIUML_PLAYER);
                this.pixelSize = 44;
                playerImgColor = new int[pixelSize][pixelSize];
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
                this.playerImgColor[i][j] = playerReader.getImage().getRGB(i, j);
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
                if (mapImgColor[i + x][j + y] == playerImgColor[i][j]) {
                    cont += 1;
                }
            }
        }
        if ((double) cont/(pixelSize*pixelSize)>= 0.95) {
            ans = true;
        } else {
            //System.out.println(cont/2500.0);
        }
        return ans;

    }

}
