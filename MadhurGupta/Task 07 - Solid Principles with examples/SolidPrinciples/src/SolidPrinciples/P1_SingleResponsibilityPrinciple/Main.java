package SolidPrinciples.P1_SingleResponsibilityPrinciple;

public class Main {
    public static void main(String[] args) {
        User user = new User("Alice", "alice@example.com");

        UserPrinter printer = new UserPrinter();
        printer.print(user);
    }

}
