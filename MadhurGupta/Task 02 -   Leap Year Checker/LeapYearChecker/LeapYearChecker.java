import java.util.Scanner;
import java.util.logging.Logger;
//import java.util.logging.Level;

public class LeapYearChecker {
    private static final Logger logger = Logger.getLogger(LeapYearChecker.class.getName());

    public static boolean isLeapYear(int year) {
        boolean isleap =  (year % 4 == 0 && year % 100 != 0) || ( year % 400 == 0);
                if(isleap) {
                    logger.info("Input year is a leap year");
                }
                else {
                    logger.warning("Input year is not a leap year");
                }
                return isleap;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

int choice;
         do {
             System.out.println("--------LEAP YEAR CHECKER---------");
             System.out.println("1. Input total years you want to check");
             System.out.println("2. help");
             System.out.println("3. Exit the program");
             System.out.println("Enter your choice");
             try {
                 choice = sc.nextInt();
                 switch (choice) {
                     case 1:
                         System.out.println("Enter the number of years you want to check : ");

                         int n = sc.nextInt();
                         for (int i = 0; i < n; i++) {
                             try {
                                 System.out.println("Enter " + (i + 1) + " year : ");
                                 int year = sc.nextInt();
                                 System.out.println(isLeapYear(year));
                             } catch (Exception e) {
                                 System.out.println("Invalid year");
                                 sc.nextLine();
                                 i--;
                             }

                         }
                         break;
                     case 2:
                         System.out.println("hello");
                         break;
                     case 3:
                         System.out.println("Exit the program");
                         break;

                     default:
                         System.out.println("Invalid user input");

                 }
             }
         catch(Exception e){
             System.out.println("Invalid input! Please enter a number.");
             sc.nextLine(); // clear the input buffer
             choice = 0;     // reset to continue loop
            }
        } while (choice != 3);
         sc.close();
    }
}
