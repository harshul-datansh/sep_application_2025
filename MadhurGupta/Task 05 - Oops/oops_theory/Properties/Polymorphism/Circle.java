package Oops.oops_theory.Properties.Polymorphism;

public class Circle extends Shapes{
  // this will run when object of circle is created
    // Hence , it will override the method of parent class(Shape)
    @Override // this is annotation , use to show this method is overridden
    void area(){

        System.out.println("area of circle pie * r * r");
    }
}
