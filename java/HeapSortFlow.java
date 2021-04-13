package java;

import java.util.Scanner;

/**
 * ダウンヒープが呼び出される旅に配列の値を木形式で表示する
 * ---------------------------------------------
 * ヒープソート
 * 要素数:5
 * x[0]:5
 * x[1]:4
 * x[2]:3
 * x[3]:2
 * x[4]:1
 *       05
 *      ／＼
 *   04      03
 *  ／＼
 * 02  01
 *
 *       05
 *      ／＼
 *   04      03
 *  ／＼
 * 02  01
 *
 *       05
 *      ／＼
 *   04      03
 *  ／＼
 * 02  01
 *
 *       01
 *      ／＼
 *   04      03
 *  ／＼
 * 02  05
 *
 *       01
 *      ／＼
 *   02      03
 *  ／＼
 * 04  05
 *
 *       01
 *      ／＼
 *   02      03
 *  ／＼
 * 04  05
 *
 *       01
 *      ／＼
 *   02      03
 *  ／＼
 * 04  05
 *
 * 昇順にソートしました
 * x[0]=1
 * x[1]=2
 * x[2]=3
 * x[3]=4
 * x[4]=5
 */

public class HeapSortFlow {
    /**
     * Swap.
     *
     * @param array  the array
     * @param index1 the index 1
     * @param index2 the index 2
     */
    static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * Pow 2 int.
     *
     * @param n the n
     * @return the int
     */
    static int pow2(int n) {
        int k = 1;
        while (n-- > 0)
            k *= 2;
        return k;
    }

    /**
     * Display space.
     *
     * @param n the n
     */
    static void displaySpace(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(' ');
    }

    /**
     * Display heap.
     *
     * @param array the array
     * @param n     the n
     */
    static void displayHeap(int[] array, int n) {
        int i = n;
        int height = 1;
        while ((i >>= 1) > 0)
            height++;
        i = 0;
        int w = 1;
        Loop:
        {
            for (int level = 0; level < height; level++) {
                displaySpace(pow2(height - level) - 2);
                for (int k = 0; k < w; k++) {
                    System.out.printf("%02d", array[i++]);
                    if (i >= n) break Loop;
                    if (k < w - 1)
                        displaySpace(pow2(height - level + 1) - 2);
                }
                System.out.println();

                displaySpace(pow2(height - level) - 3);
                for (int k = 0; k < w; k++) {
                    if (2 * k + i < n) System.out.print("／");
                    if (2 * k + i + 1 < n) System.out.print("＼");
                    if (k < w - 1) displaySpace(pow2(height - level + 1) - 4);
                }
                System.out.println();
                w *= 2;
            }
        }
        System.out.println("\n");
    }

    /**
     * array[left]~array[right]をヒープ化
     *
     * @param array the array
     * @param left  the left
     * @param right the right
     */
    static void downHeap(int[] array, int left, int right) {
        // 根
        int tmp = array[left];
        int child, parent;
        for (parent = left; parent < (right + 1) / 2; parent = child) {
            int cl = parent * 2 + 1;
            int cr = cl + 1;
            // 大きい方
            child = (cr <= right && array[cr] > array[cl]) ? cr : cl;
            if (tmp >= array[child]) break;
            array[parent] = array[child];
        }
        array[parent] = tmp;
    }

    /**
     * Heap sort.
     *
     * @param array the array
     * @param n     the n
     */
    static void heapSort(int[] array, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--) {
            displayHeap(array, n);
            downHeap(array, i, n - 1);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            displayHeap(array, n);
            downHeap(array, 0, i - 1);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("ヒープソート");

        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }

        heapSort(x, nx);

        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}
