package java;

import java.util.Scanner;

/**
 * The type Recursive copy array.
 */
public class RecursiveCopyArray {
    /**
     * 配列2の要素を配列1の要素に逆順にコピーする
     *
     * @param array1 the array 1
     * @param array2 the array 2
     */
    static void recursiveCopy(int[] array1, int[] array2) {
        int num = Math.min(array1.length, array2.length);
        for (int i = 0; i < num; i++) {
            array1[i] = array2[array2.length - i - 1];
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("配列1の要素数は");
        int num_x = stdIn.nextInt();
        int[] x = new int[num_x];
        for (int i = 0; i < num_x; i++) {
            x[i] = stdIn.nextInt();
        }
        System.out.print("配列2の要素数は");
        int num_y = stdIn.nextInt();
        int[] y = new int[num_y];
        for (int i = 0; i < num_y; i++) {
            y[i] = stdIn.nextInt();
        }
        recursiveCopy(x, y);
        System.out.println("配列2の要素を配列1に逆順にコピーしました");
        for (int num : x) {
            System.out.println(num);
        }
    }
}