# Open Closed Principle

This project demonstrates the **Open/Closed Principle (OCP)** — one of the SOLID principles of object-oriented design.

## Overview


> *"Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification."*

This means you should be able to add new features or behaviors **without modifying existing code**.

---

##  Example Description

We implement a system that calculates the area of different geometric shapes.

###  How it follows OCP:

- We define a `Shape` interface with a `calculateArea()` method.
- Each shape (e.g. `Circle`, `Rectangle`) implements this interface.
- `AreaCalculator` depends on the abstraction (`Shape`) and not on specific shapes.

➡ When a new shape is needed (like `Triangle`), we can **extend the system** by simply creating a new class that implements `Shape` — without modifying `AreaCalculator`.

---



