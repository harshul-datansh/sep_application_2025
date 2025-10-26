package SolidPrinciples.P5_DependencyInversionPrinciple;

public class Switch {
    private Switchable device;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void operate() {
        device.turnOn(); // Works with any Switchable device
        //device.turnOff();
    }
}
