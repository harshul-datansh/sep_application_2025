# Dependency Inversion Principle

This project demonstrates the **Dependency Inversion Principle (DIP)** — one of the SOLID principles of object-oriented design.

---

## Principle Overview

> **Dependency Inversion Principle** states:
>
> *"High-level modules should not depend on low-level modules. Both should depend on abstractions.  
> Abstractions should not depend on details. Details should depend on abstractions."*

In simpler terms:
- High-level components (like switches) should depend on interfaces or abstractions.
- Low-level components (like light bulbs, fans) implement those abstractions.
- This reduces tight coupling and increases flexibility.

---

##  Bad Design (Violates DIP)

```java
class LightBulb {
    public void turnOn() {
        System.out.println("LightBulb turned on");
    }

    public void turnOff() {
        System.out.println("LightBulb turned off");
    }
}

class Switch {
    private LightBulb bulb;

    public Switch(LightBulb bulb) {
        this.bulb = bulb;
    }

    public void operate() {
        bulb.turnOn(); // Directly depends on LightBulb concrete class
    }
}
