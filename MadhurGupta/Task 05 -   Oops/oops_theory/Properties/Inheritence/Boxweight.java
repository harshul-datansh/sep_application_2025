package Oops.oops_theory.Properties.Inheritence;

public class Boxweight extends Box{

    double weight;

    Boxweight(){
        this.weight = 100;
    }

    Boxweight(Boxweight other){
        super(other);
        this.weight =  other.weight;
    }

    Boxweight(double side , double weight){
        super(side);
        this.weight = weight;
    }
    Boxweight(double h , double w , double l , double weight){
      // now using extends keyword ,  I can use the base class in child class
//        this.h = h;
//        this.w = w;
//        this.l = l;
        super(h , w , l); // what is this ? call the parent class constructor
        // use to initialize values present in parent class constructor

        // super keyword is referencing to the class that is exactly above

        //one more use case of super keyword

        System.out.println(super.h); // it accesses directly above class (parent) variable
        // Although you can use this but sometimes variables names match in parent and child
        this.weight = weight;

    }
}
