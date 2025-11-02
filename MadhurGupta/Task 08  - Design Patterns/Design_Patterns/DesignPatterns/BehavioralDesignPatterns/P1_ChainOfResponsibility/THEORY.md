# Chain of Responsibility Design Pattern

---
The Chain of Responsibility design pattern is a `behavioral design pattern` that allows an object to pass a request along a chain of handlers. Each handler in the chain decides either to process the request or to pass it along the chain to the next handler.

`It passes without knowing who goes to handle it`

### Components :
1. Handler Interface
2. Concrete Handler
3. Client

#### Pros 
1. Decouples sender and receiver of a request.

2. Simplifies object responsibility assignment dynamically.

3. Easy to add or remove handlers without changing client code.

4. Promotes flexibility in processing requests.
#### Cons
1. No guarantee a request will be handled.

2. Can lead to long chains and performance overhead.

3. Debugging can be difficult due to dynamic request flow.

4. Handler order is crucial; wrong order may cause incorrect handling.