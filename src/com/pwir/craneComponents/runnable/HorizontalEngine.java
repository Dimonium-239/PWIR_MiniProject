package com.pwir.craneComponents.runnable;

import com.pwir.craneComponents.Jib;
import com.pwir.helpers.Coordinate;

public class HorizontalEngine extends Engine{

    public HorizontalEngine(Jib jib, Coordinate coordinate) {
        super(jib, coordinate);
    }

    @Override
    public void run() {
        while(jib.getCurrentCoordinate().radius != coordinate.radius) {
            if (jib.getCurrentCoordinate().radius > coordinate.radius) {
                jib.setRadiusShorter();
                System.out.printf("Radius: %d\n", jib.getCurrentCoordinate().radius);
            }
            else if (jib.getCurrentCoordinate().radius < coordinate.radius) {
                jib.setRadiusLonger();
                System.out.printf("Radius: %d\n", jib.getCurrentCoordinate().radius);
            }
        }
    }
}
