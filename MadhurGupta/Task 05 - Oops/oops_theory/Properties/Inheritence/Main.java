package Oops.oops_theory.Properties.Inheritence;

public class Main {

    public static void main(String[] args) {
        Box box = new Box();
        System.out.println(box.h + " " + box.w + " " + box.l);

        Box box1 = new Box(5);
        System.out.println(box1.h + " " + box1.w + " " + box1.l);

        Box box2 = new Box(2 , 3 , 6);
        System.out.println(box2.h + " " + box2.w + " " + box2.l);

        Box box3 = new Box(box);
        System.out.println(box3.h + " " + box3.w + " " + box3.l);

        // till now normal things going on

        Boxweight box5 = new Boxweight();
        System.out.println(box5.h); // it can access h variable of parent class



        Boxweight box7 = new Boxweight();
        System.out.println(box7.weight);


        Boxweight box6 = new Boxweight(5 , 6 , 7 , 78);
        System.out.println(box6.h + " " + box6.w + " " + box6.l + " " + box6.weight);


        // now do something funny:-

        Box box4 = new Boxweight(2 , 3 , 4 , 5);
        // here reference type is Box(parent) and object type is Boxweight(child)
       // System.out.println(box4.weight); // can't access bcz reference type decide which variable
                                         // can access not the object type

        System.out.println(box4.h); // this you can do

        // ************* IMPORTANT************

        //we know that there are mamy variable in parent and child classes
        // you are given access to the variables that are of reference type i.e Boxweight here
        //Hence , you should have access to weight
        //this also means , that the ones you are trying to access should be initialized
        //but there , when the object if of type parent class , how you call the constructor
        //like -> Box(h , w , l , weight) //no
        // this is why error
         // Boxweight box8 = new Box(2 , 3 , 4 );
       // System.out.println("box8" + box8.weight); // cant do this


        //******multilevel********

        Boxprice Box9 = new Boxprice(5 ,1 , 2 , 3 , 4);

        // ******Multiple inheritance ********

//        one class extends two  classes

//        if two parent classes contain same variable name , lets say n = 5 , n = 10
//                and child class one to access n , then which n has to come


        // that's why multiple inheritance does not support in java


        // if you want to do something like that interfaces comes in


        // ******* Hierarchical inheritance ********

//        one class inherit by many classes

//
//
// class boxweight extends box  , class boxprice extends box , class boxcolour extends box


        //********* Hybrid ******
        //combination of single and multiple inheritance does not support in java


    }

}
