package Oops.oops_theory.Abstract;

public class Daughter extends Parent{
    @Override
    void Career() {
        System.out.println("i am coder");
    }

    @Override
    void Partner() {
        System.out.println("I love iron man");
    }

    Daughter(int age){
        super(age);
    }
}
