package SolidPrinciples.P4_InterfaceSegregationPrinciple;

public class Main {
    public static void main(String[] args) {
        Workable human = new HumanWorker();
        Workable robot = new RobotWorker();

        human.work();
        robot.work();

        Eatable eater = new HumanWorker();
        eater.eat();
        // Robot is not forced to eat 👍
    }
}

