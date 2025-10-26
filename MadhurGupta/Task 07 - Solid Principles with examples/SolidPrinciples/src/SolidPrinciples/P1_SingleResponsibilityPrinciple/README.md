# Single Responsibility Principle

This project demonstrates the **Single Responsibility Principle (SRP)** — the first principle of SOLID.

## Description

I created a `User` class that contains two methods: `getName()` and `getEmail()`.

Initially, I wanted the `User` class to also print user details. However, doing so would **violate SRP**, because the class would have **more than one responsibility**:
- Managing user data
- Handling display logic

To follow SRP, I created a separate class called `UserPrinter`, which is responsible for printing user details.

## Classes

- `User`: Holds user data (`name` and `email`)
- `UserPrinter`: Responsible for displaying user information

## Purpose

This separation ensures each class has **only one reason to change**, adhering to the Single Responsibility Principle.


