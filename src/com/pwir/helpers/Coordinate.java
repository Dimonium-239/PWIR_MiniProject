package com.pwir.helpers;

import java.util.Objects;

public class Coordinate {
    public int radius;
    public int angle;
    public int height;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

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
        return "Coordinate: (r: " + ANSI_GREEN + radius + ANSI_RESET + "m" +
                ", a: " + ANSI_RED + angle + ANSI_RESET + "°" +
                ", h: " + ANSI_CYAN + height + ANSI_RESET + "m" +
                ")\n";
    }
}
