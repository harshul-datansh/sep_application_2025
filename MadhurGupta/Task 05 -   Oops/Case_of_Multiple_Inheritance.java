package Oops;


class ParentA {
    void show() {
        System.out.println("ParentA: show()");
    }
}


class ParentB {
    void show() {
        System.out.println("ParentB: show()");
    }
}

//  This would cause compile time error if allowed
// class Child extends ParentA, ParentB { }  //  NOT ALLOWED in Java

// Multiple Inheritance via INTERFACES
interface InterfaceA {
    void display();  // abstract method

    default void greet() {  //default method
        System.out.println("InterfaceA: Hello!");
    }
}

interface InterfaceB {
    void display();

    default void greet() {
        System.out.println("InterfaceB: Hello!");
    }
}


class ChildClass implements InterfaceA, InterfaceB {
    @Override
    public void display() {
        System.out.println("ChildClass: display() implementing both interfaces.");
    }


    @Override
    public void greet() {
        System.out.println("ChildClass: Resolving default method conflict.");
        InterfaceA.super.greet(); // Call InterfaceA's greet()
        InterfaceB.super.greet(); // Call InterfaceB's greet()
    }
}

// combining class and interfaces
class Parent {
    void message() {
        System.out.println("Parent: message()");
    }
}

interface InterfaceC {
    void interfaceMethod();
}

class ChildMixed extends Parent implements InterfaceC {
    @Override
    public void interfaceMethod() {
        System.out.println("ChildMixed: interfaceMethod() implementation");
    }
}


public class Case_of_Multiple_Inheritance {
    public static void main(String[] args) {
        System.out.println("Test Case 1: Multiple Class Inheritance");
        System.out.println("Cannot extend two classes simultaneously — compile-time error.");

        System.out.println("\n Test Case 2: Multiple Interface Inheritance ");
        ChildClass obj = new ChildClass();
        obj.display(); // Method from both interfaces
        obj.greet();   // Resolved conflict between InterfaceA and InterfaceB

        System.out.println("\n Test Case 3: Class + Interface Combination ");
        ChildMixed mixedObj = new ChildMixed();
        mixedObj.message();         // Inherited from Parent
        mixedObj.interfaceMethod(); // Implemented from InterfaceC
    }
}
