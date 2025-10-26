import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JavaTasks1 {

    // Factorial (Recursion & Iteration)
    public static class Factorial {
        public static long factorialRecursive(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            return n * factorialRecursive(n - 1);
        }

        public static long factorialIterative(int n) {
            long result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }

    @Test
    public void testFactorialRecursive() {
        assertEquals(120, Factorial.factorialRecursive(5));
        assertEquals(1, Factorial.factorialRecursive(0));
        assertEquals(1, Factorial.factorialRecursive(1));
        assertEquals(2, Factorial.factorialRecursive(2));
        assertEquals(720, Factorial.factorialRecursive(6));
    }

    @Test
    public void testFactorialOf20() {
        long expected = 2432902008176640000L;
        assertEquals(expected, Factorial.factorialIterative(20));
        assertEquals(expected, Factorial.factorialRecursive(20));
    }

    // 2. Filtering even numbers with lambda
    public static class EvenFilter {
        public static List<Integer> filterEvenNumbers(List<Integer> numbers) {
            return numbers.stream()
                    .filter(n -> n % 2 == 0)
                    .collect(Collectors.toList());
        }
    }

    @Nested
    class EvenFilterTest {
        @Test
        public void EvenTestMixedList() {
            List<Integer> input = Arrays.asList(1, 2, 3, 4);
            List<Integer> expected = Arrays.asList(2, 4);
            List<Integer> result = EvenFilter.filterEvenNumbers(input);
            assertEquals(expected, result);
        }

        @Test
        public void EvenTestOddList() {
            List<Integer> input = Arrays.asList(1, 3, 5, 7);
            List<Integer> expected = Arrays.asList();
            List<Integer> result = EvenFilter.filterEvenNumbers(input);
            assertEquals(expected, result);
        }

        @Test
        public void EvenTestEmptyList() {
            List<Integer> input = Arrays.asList();
            List<Integer> expected = Arrays.asList();
            List<Integer> result = EvenFilter.filterEvenNumbers(input);
            assertEquals(expected, result);
        }
    }

    // 3. Finding squares of even numbers using normal integers
    public static class EvenSquares {
        public static List<Integer> findSquaresOfEvenNumbers(List<Integer> numbers) {
            return numbers.stream()
                    .filter(num -> num % 2 == 0)
                    .map(num -> num * num)       
                    .filter(EvenSquares::isPerfectSquare)
                    .collect(Collectors.toList());
        }

        private static boolean isPerfectSquare(int num) {
            if (num < 0) return false;
            int sqrt = (int) Math.sqrt(num);
            return sqrt * sqrt == num;
        }
    }

    @Nested
    class EvenSquaresTest {

        @Test
        public void EvenSquareNegative() {
            List<Integer> input = Arrays.asList(1, -2, -4, 8);

            List<Integer> expected = Arrays.asList(4 , 16 , 64);

            List<Integer> result = EvenSquares.findSquaresOfEvenNumbers(input);
            assertEquals(expected, result);
        }

        @Test
        public void EvenSquareTest() {
            List<Integer> input = Arrays.asList(2, 3, 10, 12, 15, 20);

            List<Integer> expected = Arrays.asList( 4 , 100 , 144 , 400);


            List<Integer> result = EvenSquares.findSquaresOfEvenNumbers(input);
            assertEquals(expected, result);
        }
    }

    public static void main(String[] args) {
        System.out.println("Factorial of 5 (Recursive): " + Factorial.factorialRecursive(5));
        System.out.println("Factorial of 5 (Iterative): " + Factorial.factorialIterative(5));

        List<Integer> numbers = List.of(1, 2, 3, 4);
        System.out.println("Even numbers: " + EvenFilter.filterEvenNumbers(numbers));

        System.out.println("Squares of even numbers: " + EvenSquares.findSquaresOfEvenNumbers(numbers));
    }
}
