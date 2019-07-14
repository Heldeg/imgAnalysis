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
public final class SearchGoal implements Search {

    public static final String LARGE_GOAL = "Sprites\\largeGoal.png";
    public static final String MEDIUML_GOAL = "Sprites\\mediumLGoal.png";
    public static final String MEDIUM_GOAL = "Sprites\\mediumGoal.png";
    public static final String SMALL_GOAL = "Sprites\\smallGoal.png";
    private ReaderImg goalReader;
    private final BufferedImage mapImg;
    private int goalImgColor[][];
    private int mapImgColor[][];
    private int pixelSize;

    public SearchGoal(BufferedImage mapImg, int size) {
        this.mapImg = mapImg;
        switch (size) {
            case 1:
                this.goalReader = new ReaderImg(SMALL_GOAL);
                this.pixelSize = 36;
                goalImgColor = new int[pixelSize][pixelSize];
                break;
            case 2:
                this.goalReader = new ReaderImg(MEDIUM_GOAL);
                this.pixelSize = 40;
                goalImgColor = new int[pixelSize][pixelSize];
                break;
            case 3:
                this.goalReader = new ReaderImg(LARGE_GOAL);
                this.pixelSize = 50;
                goalImgColor = new int[pixelSize][pixelSize];
                break;
            case 4:
                this.goalReader = new ReaderImg(MEDIUML_GOAL);
                this.pixelSize = 44;
                goalImgColor = new int[pixelSize][pixelSize];
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
                this.goalImgColor[i][j] = goalReader.getImage().getRGB(i, j);
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
                if (mapImgColor[i + x][j + y] == goalImgColor[i][j]) {
                    cont += 1;
                }
            }
        }
        if ((double) cont/(pixelSize*pixelSize) >= 0.99) {
            ans = true;
        } else {
            //System.out.println(cont/2500.0);
        }
        return ans;

    }

}
