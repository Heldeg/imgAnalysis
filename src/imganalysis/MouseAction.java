/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imganalysis;

import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author EDDER
 */
public class MouseAction {
    public static void doMouseAction(Robot mrRobot,int [] startPoint){
        mrRobot.mouseMove(startPoint[0]+300, startPoint[1]+50);
        mrRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        mrRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
