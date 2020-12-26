package com.pwir.craneComponents;

import com.pwir.craneComponents.runnable.HorizontalEngine;
import com.pwir.craneComponents.runnable.VerticalEngine;
import com.pwir.helpers.Coordinate;
import com.pwir.craneComponents.runnable.RotatorEngine;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Crane {
    private final Jib jib;
    private final Hook hook;
    private final int mastHeight;

    public Crane(Jib jib, int mastHeight, Hook hook) {
        this.jib = jib;
        this.hook = hook;
        this.mastHeight = mastHeight;

    }

    public synchronized void startTask(Coordinate startCoordinate, Coordinate destinationCoordinate){

        moveToPosition(startCoordinate);
        loadCargo();
        System.out.println(this);
        moveToPosition(destinationCoordinate);
        unloadCargo();
    }

    private void loadCargo(){
        hook.setLoaded(true);
    }

    private void unloadCargo(){
        hook.setLoaded(false);
    }

    private synchronized void moveToPosition(Coordinate position){
        RotatorEngine rotatorEngine = new RotatorEngine(jib, position);
        VerticalEngine verticalEngine = new VerticalEngine(jib, position);
        HorizontalEngine horizontalEngine = new HorizontalEngine(jib, position);

        List<Runnable> workers = List.of(rotatorEngine, verticalEngine, horizontalEngine);
        ExecutorService executor = Executors.newCachedThreadPool();
        workers.forEach(executor::execute);
        executor.shutdown();
        while (!executor.isTerminated() || !executor.isShutdown());
    }

    public int getJibLength(){
        return jib.getJibLength();
    }

    public int getMastHeight(){
        return mastHeight;
    }

    public Hook getHook(){
        return hook;
    }

    @Override
    public String toString() {
        return "\n####    Crane   ####\n" +
                hook +
                jib;
    }
}
