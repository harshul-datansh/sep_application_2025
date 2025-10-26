# Parking Lot class 

---
## Overview

ParkingLot is a singleton class that models a simple parking lot manager.
It keeps track of all parking spots, which spots are free, which vehicles are parked where, and the tickets issued. It exposes methods to park a vehicle, unpark a vehicle, query available/total spots, and print the current status.

### Fields of class
1. instance — the singleton instance. Only one ParkingLot object is intended to exist.

2. capacity — total number of spots in the lot.

3. allSpots — map of spotNumber → ParkingSpot object (keeps metadata and operations for each spot).

4. parkedVehicles — map of vehicle numberPlate → ParkingSpot where that vehicle is parked. Fast lookup to check if a 
vehicle is already parked or to find its spot when unparking.

5. issuedTickets — map of vehicle numberPlate → ParkingTicket for tracking tickets issued to currently parked vehicles.

6. freeSpots — a min-heap (priority queue) of free spot numbers. Using a min-heap ensures the lowest available spot 
number is assigned first.
# Parking Ticket class

---
## Overview
Short summary first: this ParkingTicket class represents a ticket issued when a vehicle parks. Each ticket has a unique ID, the vehicle’s number plate, the parking spot number, and the entry time. All fields are final so once a ticket is created its data cannot change.

# Vehicle

## Overview
This is a private, final field named numberPlate.

It stores the license plate or registration number of the vehicle.

Being private means it can't be accessed directly from outside the class.

Being final means it can only be set once — when the object is created — and can't be changed later.

>    @Override
public String toString() {
return "Car{" +
"Number Plate='" + numberPlate + '\'' +
'}';
}

# EntryGate class 

## Overview

The EntryGate class represents a physical entry point to a parking lot — like an actual gate where a car drives in.

This class handles the logic for allowing a vehicle to enter the parking lot through that gate.

# ExitGate class

## Overview

ts job is to handle the exit of a vehicle from the parking lot.

It interacts with the ParkingLot service to unpark a vehicle using its number plate.

Like EntryGate, it's part of the Services layer — it performs business logic.

