package Oops.oops_theory.Properties.Polymorphism;

public class Main {

    public static void main(String[] args) {
        Shapes shape = new Shapes();
        Circle circle = new Circle();
        Square square = new Square();
// same method name , body is different , this is calles polymorphism
        // many ways of representing same thing
        shape.area();
        circle.area();
        square.area();

//    ********Meaning of Overriding***********

        Shapes circle1 = new Circle();
        circle1.area(); // Although circle1 is depended on Shapes , its have to call shapes area
                       // method but its only call circle are method. Why? overriding
     // Mainly , if this thing is given:-

//        Parent reference_var = new Child();

     // Here , which method have to call is depend on child()object
     // This is known as Upcasting


        // How java determines this using {DYNAMIC METHOD DISPATCH}
//        check which version of override method to be call
    }
}
