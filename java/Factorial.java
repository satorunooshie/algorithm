package java;

import java.util.Scanner;

/**
 * The type Factorial.
 */
class Factorial {
    /**
     * Factorial int.
     *
     * @param n the n
     * @return the int
     */
    static int factorial(int n) {
        if (n > 0)
            return n * factorial(n - 1);
        else
            return 1;
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
        System.out.println(x + "の階乗は" + factorial(x));
    }
}