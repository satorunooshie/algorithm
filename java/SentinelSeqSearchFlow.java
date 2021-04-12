package java;

import java.util.Scanner;

/**
 * The type Sentinel seq search flow.
 */
public class SentinelSeqSearchFlow {
    /**
     * Sentinel search int.
     *
     * @param array the array
     * @param n     the n
     * @param key   the key
     * @return the int
     */
    static int sentinelSearch(int[] array, int n, int key) {
        System.out.print("   |");
        for (int k = 0; k < n; k++) {
            System.out.printf("%4d", k);
        }
        System.out.println();
        System.out.print("---+");
        for (int k = 0; k < 4 * n + 2; k++) {
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("   |");
            System.out.printf(String.format("%%%ds*\n", (i * 4) + 3), "");
            System.out.printf("%3d|", i);
            for (int k = 0; k < n; k++) {
                System.out.printf("%4d", array[k]);
            }
            System.out.println("\n   |");
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("要素数は:");
        int num = stdIn.nextInt();
        int[] x = new int[num + 1];
        for (int i = 0; i < num; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        System.out.print("探す値:");
        int key = stdIn.nextInt();
        //番兵法で使う配列最後のコマ
        x[num] = key;
        int index = sentinelSearch(x, num, key);
        if (index == -1)
            System.out.println("その値の要素は存在しません");
        else
            System.out.println("その値はx[" + index + "]にあります");
    }
}
