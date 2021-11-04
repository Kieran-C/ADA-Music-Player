package com.kierancaruana.adamusicplayer.helpers;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;

public class Idle extends Thread{
    /**
     * Thread that checks if user is idle
     */
    public void run() {
        boolean active = true;
        int[] lastSavedMouseCoords = {900000,900000};
        while (true){
            int delay;
            if (active){delay = 30000;}
            else{delay = 100;}
            int currentX = MouseInfo.getPointerInfo().getLocation().x;
            int currentY = MouseInfo.getPointerInfo().getLocation().y;
            try {
                if ((lastSavedMouseCoords[0] != currentX) || (lastSavedMouseCoords[1] != currentY)){
                    active = true;
                }else{
                    active = false;
                }
                lastSavedMouseCoords[0] = currentX;
                lastSavedMouseCoords[1] = currentY;
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Active Status: " +active);
        }
    }
}
