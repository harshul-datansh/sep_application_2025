package SolidPrinciples.P3_LiskovSubstitutionPrinciple;

interface Bird {
    void eat();
}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void eat() {
        System.out.println("Sparrow is eating.");
    }
    public void fly() {
        System.out.println("Sparrow is flying.");
    }
}

class Ostrich implements Bird {
    public void eat() {
        System.out.println("Ostrich is eating.");
    }
    // No fly method here, because Ostrich can't fly
}

