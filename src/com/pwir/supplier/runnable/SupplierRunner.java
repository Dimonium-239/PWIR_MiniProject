package com.pwir.supplier.runnable;

import com.pwir.craneComponents.Crane;
import com.pwir.helpers.Coordinate;
import com.pwir.supplier.Supply;

import java.util.*;

public class SupplierRunner implements Runnable {
    private final Crane crane;
    private LinkedList<Supply> supplies = new LinkedList<>();
    private Supply currentSupply;

    public SupplierRunner(Crane crane) {
        this.crane = crane;
        this.crane.associateWithSupplier(this);
    }

    public boolean checkSuppliersAvailable(){
        return !supplies.isEmpty();
    }

    public Coordinate[] getLastSuppliersCoordinate() {
        Coordinate[] coordinates = new Coordinate[2];
        this.currentSupply = this.supplies.removeFirst();
        coordinates[0] = this.currentSupply.getStartCoordinate();
        coordinates[1] = this.currentSupply.getDestinationCoordinate();
        return coordinates;
    }

    @Override
    public String toString() {
        String to_return = "";
        if (this.checkSuppliersAvailable()){
            to_return += "Supplies in the queue: " + this.supplies.size();
        }
        else{
            to_return += "NO SUPPLIES AVAILABLE IN THE QUEUE";
        }
        return this.currentSupply.toString() + to_return;
    }

    @Override
    public void run() {
        while (true) {
            Supply supply = new Supply(crane.getJibLength(), crane.getMastHeight());
            this.supplies.add(supply);
            try {
                Thread.sleep(10000 + new Random(0).nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
