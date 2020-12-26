package com.pwir.helpers;

public class AngleIterator {
    private int currentIndex;

    public AngleIterator(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Integer next() {
        currentIndex++;
        if (currentIndex > 360)
            return currentIndex = 0;
        return currentIndex;
        }

    public Integer prev() {
        currentIndex--;
        if (currentIndex < 0)
            return currentIndex = 360;
        return currentIndex;
    }
}
