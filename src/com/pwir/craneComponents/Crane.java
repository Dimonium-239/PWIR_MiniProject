package com.pwir.craneComponents;

import com.pwir.craneComponents.runnable.HorizontalEngine;
import com.pwir.craneComponents.runnable.VerticalEngine;
import com.pwir.helpers.Coordinate;
import com.pwir.craneComponents.runnable.RotatorEngine;
import com.pwir.supplier.runnable.SupplierRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Crane implements Runnable {
    private final Jib jib;
    private final Hook hook;
    private final int mastHeight;
    private SupplierRunner supplierRunner;

    public Crane(Jib jib, int mastHeight, Hook hook) {
        this.jib = jib;
        this.hook = hook;
        this.mastHeight = mastHeight;
        jib.associateWithCrane(this);
    }

    public void associateWithSupplier(SupplierRunner supplierRunner) {
        this.supplierRunner = supplierRunner;
    }

    public synchronized void startTask(Coordinate[] coordinates) {
        Coordinate startCoordinate = coordinates[0];
        Coordinate destinationCoordinate = coordinates[1];
        moveToPosition(startCoordinate);
        loadCargo();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveToPosition(destinationCoordinate);
        unloadCargo();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadCargo() {
        hook.setLoaded(true);
    }

    private void unloadCargo() {
        hook.setLoaded(false);
    }

    private synchronized void moveToPosition(Coordinate position) {
        RotatorEngine rotatorEngine = new RotatorEngine(jib, position);
        VerticalEngine verticalEngine = new VerticalEngine(jib, position);
        HorizontalEngine horizontalEngine = new HorizontalEngine(jib, position);

        List<Runnable> workers = Arrays.asList(rotatorEngine, verticalEngine, horizontalEngine);
        ExecutorService executor = Executors.newCachedThreadPool();
        workers.forEach(executor::execute);
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ignored) {}
    }

    public int getJibLength() {
        return jib.getJibLength();
    }

    public int getMastHeight() {
        return mastHeight;
    }

    public Hook getHook() {
        return hook;
    }

    @Override
    public String toString() {
        for (int i = 0; i < 50; ++i) System.out.println();
        System.out.println(this.supplierRunner);
        return "\n####    Crane   ####\n" +
                hook +
                jib;
    }

    @Override
    public void run() {
        while (true) {
            try{
            if (this.supplierRunner.checkSuppliersAvailable()) {
                this.startTask(this.supplierRunner.getLastSuppliersCoordinate());
            }}catch (Exception e) {
                System.out.println(e);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
