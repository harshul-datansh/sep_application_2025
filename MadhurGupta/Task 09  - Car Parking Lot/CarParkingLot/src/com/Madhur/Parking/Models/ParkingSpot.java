package com.Madhur.Parking.Models;


public class ParkingSpot {
    private final int spotNumber;
    private boolean isOccupied;
    private String vehicleNumberPlate;


    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;  // Initially empty
        this.vehicleNumberPlate = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getVehicleNumberPlate() {
        return vehicleNumberPlate;
    }


    public void park(String numberPlate) {
        this.vehicleNumberPlate = numberPlate;
        this.isOccupied = true;
    }


    public void unpark() {
        this.vehicleNumberPlate = null;
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        if (isOccupied) {
            return "Spot " + spotNumber + " [Occupied by " + vehicleNumberPlate + "]";
        } else {
            return "Spot " + spotNumber + " [Free]";
        }
    }
}
