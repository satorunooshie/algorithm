package java;

import java.util.Scanner;

/**
 * The type Pyramid.
 */
class Pyramid {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("n段のピラミッドを表示");
        int n;
        do {
            System.out.print("n:");
            n = stdIn.nextInt();
        } while (n <= 0);
        pyramidOfStars(n);
        pyramidOfNumbers(n);
    }

    /**
     * 星でピラミッドを表現
     *
     * @param n the n
     */
    static void pyramidOfStars(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (i - 1) * 2 + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 数字でピラミッドを表現
     *
     * @param n the n
     */
    static void pyramidOfNumbers(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (i - 1) * 2 + 1; j++) {
                System.out.print(i % 10);
            }
            System.out.println();
        }
    }
}