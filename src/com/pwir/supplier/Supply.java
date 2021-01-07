package com.pwir.supplier;

import com.pwir.helpers.Coordinate;

import java.util.concurrent.ThreadLocalRandom;

public class Supply {
    private final Coordinate startCoordinate;
    private final Coordinate destinationCoordinate;

    public Supply(int jibLength, int mastHeight) {
        int distFromCraneStart = ThreadLocalRandom.current().nextInt(0, jibLength);
        int distFromCraneDest = ThreadLocalRandom.current().nextInt(0, jibLength );

        int angelStart = ThreadLocalRandom.current().nextInt(0, 360);
        int angelDest = ThreadLocalRandom.current().nextInt(0, 360);

        int heightStart = ThreadLocalRandom.current().nextInt(0, mastHeight);
        int heightDest = ThreadLocalRandom.current().nextInt(0, mastHeight);

        startCoordinate = new Coordinate(distFromCraneStart, angelStart, heightStart);
        destinationCoordinate = new Coordinate(distFromCraneDest, angelDest, heightDest);
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public Coordinate getDestinationCoordinate() {
        return destinationCoordinate;
    }

    @Override
    public String toString() {
        return "\n####  Supplier  ####" +
                "\nstartCoordinate:" + startCoordinate +
                "\ndestinationCoordinate:" + destinationCoordinate;
    }
}
