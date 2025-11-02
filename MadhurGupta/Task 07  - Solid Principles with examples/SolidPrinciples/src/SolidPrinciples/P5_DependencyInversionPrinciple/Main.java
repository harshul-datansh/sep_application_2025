package SolidPrinciples.P5_DependencyInversionPrinciple;

public class Main {
    public static void main(String[] args) {
        Switchable bulb = new LightBulb();
        Switch lightSwitch = new Switch(bulb);

        lightSwitch.operate();
    }
}
