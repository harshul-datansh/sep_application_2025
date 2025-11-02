# Prototype Design Pattern

This code demonstrates the Prototype design pattern using a simple Shape class. The purpose of the pattern is to create new objects by copying an existing object instead of creating them from scratch.

## Overview

The Shape class implements the Cloneable interface and overrides the clone() method to return a copy of itself. This allows us to duplicate objects easily without knowing their exact class type at runtime.

## How it Works

In the main method:
- We first create an original Shape object called circle.
- Then we clone it using circle.clone() to create anotherCircle.
- Both objects are independent but have the same data.

## Output

When you run the program, it prints:
Drawing Circle  
Drawing Circle

This shows that the clone has successfully created a copy of the original object.
