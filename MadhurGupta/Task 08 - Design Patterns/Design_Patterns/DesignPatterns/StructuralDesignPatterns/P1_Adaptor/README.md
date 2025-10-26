# Problem statement
> Let's consider a scenario where we have an existing system that uses a LegacyPrinter class with a method named printDocument() which we want to adapt into a new system that expects a Printer interface with a method named print(). 

# Adapter Design Pattern

This code demonstrates the Adapter design pattern using a printer example. The Adapter pattern allows an existing class with an incompatible interface to work with a new interface expected by the client.

## Overview

- `Printer` is the target interface that the client expects. It has a `print()` method.
- `LegacyPrinter` is an existing class (adaptee) with a method `printDocument()` which does the printing.
- `PrinterAdapter` adapts `LegacyPrinter` to the `Printer` interface by implementing `print()` and internally calling `printDocument()`.

## How it Works

- The client expects an object that implements the `Printer` interface.
- Instead of changing the `LegacyPrinter`, we create an adapter that bridges the gap.
- In the main method, we create a `PrinterAdapter` and pass it to the client code.
- The client can now use the legacy printer through the adapter without knowing about the implementation details.

## Output

When you run the program, it prints:
Legacy Printer is printing a document.

This shows that the adapter successfully allows the client to use the legacy printer.
