package com.pwir.craneComponents;

import com.pwir.helpers.Coordinate;

public class TowerCraneBuilder {

    private Coordinate startCoordinates = new Coordinate(0,0,0);
    private int mastHeight;
    private int jibLength;

    public TowerCraneBuilder() {}

    public TowerCraneBuilder setJib(int jibLength){
        this.jibLength = jibLength;
        return this;
    }

    public TowerCraneBuilder setMast(int mastHeight){
        this.mastHeight = mastHeight;
        return this;
    }

    public TowerCraneBuilder setStartCoordinates(int startRadius, int startAngle, int startHeight){
        startCoordinates = new Coordinate(startRadius,startAngle,startHeight);
        return this;
    }

    public Crane build(){
        Jib jib = new Jib(jibLength, startCoordinates, mastHeight);
        return new Crane(jib, mastHeight, new Hook());
    }
}
