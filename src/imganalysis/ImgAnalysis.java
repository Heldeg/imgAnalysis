/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author EDDER
 */
public class ImgAnalysis extends PApplet {

    public static final String CAP = "Sprites\\image.PNG";
    public PImage org;
    Robot mrRobot;
    ReaderImg r;
    SearchWall w;
    SearchLine sl;
    Cutter ct;
    MapIdentificator mp;

    @Override
    public void settings() {
        size(1366, 768);

    }

    @Override
    public void setup() {
        try {
            mrRobot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(ImgAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
        SavePicture.savePicture("Sprites\\image.PNG", "png", Screenshot.takeScreenshot(mrRobot));
        org = loadImage(CAP);
        LocatingEdges.locating(org).save("img.png");
        r = new ReaderImg("img.png");
        sl = new SearchLine(r.getImage());
        ct = new Cutter(sl, LocatingEdges.locating(org));
        MouseAction.doMouseAction(mrRobot, ct.getCoordinates());
        ct.firstCut();
        ct.secondCut();
        /*ct.getFinalImage().get(220, 220, 44, 44).save("mediumLBox.png");
        ct.getFinalImage().get(264, 220, 44, 44).save("mediumLGoal.png");
        ct.getFinalImage().get(132, 176, 44, 44).save("mediumLBoxGoal.png");
        ct.getFinalImage().get(44, 132, 44, 44).save("mediumLPlayer.png");
        ct.getFinalImage().get(88, 132, 44, 44).save("mediumLFloor.png");
        ct.getFinalImage().get(0, 132, 44, 44).save("mediumLWall.png");*/
        mp = new MapIdentificator(ct.getFinalImage());
        System.out.println("------");
        mp.buildMap();
        mp.printMatrixT();
        System.out.println("----");
        mp.printMatrix();
    }

    @Override
    public void draw() {
        image(ct.getFinalImage(), 0, 0);
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"imganalysis.ImgAnalysis"});
    }

}
