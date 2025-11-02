# Chain of Responsibility Pattern Example

This code demonstrates the Chain of Responsibility design pattern. In this pattern, a request is passed along a chain of handlers until one of the handlers can process it.

The code has an abstract `Handler` class which defines a method `handle` to process requests and a reference `next` to the next handler in the chain. Two concrete handlers are created:
- `HelpHandler` handles requests with the message "help".
- `ExitHandler` handles requests with the message "exit".

If a handler cannot process a request, it passes the request to the next handler in the chain.

In the `main` method, `HelpHandler` is linked to `ExitHandler`, creating a chain. When requests like "help" or "exit" are sent, they are handled by the appropriate handler in the chain. This demonstrates how requests can flow through multiple objects until they are handled, keeping the sender and receiver decoupled.
