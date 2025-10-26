# Builder Design Pattern

This code demonstrates the Builder design pattern using a Burger example. The Builder pattern is used to construct a complex object step by step and allows creating different representations of the object.

## Overview

The `BurgerBuilder` class allows setting various parts of a burger like bun, patty, cheese, lettuce, tomato, and sauce. Each setter method returns the builder itself, allowing method chaining.

The `build()` method creates a `Burger` object using the values set in the builder.

## How it Works

- You first create a `BurgerBuilder` object.
- Then, you call the setter methods to configure the burger as desired.
- Finally, call `build()` to get a `Burger` object with all the specified ingredients.

This approach makes it easy to create burgers with different combinations without creating multiple constructors or complex initialization code.
