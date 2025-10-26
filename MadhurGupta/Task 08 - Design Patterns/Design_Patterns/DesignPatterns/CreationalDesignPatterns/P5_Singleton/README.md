# Singleton Design Pattern

This code demonstrates the Singleton design pattern. The Singleton pattern ensures that only one instance of a class is created during the entire lifetime of the application.

## Overview

The Singleton class has:
- A private static variable `instance` that stores the single object.
- A private constructor so no other class can create an object directly.
- A public static method `getInstance()` that creates the instance if it does not exist and returns it.

## How it Works

In the Main class:
- We call `Singleton.getInstance()` to get the single instance of the class.
- Then we call `doSomething()` to perform an action.
- If `getInstance()` is called again, it will return the same object instead of creating a new one.

## Output

When you run the program, it prints:
Singleton is Instantiated.  
Something is Done.

This shows that only one object of the class is created and used.
