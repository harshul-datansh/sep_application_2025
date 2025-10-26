# Liskov Substitution Principle

This project demonstrates the **Liskov Substitution Principle (LSP)** — one of the five SOLID principles of object-oriented design.

---

## Overview

> **Liskov Substitution Principle** (by Barbara Liskov):
>
> *"Objects of a superclass should be replaceable with objects of its subclasses without breaking the application."*

In simpler terms:
- Subclasses should behave in a way that does **not surprise or break** the expectations of the base class.
- They should be **logically substitutable**.

---

##  Bad Design Example (Violates LSP)

In this example, `Ostrich` extends `Bird` and inherits a `fly()` method, even though **ostriches can't fly**.

```java
class Bird {
    public void fly() {
        System.out.println("I can fly!");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly!");
    }
}
