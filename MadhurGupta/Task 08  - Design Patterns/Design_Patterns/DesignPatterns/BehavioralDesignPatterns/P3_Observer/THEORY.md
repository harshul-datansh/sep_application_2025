# Observer design pattern

---
## Overview
Observer Design Pattern is a behavioral pattern that establishes a one-to-many dependency between objects. When the subject changes its state, all its observers are automatically notified and updated. It focuses on enabling efficient communication and synchronization between objects in response to state changes.

#### When not to use the Observer Design Pattern?

1. When the relationships between objects are simple and don’t require notifications.
2. When performance is a concern, as many observers can lead to overhead during updates.
3. When the subject and observers are tightly coupled, as it defeats the purpose of decoupling.
4. When number of observers is fixed and won’t change over time.
5. When the order of notifications is crucial, as observers may be notified in an unpredictable sequence.

## Components
1. Subject
2. Observer
3. Concrete Objects
4. Concrete Observer

