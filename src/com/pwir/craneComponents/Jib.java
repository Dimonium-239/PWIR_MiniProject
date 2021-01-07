package com.pwir.craneComponents;

import com.pwir.helpers.*;

public class Jib {
    private final int jibLength;
    private final int mastHeight;
    private final Coordinate currentCoordinate;
    private final AngleIterator angleIterator;
    private Crane crane;


    public Jib(int jibLength, Coordinate currentCoordinate, int mastHeight) {
        this.jibLength = jibLength;
        this.currentCoordinate = currentCoordinate;
        this.angleIterator = new AngleIterator(currentCoordinate.angle);
        this.mastHeight = mastHeight;
    }

    public void associateWithCrane(Crane crane){
        this.crane = crane;
    }

    public int getJibLength() {
        return jibLength;
    }

    public Coordinate getCurrentCoordinate() {
        return currentCoordinate;
    }

    public void setHigher(){
        synchronized( System.class ) {
            System.out.println(crane);
        }        if (currentCoordinate.height < mastHeight)
            currentCoordinate.height++;
    }

    public void setLower(){
        synchronized( System.class ) {
            System.out.println(crane);
        }        if (currentCoordinate.height > 0)
            currentCoordinate.height--;
    }

    public void setRadiusLonger(){
        synchronized( System.class ) {
            System.out.println(crane);
        }        if (currentCoordinate.radius < jibLength)
            currentCoordinate.radius++;
    }

    public void setRadiusShorter(){
        synchronized( System.class ) {
            System.out.println(crane);
        }        if (currentCoordinate.radius > 0)
            currentCoordinate.radius--;
    }

    public void rotateCW(){
        synchronized( System.class ) {
            System.out.println(crane);
        }        currentCoordinate.angle = angleIterator.next();
    }

    public void rotateCCW(){
        synchronized( System.class )
        {
            System.out.println(crane);
        }
        currentCoordinate.angle = angleIterator.prev();
    }

    @Override
    public String toString() {
        return "\nJibs " +
                "currentCoordinate:\n" + currentCoordinate;
    }
}
