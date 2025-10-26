package Oops.oops_theory.Access_control;

public class Main {
    public static void main(String[] args) {
        A obj = new A(10 , "Madhur");
            // need ton do few things
        //access the data members
        //modifying the data members

        //obj.num // giving error because we cant access it (private)

        System.out.println(obj.getNum()); // from this we can access it

        obj.setNum(90);  //modifying
        System.out.println(obj.getNum()); //printing
    }

}
