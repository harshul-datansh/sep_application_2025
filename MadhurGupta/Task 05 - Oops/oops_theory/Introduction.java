package Oops.oops_theory;

public class Introduction {

    public static void main(String[] args) {
        //    store 5 rollno of a student

        int[] number = new int[5];

//    store 5 names of a student

        String[] names = new String[5];

//     store data of student contain rollno , marks and name

//        int[] rollno = new int[5];
//        String[] name = new String[5];
//        float[] marks = new float[5];

        // but now I want to store these in a single data type : there class comes :-
        Student[] students = new Student[5];


//  class is the Template for Objects  -> logical construct
//    Objects are instance of class , instance means physical thing of class -> physical reality
//    occupies space in memory
//  objects store in heap memory and reference variable store in stack memory

//   *************************** How to create Objects  *************************
    // Declaring the object :- At this point it is null

    Student student1;

    //   initalize :-


         student1 = new Student();

//         Making one more object :-
        Student student2 = new Student(50 , "RAHUL" , 90);
        Student student3 = new Student(student1);



//    ****** How to access Instance variable  *************
    //using dot operator
       // System.out.println(student1.rollno); // by default 0
        //System.out.println(student1.name);  // by default null
       // System.out.println(student1.marks);  // by default 0.0


//        ************* manipulating the  objects********************

//        student1.rollno  = 13;
//        student1.name  = "Madhur";
//        student1.marks = 100;

        System.out.println(student1.rollno);
        System.out.println(student1.name);
        System.out.println(student1.marks);

//        calling the  greeting function :-
             student1.greeting();

        System.out.println();
        System.out.println(student2.name);
        System.out.println(student2.rollno);
        System.out.println(student2.marks);

             student2.greeting();

        System.out.println(student3.name);


        //one more concept new memory allocation :-

        Student one = new Student();
        Student two = one;

        one.name = "something something";
        System.out.println(two.name); //give me same output

    }






}

class Student{
    int rollno ;
    String name;
    float marks;



//      Making a function to understand this keyword :-
    void greeting(){
        System.out.println("hello my name is " + this.name);
    }
    // Making a constructor :-
    Student() {
//        student1.rollno = 13;
                          // I want when i write something like Student student1 = new Student()
                              //  automatically this student1.rollno = 13
                              // but what if i want to do this for another object student2
                              //  I have to use this that replace this with the paticualar
                              // object constructor called

//        this.rollno = 13;
//        this.name = "MADHUR";
//        this.marks = 100.0f;

//        calling a constructor from another constructor

        this(1000 , "consincons" , 100);
    }

    Student(int rollno , String name , float marks){
        this.rollno = rollno;
        this.name = name;
        this.marks = marks;
    }

    Student(Student other){
        this.rollno = other.rollno;
        this.name = other.name;
        this.marks = other.marks;

    }
}