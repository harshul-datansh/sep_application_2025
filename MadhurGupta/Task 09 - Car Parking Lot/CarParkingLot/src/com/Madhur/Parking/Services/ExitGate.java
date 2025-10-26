package com.Madhur.Parking.Services;

import com.Madhur.Parking.Models.Vehicle;


public class ExitGate {
    private final int gateId;

    public ExitGate(int gateId) {
        this.gateId = gateId;
    }


    public void processExit(Vehicle car) {
        System.out.println("\n Car attempting to exit via Gate " + gateId + ": " + car.getNumberPlate());
        ParkingLot lot = ParkingLot.getInstance();
        boolean unparked = lot.vehicleUnpark(car.getNumberPlate());
        if (unparked) {
            System.out.println(" Exit successful at Gate " + gateId);
        } else {
            System.out.println(" Exit failed at Gate " + gateId + ". Vehicle not found.");
        }
    }
}
