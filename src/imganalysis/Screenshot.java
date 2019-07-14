/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import com.jogamp.newt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**
 *
 * @author EDDER
 */
public class Screenshot {

    public Screenshot() {
    }
    
    public static BufferedImage takeScreenshot(Robot mrRobot){
        Rectangle rectangle = new Rectangle(1366,768);
        mrRobot.keyPress(KeyEvent.VK_ALT);
        mrRobot.keyPress(KeyEvent.VK_TAB);
        mrRobot.keyPress(KeyEvent.VK_TAB);
        mrRobot.delay(500);
        mrRobot.keyRelease(KeyEvent.VK_TAB);
        mrRobot.keyRelease(KeyEvent.VK_TAB);
        mrRobot.keyRelease(KeyEvent.VK_ALT);
        mrRobot.delay(1000);
        BufferedImage bf = mrRobot.createScreenCapture(rectangle);
        return bf;
    }
}
