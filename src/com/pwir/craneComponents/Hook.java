package com.pwir.craneComponents;

public class Hook {

    private boolean isLoaded = false;

    public void setLoaded(boolean loaded) {
        this.isLoaded = loaded;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public String toString() {
        return "Cargo: " + isLoaded;
    }
}
