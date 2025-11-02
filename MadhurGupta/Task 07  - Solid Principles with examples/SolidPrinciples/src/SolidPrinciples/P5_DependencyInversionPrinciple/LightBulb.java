package SolidPrinciples.P5_DependencyInversionPrinciple;

public class LightBulb implements  Switchable{

    @Override
    public void turnOn() {
        System.out.println("Light bulb turned on");
    }

    @Override
    public void turnOff() {
        System.out.println("Light bulb turned off");
    }
}
