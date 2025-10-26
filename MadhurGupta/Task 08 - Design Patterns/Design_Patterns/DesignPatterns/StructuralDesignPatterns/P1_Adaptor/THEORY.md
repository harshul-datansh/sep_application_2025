# Adaptor design pattern

---

## Overview
Adapter Design Pattern is a structural pattern that acts as a bridge between two incompatible interfaces, allowing them to work together. It is especially useful for integrating legacy code or third-party libraries into a new system.

### Components of Adaptor design pattern

1. Target Interface: The interface expected by the client, defining the operations it can use.
2. Adaptee: The existing class with an incompatible interface that needs integration.
3. Adapter: Implements the target interface and uses the adaptee internally, acting as a bridge.
4. Client: Uses the target interface, unaware of the adapter or adaptee details.

#### Pros of Adapter Design Pattern
Below are the pros of Adapter Design Pattern:

Promotes code reuse without modification.
Keeps classes focused on core logic by isolating adaptation.
Supports multiple interfaces through interchangeable adapters.
Decouples system from implementations, easing modifications and swaps.
#### Cons of Adapter Design Pattern
Below are the cons of Adapter Design Pattern:

Adds complexity and can make code harder to follow.
Introduces slight performance overhead due to extra indirection.
Multiple adapters increase maintenance effort.
Risk of overuse for minor changes, leading to unnecessary complexity.
Handling many interfaces may require multiple adapters, complicating design.
Uses Of Adapter Design Pattern
#### We can use adapter design pattern when:

Enables communication between incompatible systems.
Reuses existing code or libraries without rewriting.
Simplifies integration of new components, keeping the system flexible.
Centralizes compatibility changes, making maintenance easier and safer.
#### When not to use Adapter Design Pattern?
Do not use adapter design pattern when:

If the system is straightforward and all components are compatible, an adapter may be unnecessary.
Adapters can introduce a slight overhead, which might be a concern in performance-sensitive environments.
When there are no issues with interface compatibility, using an adapter can be redundant.
For projects with a very short lifespan, the overhead of implementing an adapter might not be worth it.