package java;

import java.util.Scanner;

/**
 * The type Gcd array.
 */
public class GCDArray {
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
     * Gcd array int.
     *
     * @param array the array
     * @param start the start
     * @param no    the no
     * @return the int
     */
    static int gcdArray(int[] array, int start, int no) {
        return switch (no) {
            case 1 -> array[start];
            case 2 -> gcd(array[start], array[start + 1]);
            default -> gcd(array[start], gcdArray(array, start + 1, no - 1));
        };
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("何個の整数の最大公約数を求めますか:");

        int num;
        do {
            num = stdIn.nextInt();
        } while (num <= 1);

        int[] x = new int[num];

        for (int i = 0; i < num; i++) {
            System.out.printf("x[%d]:", i);
            x[i] = stdIn.nextInt();
        }
        System.out.printf("最大公約数は%s\n", gcdArray(x, 0, num));
    }
}
