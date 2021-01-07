package com.pwir.craneComponents.runnable;

import com.pwir.craneComponents.Jib;
import com.pwir.helpers.Coordinate;

/**
 * One time rotator.
 */
public class RotatorEngine extends Engine {

    public RotatorEngine(Jib jib, Coordinate coordinate) {
        super(jib, coordinate);
    }

    @Override
    public void run() {
        while (jib.getCurrentCoordinate().angle != coordinate.angle) {
            if (jib.getCurrentCoordinate().angle - coordinate.angle < 180 &&
                    jib.getCurrentCoordinate().angle - coordinate.angle >= 0) {
                jib.rotateCCW();
//                System.out.printf("Angle: %d\n", jib.getCurrentCoordinate().angle);
            } else {
                jib.rotateCW();
//                System.out.printf("Angle: %d\n", jib.getCurrentCoordinate().angle);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
