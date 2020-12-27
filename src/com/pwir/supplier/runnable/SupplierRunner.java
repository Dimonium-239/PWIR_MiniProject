package com.pwir.supplier.runnable;

import com.pwir.craneComponents.Crane;
import com.pwir.supplier.Supplier;

public class SupplierRunner implements Runnable{
    private final Crane crane;

    public SupplierRunner(Crane crane) {
        this.crane = crane;
    }

    @Override
    public void run() {
        while (true){
            Supplier supplier = new Supplier(crane.getJibLength(), crane.getMastHeight());
            System.out.println(supplier);
            crane.startTask(supplier.getStartCoordinate(), supplier.getDestinationCoordinate());
            System.out.println(crane);
        }
    }
}
