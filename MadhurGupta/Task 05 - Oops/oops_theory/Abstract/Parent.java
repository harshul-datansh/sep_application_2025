package Oops.oops_theory.Abstract;
//final public abstract class parent{}//you cant have abstract final class because then it
// can't inherit by child classes then abstract methods can't be overridden
public abstract class Parent {
    int age;
    abstract void Career();
    abstract void Partner();
    // parent class contain methods which don't have 'any' body and child class have to override
    //them to call this is called abstract methods


    //class which contain one or more than one abstract methods are called abstract class
    //so class has also declare abstract

   public Parent(int age){
        this.age = age;
    }

    static void Hello(){
        System.out.println("hello"); // you can create static methods in abs classes
                                     // but you can not create abs static methods
                                      // reason -> static don't override
                                       //but abs methods must have to

                                    // same reason can't create abs constructor

    }

    void normal(){
        System.out.println("normal method "); // yes can create
    }
}
