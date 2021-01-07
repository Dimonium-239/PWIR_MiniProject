package com.pwir.supplier.runnable;

import com.pwir.craneComponents.Crane;
import com.pwir.helpers.Coordinate;
import com.pwir.supplier.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SupplierRunner implements Runnable {
    private final Crane crane;
    private List<Supplier> suppliers = new ArrayList<Supplier>();

    public SupplierRunner(Crane crane) {
        this.crane = crane;
        this.crane.associateWithSupplier(this);
    }

    public boolean checkSuppliersAvailable(){
        return suppliers.isEmpty();
    }

    public Coordinate[] getLastSuppliersCoordinate() {
        Coordinate[] coordinates = new Coordinate[2];
        coordinates[0] = this.suppliers.get(0).getStartCoordinate();
        coordinates[1] = this.suppliers.get(0).getDestinationCoordinate();
        return coordinates;
    }

    @Override
    public String toString() {
        return this.suppliers.get(0).toString();
    }

    @Override
    public void run() {
        while (true) {
            Supplier supplier = new Supplier(crane.getJibLength(), crane.getMastHeight());
            this.suppliers.add(supplier);
            try {
                Thread.sleep(new Random(1000).nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
