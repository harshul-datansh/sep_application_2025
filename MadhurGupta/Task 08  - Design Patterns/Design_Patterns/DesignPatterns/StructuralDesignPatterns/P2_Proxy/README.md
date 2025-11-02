# Proxy Design Pattern

This code demonstrates the Proxy design pattern using an image example. The Proxy pattern provides a placeholder for another object to control access to it, often to delay or manage expensive operations.

## Overview

- `Image` is the abstract subject that defines the `display()` method.
- `RealImage` is the real object that loads and displays the image from disk.
- `ProxyImage` is the proxy that controls access to `RealImage`. It loads the image only when `display()` is called for the first time and caches it for future use.

## How it Works

- In the main method, we create a `ProxyImage` object instead of `RealImage`.
- The image is **not loaded immediately**.
- When `display()` is called the first time, the proxy creates the real image and loads it from disk.
- Subsequent calls to `display()` use the already loaded image, avoiding repeated loading.

## Output

When you run the program, it prints:
Loading image: example.jpg  
Displaying image: example.jpg  
Displaying image: example.jpg

This shows that the image is loaded only once and then reused through the proxy.
