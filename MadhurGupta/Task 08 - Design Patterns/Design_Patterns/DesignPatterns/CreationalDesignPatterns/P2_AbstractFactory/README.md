# Abstract factory pattern

# Abstract Factory Design Pattern

This code demonstrates the Abstract Factory design pattern using a furniture example. The goal of the pattern is to create families of related objects (like chairs, sofas, and coffee tables) without specifying their concrete classes directly.

## Overview

The program defines three product types: Chair, Sofa, and CoffeeTable.  
Each product has two style variants: Modern and Victorian.  
Instead of creating objects directly, we use a factory interface called FurnitureFactory that declares methods to create each product.

Two concrete factories implement this interface:
- ModernFurnitureFactory (creates modern style furniture)
- VictorianFurnitureFactory (creates victorian style furniture)

## How it Works

The Room class is the client that uses a factory passed in its constructor. It calls the factory methods to create matching furniture and then uses them inside the relax() method.

In the main method, we create two rooms:
- One with ModernFurnitureFactory
- One with VictorianFurnitureFactory

This way we can easily switch the entire set of furniture by just changing the factory we pass.

## Output

When you run the program, it prints which furniture is present in the room and simulates sitting, lying, and putting a cup on the table for both modern and victorian styles.
