package com.pwir.craneComponents.runnable;

import com.pwir.craneComponents.Jib;
import com.pwir.helpers.Coordinate;

public class VerticalEngine extends Engine{

    public VerticalEngine(Jib jib, Coordinate coordinate) {
        super(jib, coordinate);
    }

    @Override
    public void run() {
        while(jib.getCurrentCoordinate().height != coordinate.height) {
            if (jib.getCurrentCoordinate().height > coordinate.height) {
                jib.setLower();
//                System.out.printf("Height: %d\n", jib.getCurrentCoordinate().height);
            }
            else if (jib.getCurrentCoordinate().height < coordinate.height) {
                jib.setHigher();
//                System.out.printf("Height: %d\n", jib.getCurrentCoordinate().height);
            }
        }
    }
}
