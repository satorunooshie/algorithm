package java;

import java.util.Scanner;

/**
 * The type Euclid gcd without recur.
 */
public class EuclidGCDWithoutRecur {
    /**
     * Gcd int.
     *
     * @param x the x
     * @param y the y
     * @return the int
     */
    static int gcd(int x, int y) {
        while (y != 0) {
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("二つの整数の最大公約数を求める:");
        System.out.print("整数を入力せよ:");
        int x = stdIn.nextInt();
        System.out.print("整数を入力せよ:");
        int y = stdIn.nextInt();
        System.out.printf("最大公約数は%d\n", gcd(x, y));
    }
}
