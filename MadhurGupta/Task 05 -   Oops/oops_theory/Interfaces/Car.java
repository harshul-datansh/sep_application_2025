package Oops.oops_theory.Interfaces;

public class Car implements Engine , Brake{

    int a = 10;
    @Override
    public void brake() {
        System.out.println("I apply break as a normal car");
    }

    @Override
    public void start() {
        System.out.println("I start car as a normal car");
    }

    @Override
    public void stop() {
        System.out.println("I stop car as a normal car");
    }

    @Override
    public void accelerate() {
        System.out.println("I accelerate car as a normal car");
    }
}
