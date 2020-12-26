package com.pwir;

import com.pwir.craneComponents.*;
import com.pwir.supplier.Supplier;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Crane crane = new TowerCraneBuilder()
                .setStartCoordinates(0,0,0)
                .setJib(50)
                .setMast(100)
                .build();

        Supplier supplier = new Supplier(crane.getJibLength(), crane.getMastHeight());

        System.out.println(supplier);
        System.out.println(crane);

        crane.startTask(supplier.getStartCoordinate(), supplier.getDestinationCoordinate());

        System.out.println(crane);

    }
}
