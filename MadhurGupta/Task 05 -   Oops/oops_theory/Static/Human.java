package Oops.oops_theory.Static;

public class Human {

    int age;
    String name;
    int salary;
    boolean married;
// if some property that is common to every object that ve make :- example -> polulation
//    use static to make it common
    static long population;


    static void message(){
        System.out.println("message");
       // System.out.println(this.age); // can not use this in static bcz this refers to an obj
                                      // and this is a static fun
    }

public Human(int age , String name , int salary , boolean married){

    this.age = age;
    this.name = name;
    this.salary = salary;
    this.married = married;
   // this.population +=1; // we can access this via this but for static memeber use Class
    Human.population +=1;  //  itself by convention

}

}

//key points:-
//static variable does not depend on object
// they can be accessed without creating any object
// they belong to class , do not belong to objects
// resolve during compile time , bcz objects are resolve at run time and they are not belongs
//objects

// Why main function is static ?
// because main is the function that runs the program without it program cant be run
// if we did not declare it static it has to create object to run
//But we don't do that , that's why we declare it static so it can run without creating an oj