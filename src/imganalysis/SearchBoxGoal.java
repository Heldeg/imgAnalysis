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
public final class SearchBoxGoal implements Search {

    public static final String LARGE_BOX_GOAL = "Sprites\\largeBoxGoal.png";
    public static final String MEDIUML_BOX_GOAL = "Sprites\\mediumLBoxGoal.png";
    public static final String MEDIUM_BOX_GOAL = "Sprites\\mediumBoxGoal.png";
    public static final String SMALL_BOX_GOAL = "Sprites\\smallBoxGoal.png";
    private ReaderImg boxGoalReader;
    private final BufferedImage mapImg;
    private int boxGoalImgColor[][];
    private int mapImgColor[][];
    private int pixelSize;

    public SearchBoxGoal(BufferedImage mapImg, int size) {
        this.mapImg = mapImg;
        switch (size) {
            case 1:
                this.boxGoalReader = new ReaderImg(SMALL_BOX_GOAL);
                this.pixelSize = 36;
                boxGoalImgColor = new int[pixelSize][pixelSize];
                break;
            case 2:
                this.boxGoalReader = new ReaderImg(MEDIUM_BOX_GOAL);
                this.pixelSize = 40;
                boxGoalImgColor = new int[pixelSize][pixelSize];
                break;
            case 3:
                this.boxGoalReader = new ReaderImg(LARGE_BOX_GOAL);
                this.pixelSize = 50;
                boxGoalImgColor = new int[pixelSize][pixelSize];
                break;
            case 4:
                this.boxGoalReader = new ReaderImg(MEDIUML_BOX_GOAL);
                this.pixelSize = 44;
                boxGoalImgColor = new int[pixelSize][pixelSize];
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
                this.boxGoalImgColor[i][j] = boxGoalReader.getImage().getRGB(i, j);
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
                if (mapImgColor[i + x][j + y] == boxGoalImgColor[i][j]) {
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
