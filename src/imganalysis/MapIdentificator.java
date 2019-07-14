/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import processing.core.PImage;

/*
    *
    **  0 -> Floor
    **  1 -> Wall
    **  2 -> Box
    **  3 -> Goal
    **  4 -> Box and Goal
    **  5 -> Player
    **  6 -> Plater and Goal
 */
public class MapIdentificator {

    private final PImage mapImg;
    private final int width;
    private final int height;
    private int xLength;
    private int yLength;
    private int mapType;
    private int blockSize;
    private int contGoal;
    private int contBox;
    private final SearchWall sW;
    private final SearchPlayer sP;
    private final SearchGoal sG;
    private final SearchFloor sF;
    private final SearchBoxGoal sBG;
    private final SearchBox sB;
    private final int[][] tMap;
    private int[][] map;

    public MapIdentificator(PImage mapImg) {
        this.mapImg = mapImg;
        mapImg.save("finalImg.png");
        this.width = mapImg.width;
        this.height = mapImg.height;
        this.contBox = 0;
        this.contGoal = 0;
        System.out.println("------------ID");
        identMapType();
        System.out.println("-----Length");
        System.out.print("X: "+xLength);
        System.out.println(" Y: "+yLength);
        System.out.println("------");
        ReaderImg img = new ReaderImg("finalImg.png");
        this.sW = new SearchWall(img.getImage(), mapType);
        this.sP = new SearchPlayer(img.getImage(), mapType);
        this.sG = new SearchGoal(img.getImage(), mapType);
        this.sF = new SearchFloor(img.getImage(), mapType);
        this.sBG = new SearchBoxGoal(img.getImage(), mapType);
        this.sB = new SearchBox(img.getImage(), mapType);
        tMap = new int[xLength][yLength];
        //sW.prueba();
    }

    public void buildMap() {
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                tMap[i][j] = identPos((i * blockSize), (j * blockSize));

            }
        }
        transpose();
        checkNBoxNGoal();
    }
    
    private void checkNBoxNGoal(){
        if (this.contBox==this.contGoal) {
            System.out.println("#Box=#Goal");
        }else if(this.contBox==this.contGoal+1){
            System.out.println("PlayerInGoal");
            for (int[] map1 : map) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map1[j] == 5) {
                        map1[j] = 6;
                        return;
                    }
                }
            }
        }else{
            System.out.println("Error");
        }
    }
    private void transpose() {
        this.map = new int[tMap[0].length][tMap.length];
        for (int i = 0; i < tMap[0].length; i++) {
            for (int j = 0; j < tMap.length; j++) {
                map[i][j] = tMap[j][i];
            }
        }
    }

    private int identPos(int x, int y) {
        int type = -1;
        if (sW.compareImg(x, y)) {
            type = 1;
        } else if (sP.compareImg(x, y)) {
            type = 5;
        } else if (sG.compareImg(x, y)) {
            type = 3;
            this.contGoal+=1;
        } else if (sB.compareImg(x, y)) {
            type = 2;
            this.contBox +=1;
        } else if (sBG.compareImg(x, y)) {
            type = 4;
        } else if (sF.compareImg(x, y)) {
            type = 0;
        }
        return type;
    }

    private void identMapType() {
        if ((width) % 36 == 0 && (height) % 36 == 0) {
            this.mapType = Search.SMALL;
            this.blockSize = 36;
            System.out.println("width: " + width);
            System.out.println("height: " + height);
            this.xLength = (width) / blockSize;
            this.yLength = (height) / blockSize;
            System.out.println("Block: " + blockSize);
        } else if (width % 50 == 0 && (height) % 50 == 0) {
            this.mapType = Search.LARGE;
            this.blockSize = 50;
            System.out.println("width: " + width);
            System.out.println("height: " + height);
            this.xLength = (width ) / blockSize;
            this.yLength = (height ) / blockSize;
            System.out.println("Block: " + blockSize);
        } else if ((width) % 40 == 0 && (height) % 40 == 0) {
            this.mapType = Search.MEDIUM;
            this.blockSize = 40;
            System.out.println("width: " + width);
            System.out.println("height: " + height);
            this.xLength = (width ) / blockSize;
            this.yLength = (height) / blockSize;
            System.out.println("Block: " + blockSize);
        } else {
            this.mapType = Search.MEDIUMLARGE;
            this.blockSize = 44;
            System.out.println("width: " + width);
            System.out.println("height: " + height);
            this.xLength = (width) / blockSize;
            this.yLength = (height) / blockSize;
            System.out.println("Block: " + blockSize);
        }
    }

    public PImage getMapImg() {
        return mapImg;
    }

    public void printMatrixT() {
        for (int[] tMap1 : tMap) {
            for (int j = 0; j < tMap[0].length; j++) {
                System.out.print(tMap1[j] + " ");
            }
            System.out.println("");
        }
    }

    public void printMatrix() {
        for (int[] map1 : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map1[j] + " ");
            }
            System.out.println("");
        }
    }
}
