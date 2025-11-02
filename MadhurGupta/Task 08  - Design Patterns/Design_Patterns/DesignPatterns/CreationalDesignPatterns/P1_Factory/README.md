#  Factory Design Pattern in Java

This project demonstrates the **Factory Design Pattern** using a simple example of creating shapes (`Circle`, `Rectangle`, `Square`).

The **Factory Pattern** is one of the most commonly used **Creational Design Patterns**.  
It provides a **single point of object creation** so that client code does not need to know about the exact class that is being created.

---

##  What is the Factory Design Pattern?

> **Definition:**  
> The Factory Pattern defines an interface or abstract class for creating objects, but lets subclasses or a factory class decide which class to instantiate.  
> This pattern allows you to create objects without exposing the creation logic to the client and refers to the newly created object using a common interface.

---

##  Why Use Factory Pattern?

Without a factory, you would write something like this in your main method:

```java
Shape shape1 = new Circle();
Shape shape2 = new Rectangle();
Shape shape3 = new Square();
