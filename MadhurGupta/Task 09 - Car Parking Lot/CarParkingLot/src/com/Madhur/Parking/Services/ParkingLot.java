package com.Madhur.Parking.Services;

import com.Madhur.Parking.Models.ParkingSpot;
import com.Madhur.Parking.Models.ParkingTicket;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class ParkingLot {

    // Singleton instance
    private static ParkingLot instance;

    // Total capacity of the parking lot
    private final int capacity;

    // Stores all parking spots: <spotNumber, ParkingSpot>
    private final Map<Integer, ParkingSpot> allSpots;

    // Maps vehicle number plates to their ParkingSpot
    private final Map<String, ParkingSpot> parkedVehicles;

    // Stores parking tickets issued for each vehicle: <vehicleNumber, ParkingTicket>
    private final Map<String, ParkingTicket> issuedTickets;

    // Min-heap to manage free spot numbers (lowest spot gets assigned first)
    private final PriorityQueue<Integer> freeSpots;

    /**
     * Private constructor (Singleton pattern). Initializes parking lot.
     */
    private ParkingLot(int capacity) {
        this.capacity = capacity;
        this.allSpots = new HashMap<>();
        this.parkedVehicles = new HashMap<>();
        this.issuedTickets = new HashMap<>();
        this.freeSpots = new PriorityQueue<>();

        // Initialize all spots and add them to the free queue
        for (int i = 1; i <= capacity; i++) {
            ParkingSpot spot = new ParkingSpot(i);
            allSpots.put(i, spot);
            freeSpots.offer(i);
        }
    }

    /**
     * Initialize the ParkingLot singleton with a given capacity.
     */
    public static ParkingLot getInstance(int capacity) {
        if (instance == null) {
            instance = new ParkingLot(capacity);
        }
        return instance;
    }

    /**
     * Get the existing ParkingLot instance (after it's initialized).
     */
    public static ParkingLot getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ParkingLot not initialized. Call getInstance(capacity) first.");
        }
        return instance;
    }


    public boolean vehiclePark(String numberPlate) {
        if (parkedVehicles.containsKey(numberPlate)) {
            System.out.println(" Vehicle is already parked.");
            return false;
        }

        if (freeSpots.isEmpty()) {
            System.out.println(" Parking is full.");
            return false;
        }

        int spotNumber = freeSpots.poll();  // Get available spot
        ParkingSpot spot = allSpots.get(spotNumber);

        // Park the vehicle in the spot
        spot.park(numberPlate);
        parkedVehicles.put(numberPlate, spot);

        // Generate parking ticket
        ParkingTicket ticket = new ParkingTicket(numberPlate, spotNumber);
        issuedTickets.put(numberPlate, ticket);

        System.out.println(" Vehicle " + numberPlate + " parked at spot: " + spotNumber);
        System.out.println(ticket);  // Print ticket details

        return true;
    }


    public boolean vehicleUnpark(String numberPlate) {
        if (!parkedVehicles.containsKey(numberPlate)) {
            System.out.println(" Vehicle not found in parking.");
            return false;
        }

        // Get the spot and unpark it
        ParkingSpot spot = parkedVehicles.remove(numberPlate);
        spot.unpark();
        freeSpots.offer(spot.getSpotNumber());

        // Remove and show ticket info
        ParkingTicket ticket = issuedTickets.remove(numberPlate);

        System.out.println(" Vehicle " + numberPlate + " unparked from spot: " + spot.getSpotNumber());
        if (ticket != null) {
            System.out.println(" Ticket Closed: " + ticket.getTicketId());
        }

        return true;
    }

    /**
     * Get number of currently available spots.
     */
    public int getAvailableSpots() {
        return freeSpots.size();
    }

    /**
     * Get total parking capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Print current status of the parking lot.
     */
    public void printStatus() {
        System.out.println("\n Parking Lot Status ");
        System.out.println("Total Capacity: " + capacity);
        System.out.println("Available Spots: " + getAvailableSpots());

        if (parkedVehicles.isEmpty()) {
            System.out.println("No vehicles currently parked.");
        } else {
            System.out.println("Parked Vehicles:");
            for (Map.Entry<String, ParkingSpot> entry : parkedVehicles.entrySet()) {
                String number = entry.getKey();
                ParkingSpot spot = entry.getValue();
                System.out.println("  🔹 " + number + " -> Spot " + spot.getSpotNumber());
            }
        }

        System.out.println("\n");
    }
}
