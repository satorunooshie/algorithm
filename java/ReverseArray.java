package java;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The type Reverse array.
 */
class ReverseArray {
    /**
     * Swap.
     *
     * @param a    the a
     * @param idx1 the idx 1
     * @param idx2 the idx 2
     */
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    /**
     * Reverse.
     *
     * @param a the a
     */
    static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++)
            swap(a, i, a.length - i - 1);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("要素数は");
        int num = stdIn.nextInt();
        int[] x = new int[num];
        for (int i = 0; i < num; i++) {
            x[i] = stdIn.nextInt();
        }
        reverse(x);
        System.out.println("並び順を反転しました");
        System.out.println(Arrays.toString(x));
    }
}