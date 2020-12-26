package com.pwir.craneComponents.runnable;

import com.pwir.craneComponents.Jib;
import com.pwir.helpers.Coordinate;

public abstract class Engine implements Runnable{
    protected final Jib jib;
    protected final Coordinate coordinate;

    public Engine(Jib jib, Coordinate coordinate) {
        this.jib = jib;
        this.coordinate = coordinate;
    }
}
