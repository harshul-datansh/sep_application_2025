# Iterator design pattern

---
## Overview
It provides to accessing the objects without exposing its underlying implementations

Use when working with Collection frameworks and making objects that take multiple values 
for example -> creating a list of registration  Users

Iterator helps to create common interface that use to implement different types of list , sets etc..

#### Pros

1. loose coupling
2. concurrent iteration
3. Single responsibility principle

#### Cons
1. Overhead for simple collection
2. Increased number of classes