package com.pwir.craneComponents;

import com.pwir.helpers.*;

public class Jib {
    private final int jibLength;
    private final int mastHeight;
    private final Coordinate currentCoordinate;
    private final AngleIterator angleIterator;


    public Jib(int jibLength, Coordinate currentCoordinate, int mastHeight) {
        this.jibLength = jibLength;
        this.currentCoordinate = currentCoordinate;
        this.angleIterator = new AngleIterator(currentCoordinate.angle);
        this.mastHeight = mastHeight;
    }

    public int getJibLength() {
        return jibLength;
    }

    public Coordinate getCurrentCoordinate() {
        return currentCoordinate;
    }

    public void setHigher(){
        if (currentCoordinate.height < mastHeight)
            currentCoordinate.height++;
    }

    public void setLower(){
        if (currentCoordinate.height > 0)
            currentCoordinate.height--;
    }

    public void setRadiusLonger(){
        if (currentCoordinate.radius < jibLength)
            currentCoordinate.radius++;
    }

    public void setRadiusShorter(){
        if (currentCoordinate.radius > 0)
            currentCoordinate.radius--;
    }

    public void rotateCW(){
        currentCoordinate.angle = angleIterator.next();
    }

    public void rotateCCW(){
        currentCoordinate.angle = angleIterator.prev();
    }

    @Override
    public String toString() {
        return "\nJibs " +
                "currentCoordinate:\n" + currentCoordinate;
    }
}
