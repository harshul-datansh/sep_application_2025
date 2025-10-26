package Oops.oops_theory.Static;

public class InnerClasses {

    // inner class is doest not depend on obj of outer class
    static class Test{
               String name;

              public Test(String name){
                   this.name = name;
               }


    }


    public static void main(String[] args) {
        Test obj = new Test("Madhur"); // this give error bcz it want instance of outer
                                             //class
           Test obj1 = new Test("Rahul");
        //but if you make inner class static then its work fine


        System.out.println(obj.name);  //that's why we got individual obj answer
        System.out.println(obj1.name); // bcz of inner class does not depend on obj of outer
                                       // class


    }


}
