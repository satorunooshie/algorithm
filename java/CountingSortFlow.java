package java;

import java.util.Scanner;

/**
 * The type Counting sort flow.
 */
public class CountingSortFlow {
    /**
     * Counting sort.
     *
     * @param array the array
     * @param n     the n
     * @param max   the max
     */
    static void countingSort(int[] array, int n, int max) {
        int[] freq = new int[max + 1];
        int[] tmp = new int[n];

        System.out.println("Step1: 度数分布表の作成");
        for (int i = 0; i < n; i++) {
            for (int k = 0; k <= max; k++)
                System.out.printf("%3d", freq[k]);
            System.out.println();
            freq[array[i]]++;
        }
        for (int k = 0; k <= max; k++)
            System.out.printf("%3d", freq[k]);
        System.out.println();

        System.out.println("Step2: 累積度数分布表の作成");
        for (int i = 1; i <= max; i++) {
            for (int k = 0; k <= max; k++)
                System.out.printf("%3d", freq[k]);
            System.out.println();
            freq[i] += freq[i - 1];
        }
        for (int k = 0; k <= max; k++)
            System.out.printf("%3d", freq[k]);
        System.out.println();

        System.out.println("Step3: ソート");
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k < n; k++)
                System.out.printf("%3d", tmp[k]);
            System.out.println();
            tmp[--freq[array[i]]] = array[i];
        }
        for (int k = 0; k < n; k++)
            System.out.printf("%3d", tmp[k]);
        System.out.println();

        if (n >= 0) System.arraycopy(tmp, 0, array, 0, n);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("度数ソート");

        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            do {
                System.out.print("x[" + i + "]:");
                x[i] = stdIn.nextInt();
            } while (x[i] < 0);
        }

        int max = x[0];
        for (int i = 1; i < nx; i++)
            if (x[i] > max) max = x[i];

        countingSort(x, nx, max);

        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}
