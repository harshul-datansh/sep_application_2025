package SolidPrinciples.P3_LiskovSubstitutionPrinciple;

public class Main {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird ostrich = new Ostrich();

        sparrow.eat();
        ((FlyingBird) sparrow).fly(); // OK, sparrow can fly

        ostrich.eat();
        // ostrich.fly(); // Not possible, no fly method in Bird interface
    }
}
