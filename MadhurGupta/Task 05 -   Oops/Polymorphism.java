package Oops;

// Outer Class (Just to hold main method)
public class Polymorphism {

    // Parent Class
    static class Parent {
        // Overloaded method (compile-time polymorphism)
        public void func() {
            System.out.println("Parent.func()");
        }

        // Overloaded method (same name, different parameter)
        public void func(int a) {
            System.out.println("Parent.func(int): " + a);
        }
    }

    // Child Class (inherits Parent)
    static class Child extends Parent {
        // Overrides Parent.func(int) (runtime polymorphism)
        @Override
        public void func(int a) {
            System.out.println("Child.func(int): " + a);
        }
    }

    public static void main(String[] args) {
        // Creating objects
        Parent parent = new Parent();
        Child child = new Child();

        // Upcasting (polymorphic reference)
        Parent polymorphicObj = new Child();

        System.out.println("=== Method Overloading (Compile-Time Polymorphism) ===");
        parent.func();      // Calls Parent.func()
        parent.func(10);    // Calls Parent.func(int)

        System.out.println("\n=== Method Overriding (Runtime Polymorphism) ===");
        child.func(20);     // Calls Child.func(int)

        System.out.println("\n=== Polymorphism in Action (Dynamic Dispatch) ===");
        polymorphicObj.func(30);  // Calls Child.func(int) (runtime binding)
    }
}
