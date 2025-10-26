package Oops.oops_theory.Interfaces;

public class Main {
    public static void main(String[] args) {
        Car car  = new Car();
        car.start();
        car.accelerate();
        car.brake();
        car.stop();

        Engine car1 = new Car(); // you can do this same rules apply what we studied back
       // car.a; // can't access
    }
}
