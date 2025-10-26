package Oops.oops_theory.Properties.Inheritence;

public class Box {
    double h;
    double w;
    double l;

    Box(){
        this.h = -1;
        this.w = -1;
        this.l = -1;
    }

    Box(double side){

       // super(); // it is not given error , but there is no parent class of box
        // then whom it is referring to -> to the object class , which is parent class of all
        this.w = side;
        this.h = side;
        this.l = side;


    }


    Box(double h , double w , double l){
        this.h  = h;
        this.w = w;
        this.l  = l;
    }

    Box(Box old){
        this.h = old.h;
        this.w = old.w;
        this.l = old.l;
    }


}
