package com.pwir;

import com.pwir.craneComponents.*;
import com.pwir.supplier.runnable.SupplierRunner;

public class Main {

    public static void main(String[] args) {
        Crane crane = new TowerCraneBuilder()
                .setStartCoordinates(0,0,0)
                .setJib(50)
                .setMast(100)
                .build();
        Thread craneThread = new Thread(crane);
        craneThread.start();


        Thread supplierThread = new Thread(new SupplierRunner(crane));
        supplierThread.start();
    }
}
