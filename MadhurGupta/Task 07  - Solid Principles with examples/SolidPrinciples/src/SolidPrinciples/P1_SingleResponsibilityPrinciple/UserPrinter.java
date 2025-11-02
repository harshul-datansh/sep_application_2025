package SolidPrinciples.P1_SingleResponsibilityPrinciple;

public class UserPrinter {
    public void print(User user) {
        System.out.println("User Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }
}
