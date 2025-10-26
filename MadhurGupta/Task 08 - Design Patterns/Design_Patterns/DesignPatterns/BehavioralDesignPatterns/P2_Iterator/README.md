This code demonstrates the Iterator design pattern, which allows sequential access to elements of a collection without exposing its internal structure.

It has a `Container` interface that defines a method `getIterator()` to get an iterator, and an `Iterator` interface that defines `hasNext()` and `next()` methods to traverse elements.

`NameRepository` is a concrete collection that stores an array of names and provides a custom iterator `NameIterator` to access them. The iterator keeps track of the current position and moves through the array until all elements are accessed.

In the main method, the client gets an iterator from `NameRepository` and uses it to print each name in the collection. This demonstrates how the iterator pattern separates the way elements are accessed from the collection itself.

**Output:**  
Alice  
Bob  
Charlie
