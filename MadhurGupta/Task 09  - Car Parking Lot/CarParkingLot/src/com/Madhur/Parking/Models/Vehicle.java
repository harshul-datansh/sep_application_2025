package com.Madhur.Parking.Models;


public class Vehicle {
    private final String numberPlate;

    public Vehicle(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Number Plate='" + numberPlate + '\'' +
                '}';
    }
}
