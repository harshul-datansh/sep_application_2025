# Factory design pattern

## Overview
The Factory Method is a creational design pattern that defines an interface for creating objects in a superclass while allowing subclasses to decide which objects to instantiate. It delegates the responsibility of object creation to a dedicated method, promoting loose coupling between the creator and the products and making the system more flexible and extensible.

#### When to Use the Factory Method
1. When object creation logic is complex or varies based on conditions.
2. When the system needs to be open for extension but closed for modification (OCP).
3. When new product types may be added in the future.
4. When you want to decouple client code from concrete implementations.

#### Components of Factory Method Design Pattern


1. Product: Abstract interface or class for objects created by the factory.
2. Concrete Product: The actual object that implements the product interface.
3. Creator (Factory Interface/Abstract Class): Declares the factory method.
4. Concrete Creator (Concrete Factory): Implements the factory method to create specific products.