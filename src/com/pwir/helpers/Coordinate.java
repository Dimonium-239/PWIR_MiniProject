package com.pwir.helpers;

import java.util.Objects;

public class Coordinate {
    public int radius;
    public int angle;
    public int height;

    public Coordinate(int radius, int angle, int height) {
        this.radius = radius;
        this.angle = angle;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return radius == that.radius && angle == that.angle && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, angle, height);
    }

    @Override
    public String toString() {
        return "Coord: (r: " + radius +
                ", a: " + angle + "°" +
                ", h: " + height +
                ")\n";
    }
}
