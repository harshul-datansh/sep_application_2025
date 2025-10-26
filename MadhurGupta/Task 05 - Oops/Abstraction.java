package Oops;

// Top-level abstract class
abstract class Vehicle {
    // Abstract methods (what every vehicle must do)
    abstract void accelerate();
    abstract void brake();

    // Concrete method (common to all vehicles)
    void startEngine() {
        System.out.println("Engine started!");
    }
}

// Concrete class implementing the abstract class
class Car extends Vehicle {
    @Override
    void accelerate() {
        System.out.println("Car: Pressing gas pedal...");
        // Hidden complex logic: fuel injection, gear shifting, etc.
    }

    @Override
    void brake() {
        System.out.println("Car: Applying brakes...");
        // Hidden logic: hydraulic pressure, brake pads, etc.
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Vehicle myCar = new Car(); // Polymorphic reference
        myCar.startEngine();      // Concrete method from Vehicle
        myCar.accelerate();       // Car's implementation
        myCar.brake();            // Car's implementation
    }
}
