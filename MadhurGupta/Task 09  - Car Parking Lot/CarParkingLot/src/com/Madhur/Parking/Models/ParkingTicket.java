package com.Madhur.Parking.Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final String vehicleNumber;
    private final int spotNumber;
    private final LocalDateTime entryTime;


    public ParkingTicket(String vehicleNumber, int spotNumber) {
        this.ticketId = UUID.randomUUID().toString(); // Generate unique ID
        this.vehicleNumber = vehicleNumber;
        this.spotNumber = spotNumber;
        this.entryTime = LocalDateTime.now(); // Capture entry time
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Parking Ticket {" +
                "\n  Ticket ID: " + ticketId +
                "\n  Vehicle: " + vehicleNumber +
                "\n  Spot Number: " + spotNumber +
                "\n  Entry Time: " + entryTime +
                "\n}";
    }
}
