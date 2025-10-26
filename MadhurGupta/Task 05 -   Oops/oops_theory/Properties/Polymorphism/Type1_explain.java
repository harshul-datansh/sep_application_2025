package Oops.oops_theory.Properties.Polymorphism;

public class Type1_explain {

    int num(int a , int b){
        return a + b;
    }

    int num(int a , int b , int c){
        return a + b + c;

    }

    public static void main(String[] args) {
        Type1_explain number = new Type1_explain();
        System.out.println(number.num(5 , 3)); // it will call first one
        System.out.println(number.num(5 , 6 , 6)); // second one
    }
}
