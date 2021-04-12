package java;

import java.util.Scanner;

/**
 * The type Binary insertion sort.
 */
public class BinaryInsertionSort {
    /**
     * 単純挿入ソートは配列の要素数が多くなると
     * 要素の挿入に要する比較・代入のコストが無視できなくなる
     * 目的列はソート済であるため、挿入すべき位置は二分探索によって調べられる
     *
     * 但し、安定ではなくなる
     *
     * @param array the array
     * @param n     the n
     */
    static void binaryInsertionSort(int[] array, int n) {
        for (int i = 1; i < n; i++) {
            int key = array[i];
            // 探索範囲先頭の添字
            int pl = 0;
            // 探索範囲末尾の添字
            int pr = i - 1;
            // 挿入すべき位置の添字
            int pc, pd;

            do {
                pc = (pl + pr) / 2;
                if (array[pc] == key)
                    break;
                else if (array[pc] < key)
                    pl = pc + 1;
                else
                    pr = pc - 1;
            } while (pl <= pr);
            pd = (pl <= pr) ? pc + 1 : pr + 1;

            /*
            for (int j = i; j > pd; j--)
                array[j] = array[j - 1];
             */
            if (i - pd >= 0) System.arraycopy(array, pd, array, pd + 1, i - pd);
            array[pd] = key;
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("2分挿入ソート");

        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        binaryInsertionSort(x, nx);

        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}
