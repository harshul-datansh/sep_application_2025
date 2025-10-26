package Oops.oops_theory.Abstract;

public class Main {

    public static void main(String[] args) {
        Son son = new Son(30);
        son.Career();

        Daughter dau = new Daughter(20);
        dau.Partner();

       // Parent mon = new Parent(); // error u cant create obj of abstract parent class
        // you have to override them

        Parent.Hello();// static called by class name : previous lectures


    }
}
