package java;

import java.util.Scanner;

/**
 * The type Factorial without recur.
 */
public class FactorialWithoutRecur {
    /**
     * Factorial int.
     *
     * @param n the n
     * @return the int
     */
    static int factorial(int n) {
        int fact = 1;
        while (n > 1)
            fact *= n--;
        return fact;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("整数を入力せよ:");
        int x = stdIn.nextInt();
        System.out.printf("%dの階乗は%dです\n", x, factorial(x));
    }
}
