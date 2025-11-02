# Builder Design Pattern

---
The Builder Design Pattern is a creational pattern that provides a step-by-step approach to constructing complex objects. It separates the construction process from the object’s representation, enabling the same process to create different variations of an object. This pattern is especially useful when an object requires multiple steps or configurations during creation.

### Components of Builder design Pattern
1. Product
2. Builder
3. Concrete Builder
4. Director(Optional)
5. Client

#### When to use Builder desrign pattern
1. Complex object creation
2. Step-by-step-construction
3. configure object creation

#### When not to use
1. Simple object creation
2. Performance issue
3. immutable objects with final fields
4. tight coupling with product

