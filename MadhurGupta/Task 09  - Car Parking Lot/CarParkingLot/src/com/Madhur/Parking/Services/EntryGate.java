package com.Madhur.Parking.Services;


import com.Madhur.Parking.Models.Vehicle;

public class EntryGate {
    private final int gateId;

    public EntryGate(int gateId) {
        this.gateId = gateId;
    }

    public void processEntry(Vehicle car) {
        System.out.println("\nCar arrived at Entry Gate " + gateId + ": " + car.getNumberPlate());
        ParkingLot lot = ParkingLot.getInstance();
        boolean parked = lot.vehiclePark(car.getNumberPlate());
        if (parked) {
            System.out.println(" Entry successful at Gate " + gateId);
        } else {
            System.out.println(" Entry failed at Gate " + gateId);
        }
    }
}
