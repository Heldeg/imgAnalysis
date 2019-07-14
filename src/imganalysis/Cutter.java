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
public class Cutter {

    private final SearchLine searcher;
    private final int[] coordinates;
    private final int[] rectangle;
    private final PImage originalImage;
    private PImage secondImage;
    private PImage finalImage;

    public Cutter(SearchLine searcher, PImage originalImage) {
        this.searcher = searcher;
        this.coordinates = this.searcher.position();
        this.originalImage = originalImage;
        this.rectangle = new int[4];
        System.out.println("-----Cut Inic");
    }

    public void firstCut() {
        /*Queda la imagen con solo el mapa y zona negra
        el +270 es lo que qito a la izquiera y los valores 580, 477
        son propios del mapa*/
        this.secondImage = originalImage.get(coordinates[0] + 270, coordinates[1], 580, 477);
        System.out.println("First Cut");
    }

    public void secondCut() {
        rectangle[0] = lefthPos();
        rectangle[1] = topPos();
        rectangle[2] = rightPos();
        rectangle[3] = buttomPos();

        this.finalImage = secondImage.get(rectangle[0], rectangle[1],
                rectangle[2] - rectangle[0], rectangle[3] - rectangle[1]);
        System.out.println("Second Cut");
        correction();
    }

    public void correction() {
        if ((finalImage.width + 1) % 36 == 0 && (finalImage.height + 1) % 36 == 0) {
            this.finalImage = secondImage.get(rectangle[0], rectangle[1],
                    rectangle[2] - rectangle[0]+1, rectangle[3] - rectangle[1]+1);
        } else if (finalImage.width % 50 == 0 && (finalImage.height + 2) % 50 == 0) {
            this.finalImage = secondImage.get(rectangle[0], rectangle[1],
                    rectangle[2] - rectangle[0], rectangle[3] - rectangle[1]+2);
        } else if ((finalImage.width + 2) % 40 == 0 && (finalImage.height + 1) % 40 == 0) {
            this.finalImage = secondImage.get(rectangle[0], rectangle[1],
                    rectangle[2] - rectangle[0]+2, rectangle[3] - rectangle[1]+1);
        } else {
            this.finalImage = secondImage.get(rectangle[0], rectangle[1],
                    rectangle[2] - rectangle[0], rectangle[3] - rectangle[1]+2);
        }
        System.out.println("Correction");
    }

    private int lefthPos() {
        int posValue = -1;
        for (int i = 0; i < secondImage.width; i++) {
            for (int j = 0; j < secondImage.height; j++) {
                if (secondImage.get(i, j) == -2) {
                    posValue = i;
                    return posValue;
                }
            }
        }
        return posValue;
    }

    private int rightPos() {
        int posValue = -1;
        for (int i = secondImage.width - 1; i > -1; i--) {
            for (int j = 0; j < secondImage.height; j++) {
                if (secondImage.get(i, j) == -2) {
                    posValue = i;
                    return posValue;
                }
            }
        }
        return posValue;
    }

    private int topPos() {
        int posValue = -1;
        for (int i = 0; i < secondImage.height; i++) {
            for (int j = 0; j < secondImage.width; j++) {
                if (secondImage.get(j, i) == -2) {
                    posValue = i;
                    return posValue;
                }
            }
        }
        return posValue;
    }

    private int buttomPos() {
        int posValue = -1;
        for (int i = secondImage.height - 1; i > -1; i--) {
            for (int j = 0; j < secondImage.width; j++) {
                if (secondImage.get(j, i) == -2) {
                    posValue = i;
                    return posValue;
                }
            }
        }
        return posValue;
    }

    public PImage getFinalImage() {
        return finalImage;
    }

    public PImage getSecondImage() {
        return secondImage;
    }

    public int[] getCoordinates() {
        return coordinates;
    }
    
}
