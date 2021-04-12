package java;

import java.util.Scanner;

/**
 * The type Insertion sort flow.
 */
public class InsertionSortFlow {

    /**
     * Print array.
     *
     * @param array the array
     */
    public static void printArray(int[] array) {
        for (int j : array) System.out.print("[" + j + "]");
        System.out.println();
    }

    /**
     * 挿入過程を詳細に表示する
     * 着目要素の下に+、
     * 挿入される位置の要素の下に^
     * その間に-を表示
     * 挿入が行われない場合は着目要素の下に+のみ表示
     *
     * @param array the array
     * @param n     the n
     */
    static void insertionSortTrace(int[] array, int n) {
        for (int i = 1; i < n; i++) {
            for (int m = 0; m < n; m++)
                System.out.printf("%3d ", array[m]);
            System.out.println();

            int j;
            int tmp = array[i];
            for (j = i; j > 0 && array[j - 1] > tmp; j--)
                array[j] = array[j - 1];
            array[j] = tmp;
            System.out.print(" ".repeat(4 * j));
            System.out.print(i != j ? "^-" : " ");
            System.out.print("-".repeat(4 * (i - j)));
            System.out.println("+");
            System.out.printf("array[%d]の%dをarray[%d]の位置に挿入しました\n\n", i, tmp, j);
        }
    }

    /**
     * Insertion sort trace.
     */
    public static void insertionSortTrace() {
        int[] array = {90, 34, 78, 12, 56};
        int ins, cmp, tmp;
        printArray(array);
        for (ins = 1; ins < array.length; ins++) {
            System.out.printf("外側のループ: tmp <- array[%d] = %d\n", ins, array[ins]);
            tmp = array[ins];
            for (cmp = ins - 1; cmp >= 0; cmp--) {
                System.out.printf("内側のループ: ins <- ins = %d, cmp = %d, tmp = %d\n", ins, cmp, tmp);
                if (array[cmp] > tmp)
                    array[cmp + 1] = array[cmp];
                else {
                    System.out.println("breakで中断");
                    break;
                }
            }
            System.out.printf("外側のループ: ins = %d, cmp = %d, tmp = %d\n", ins, cmp, tmp);
            System.out.printf("外側のループ: 確定した挿入位置 = array[%d] <- tmp\n\n", cmp + 1);
            array[cmp + 1] = tmp;
        }
        printArray(array);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("単純挿入ソート");

        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        insertionSortTrace(x, nx);
        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);

        System.out.print("要素数:");
        nx = stdIn.nextInt();
        x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        insertionSortTrace();
    }
}
