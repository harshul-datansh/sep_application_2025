# SOLID Principles – Easy Guide

This is a quick and simple explanation of the **SOLID principles** in Object-Oriented Programming (OOP).  
Think of these as **rules for writing clean, maintainable, and bug-free code**.

---

## 1️⃣ **S — Single Responsibility Principle (SRP)**

**Definition:**  
A class should have **only one responsibility**.  
In simple words: **Do one thing, and do it well.**

**Example:**  
Imagine a class that is a chef, gardener, driver, and painter all at once.  
If you change one thing (say gardening logic), it may break cooking logic.

**Why it matters:**
- Makes code easier to maintain
- Fewer bugs when making changes
- Keeps each class focused

---

## 2️⃣ **O — Open/Closed Principle (OCP)**

**Definition:**  
A class should be **open for extension, but closed for modification**.

**Meaning:**  
You should be able to **add new functionality** to a class **without modifying existing code**.

**Example:**  
Instead of editing a class to add a new feature, extend it with inheritance or composition.  
This avoids breaking existing code that already depends on that class.

---

## 3️⃣ **L — Liskov Substitution Principle (LSP)**

**Definition:**
> “If `S` is a subtype of `T`, then objects of type `T` should be replaceable with objects of type `S` without breaking the program.”

**Meaning:**  
Child classes should be able to do **everything** the parent class can do (and maybe more),  
without surprising the code that uses them.

**Example:**  
If the parent class has a method `bringCoffee()`,  
the child class should not say *"I can’t bring coffee, I’ll bring water."*  
That would break expectations and violate LSP.

---

## 4️⃣ **I — Interface Segregation Principle (ISP)**

**Definition:**  
A class **should not be forced to implement methods it does not use**.

**Meaning:**  
Split big interfaces into smaller ones.  
Each class should implement only what it actually needs.

**Benefits:**
- Avoids unnecessary methods
- Reduces chances of unexpected bugs

---

## 5️⃣ **D — Dependency Inversion Principle (DIP)**

**Definition:**  
High-level classes should depend on **abstractions (interfaces)**, not on concrete implementations.

**Example:**  
Instead of writing separate code for TV, Fan, and AC remotes —  
create a `RemoteControl` interface and let all devices implement it.

**Why it matters:**
- Makes code flexible
- Easy to switch implementations without rewriting everything

---

## 🎯 **Goal of SOLID**

Following SOLID principles helps you:  
✅ Write **cleaner, modular code**  
✅ **Avoid bugs** when adding new features  
✅ Make your code **easier to test and maintain**

---

> 💡 **Tip:** If you want to make this even better, add small **Java code snippets** for each principle  
> (e.g., a `RemoteControl` interface for DIP, a `Shape` example for OCP).  
> This will make the README more practical for learners or GitHub viewers.
