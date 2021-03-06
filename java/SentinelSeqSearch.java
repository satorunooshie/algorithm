package java;

import java.util.Scanner;

/**
 * The type Sentinel seq search.
 */
class SentinelSeqSearch {
    /**
     * Sentinel search int.
     *
     * @param a   the a
     * @param n   the n
     * @param key the key
     * @return the int
     */
    static int sentinelSearch(int[] a, int n, int key) {
        int i = 0;
        a[n] = key;
        while (a[i] != key) {
            i++;
        }
        /* for version
        for (i = 0; a[i] != key; i++)
            ;
         */
        return i == n ? -1 : i;
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