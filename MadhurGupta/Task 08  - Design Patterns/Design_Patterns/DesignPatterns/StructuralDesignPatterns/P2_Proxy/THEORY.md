# Proxy design pattern

---

## Overview
Proxy Design Pattern is a structural design pattern where a proxy object acts as a placeholder to control access to the real object. The client communicates with the proxy, which forwards requests to the real object. The proxy can also provide extra functionality such as access control, lazy initialization, logging, and caching.
## Components
>Subject , Real Subject , Proxy

### Why do we need Proxy Design Pattern?
1. We need the Proxy Design Pattern to control and manage access to objects.
2. It acts as a placeholder or intermediary, providing additional functionalities like lazy loading, security checks, 
   logging, or remote access without changing the real object’s code.
3. This helps optimize performance, enhance security, and maintain flexibility in system design.
### When not to use Proxy Design Pattern?
1. Overhead for Simple Operations: Avoid using a proxy for simple objects or operations that don't involve 
resource-intensive tasks. Introducing a proxy might add unnecessary complexity in such cases.
2. Unnecessary Abstraction: If your application doesn't require lazy loading, access control, or additional 
   functionalities provided by proxies, introducing proxies may lead to unnecessary abstraction and code complexity.
3. Performance Impact: If the introduction of a proxy negatively impacts performance rather than improving it, 
   especially in cases where objects are lightweight and creation is not a significant overhead.
4. When Access Control Isn't Needed: If there are no access control requirements and the client code can directly 
   interact with the real object without any restrictions.
5. When Eager Loading is Acceptable: If eager loading of objects is acceptable and doesn't affect the performance of 
   the system, introducing a proxy for lazy loading might be unnecessary.