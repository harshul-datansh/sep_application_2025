package Oops.oops_theory.Access_control;

public class A {
    private int num;  // it can only access in this file
    //String name;  // if no access modifier mention , then default one
                 // within same package can be accessible , but in other package can't

   protected String name;  // then not access only in different package & no subclass
                          // check both
    //one more point of protected is that in different package only subclass can access it
    // not the class itself
    int[] arr;

    public int getNum() {
        return num; // using getter and setter we can access and change num value in the other
                     // file.
    }

    public void setNum(int num) {
        this.num = num;
    }

    public A(int num , String name){
        this.num = num;
        this.name = name;
        this.arr= new int[num];

    }
}
