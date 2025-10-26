package Oops;

public class Inheritence {

    static class Box {
        int length;         // if i make it private , Although boxchild class extends this class but it cant
        //private int length;                    // access this
        int width;
        int height;

        public Box() {

        }

        public Box(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }


        static class Boxchild extends Box {

            double weight;

            Boxchild(int length, int width, int height, double weight) {
                super(length, width, height);// calling parent class constructor for initializing the values
                this.weight = weight;
            }

            Boxchild(double weight) {

                this.weight = weight;
            }
        }

        public static void main(String[] args) {
            Box box = new Box(1, 2, 3);
            System.out.println(box.height + box.width + box.length);

            Box b = new Boxchild(1, 2, 3, 4);
            System.out.println(b.length);

            Boxchild b1 = new Boxchild(1);
            System.out.println(b1.weight + " " + b1.height);

            // Boxchild box2 = new Box(1 ,2  , 3 , 4);
            // box2.height;

            Box box3 = new Boxchild(1, 2, 3, 4);
            System.out.println(box3.width);
            System.out.println(box3.height);
            System.out.println(box3.length);
            //System.out.println(box3.weight);


        }
    }
}
