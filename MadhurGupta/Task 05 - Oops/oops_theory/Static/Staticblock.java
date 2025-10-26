package Oops.oops_theory.Static;

public class Staticblock {
    static int a = 4;
    static int b;

    // static block only run once when class is loaded
    static {
        System.out.println("I am static block");
        b = a*5;
    }

    public static void main(String[] args) {
        Staticblock obj = new Staticblock();
        System.out.println(Staticblock.a + " " + Staticblock.b);

        Staticblock.b +=3;

        System.out.println(Staticblock.a + " " + Staticblock.b);

    //now when another obj is made , class is loaded but static block run only first
        // time when class is loaded

        Staticblock obj1 = new Staticblock();
        System.out.println(Staticblock.a + " " + Staticblock.b);

        System.out.println(obj1.a + " " + obj1.b); // it also give me same output
                                                   // but conventionally for static use class
                                                    // name


    }
}
