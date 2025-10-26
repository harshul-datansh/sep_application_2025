package StudentManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Student {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    String name;
    int age;
    String course_name;
    String email;

    // Constructor with email as String
    public Student(String name, int age, String course_name, String email) {
        this.name = name;
        this.age = age;
        this.course_name = course_name;
        this.email = email;
    }

    public void display() {
        System.out.println("Records of all students");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course_name: " + course_name);
        System.out.println("Email: " + email);
    }

    public static boolean isValidAge(int age) {
        return age >= 5 && age <= 100;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of students");
        int number = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < number; i++) {
            System.out.println("Enter Details of student " + (i + 1));

            System.out.println("Enter name of student");
            String name = sc.nextLine();

            int age;
            while (true) {
                System.out.println("Enter age of student");
                age = sc.nextInt();
                sc.nextLine();
                if (isValidAge(age)) {
                    break;
                } else {
                    System.out.println("ERROR: Invalid age. Please enter age between 5 and 100.");
                }
            }

            System.out.println("Enter course_name of student");
            String course_name = sc.nextLine();

            String email;
            while (true) {
                System.out.println("Enter email of student");
                email = sc.nextLine();
                if (isValidEmail(email)) {
                    break;
                } else {
                    System.out.println("ERROR: Invalid email format.");
                }
            }

            Student s = new Student(name, age, course_name, email);
            students.add(s);
        }

        for (Student s : students) {
            s.display();
            System.out.println();
        }
        sc.close();
    }
}
