package Oops.oops_theory.Static;



public class Main {
//  we don't need to import human class here bcz it is in the same package called static
    public static void main(String[] args) {
        Human madhur = new Human(12 , "madhur" , 10000 , false);
        Human rahul = new Human(19 , "rahul" , 30000 , true);
        Human arpit = new Human(50 , "arpit" , 10000 , true);


        System.out.println(madhur.population); // can be access via reference variable
        System.out.println(Human.population);  // but conventionally use class name


        //now if I want to access not static inside static context  , it will give me error
        //greeting(); // generate error

        fun(); //this works fine


        Main funn = new Main();
        funn.fun2();
        funn.greeting(); // both can be call now bcz object is made


    }

    // let's make a non-static func:-
    //its belong to instances(objects)
    void greeting(){
        System.out.println("hello");

      //  fun();  //this is correct  , you can use static inside not static
    }

    // let's make a static func:-
    // it is  not belong to instances
    static void fun(){
        System.out.println("fun");

        //greeting(); // Also can't do this here , because you are using non-static fun inside
                   // static fun -> error
       // you can not access non-static stuff without referencing their instances inside
        // static context


        // Hence , Here I am referencing it
        Main obj = new Main();

        obj.greeting();

    }


    void fun2(){
        greeting(); // here it correct non-static inside non-static
    }



}
