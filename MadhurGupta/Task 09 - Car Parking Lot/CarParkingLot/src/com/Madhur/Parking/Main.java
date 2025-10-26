package com.Madhur.Parking;

import com.Madhur.Parking.Models.Vehicle;
import com.Madhur.Parking.Services.EntryGate;
import com.Madhur.Parking.Services.ExitGate;
import com.Madhur.Parking.Services.ParkingLot;

public class Main {
    public static void main(String[] args) {
        System.out.println("Parking Lot System Started");

        // Step 1: Initialize ParkingLot with 3 spots
        ParkingLot parkingLot = ParkingLot.getInstance(3);

        // Step 2: Create Entry and Exit Gates
        EntryGate entryGate1 = new EntryGate(1);
        ExitGate exitGate1 = new ExitGate(1);

        // Step 3: Create Car objects
        Vehicle car1 = new Vehicle("KA-01-AA-1234");
        Vehicle car2 = new Vehicle("KA-01-AA-5678");
        Vehicle car3 = new Vehicle("KA-01-AA-9999");
        Vehicle car4 = new Vehicle("KA-01-AA-0001"); // Extra car for testing full condition

        // Step 4: Process entries
        entryGate1.processEntry(car1);
        entryGate1.processEntry(car2);
        entryGate1.processEntry(car3);

        // Step 5: Attempt to park when full
        entryGate1.processEntry(car4); // Should show "Parking Full"

        // Step 6: Unpark a vehicle
        exitGate1.processExit(car2);   // Car2 exits

        // Step 7: Try again to park car4 (should now succeed)
        entryGate1.processEntry(car4); // Should park now

        // Step 8: Print final parking status
        parkingLot.printStatus();

        System.out.println("Parking Lot Simulation Complete");
    }
}
