package Oops.oops_theory.Access_control;

public class Object_class {

    // Top most class in the hierarchy , root of all classes

//    every class extends Object class internally
int num;

Object_class(int num){
    this.num = num;
}


    @Override
    public int hashCode() {
        return super.hashCode();  // represent unique random integer value for obj holds
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // equals method actually check the content inside it
                                  // while == operator check the both var point to the same
                                  //obj
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }


    public static void main(String[] args) {
        Object_class obj = new Object_class(10);
        Object_class obj1 = new Object_class((10));
        System.out.println(obj.hashCode()); // giving some random integer value
        System.out.println(obj1.hashCode());// giving different random integer value
    }
}
