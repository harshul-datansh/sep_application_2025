public class Solid_principles {

    //*******************1. Single responsibility : ************************

    // A class should have single responsibility
    // for example :-

    // A class have distributed responsibility

    // A class is not like this that
    // I am a chef , gardener , driver and painter

    //Reason :-
    //if a Class has many responsibilities, it increases the possibility
    // of bugs because making changes to one of its responsibilities,
    // could affect the other ones without you knowing.

   //example :
   // lets say a parent class have method bring coffee ,
   // it expected that if child class ask of cappuccino it should do that
   // if child class say i cant bring cappuccino i can bring water then lsp violates

   //Goal :
   // Make things consistent in the code and ensures that if a parent having some prop and methods , child class also
   // behaves same although child class can their own method

    //Goal :-
    // This principle aims to separate behaviours so that if bugs arise
    //  as a result of your change, it won’t affect other unrelated behaviours.

    //********************* 2. Open-Closed ****************************8

    //A class is ready for extension but not for modification

    //for example :-

    // class employee{
    //  paint()
//}
    //class employee{
    // cut()
    //}

    //extend the class behaviour that a class can cut and paint also

    //If you want the Class to perform more functions,
   // the ideal approach is to add to the functions that already exist NOT change them.

    //Goal :-
    //This principle aims to extend a Class’s behaviour without changing
    // the existing behaviour of that Class.
    // This is to avoid causing bugs wherever the Class is being used.




  // ************************ 3. L — Liskov Substitution ****************************

   //“If S is a subtype of T, then objects of type T should be replaceable with objects of type S
   // without altering the correctness of the program.”




    //*********************** 4. I — Interface Segregation *************************

    // When a Class is required to perform actions that are not useful,
    // it is wasteful and may produce unexpected bugs if the Class does not have the ability to perform those actions.
    //
    //A Class should perform only actions that are needed to fulfil its role.
    // Any other action should be removed completely or moved somewhere else if it might be used by another Class in the future.
    //
    //Goal

    //This principle aims at splitting a set of actions into smaller sets so that a Class
    // executes ONLY the set of actions it requires.


   //************************ 5. D — Dependency Inversion ***********************************
   // high level class is remoteControl
   //A high-level class should depend on an interface,
   //not directly on the concrete class (like TV or Fan).
   // no need to create different classes for every distinct objects , create one interface of remotecontrol
   //Goal :-
   //This principle aims at reducing the dependency of a high-level Class on the low-level Class by
   // introducing an interface.


   }
