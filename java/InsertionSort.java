package java;

import java.util.Scanner;

/**
 * O(n^2)
 */
class InsertionSort {

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
     * 別名シャトルソート
     * 飛び越えた要素の交換が行われることがないので安定している
     *
     * 原列の先頭要素を目的列内の適切な位置に挿入するという操作をn - 1回繰り返す
     * 左隣の要素が現在着目している要素の値よりも大きい限り
     * その値を代入する作業を繰り返す
     * 但し、挿入する値以下の要素に出会うとそこから先は捜査不要なためそこに挿入する値を代入する
     * つまり終了条件は
     * ・目的列の左端に到達した
     * ・tmpと等しいか、小さいキーを持った項目array[j - 1]が見つかった
     * のいずれか一方となり、ド・モルガンの法則を使えば、継続条件は
     * ・jが0より大きい
     * a[j - 1]の値がtmpより大きい
     * のどちらもが成立していることとなる
     *
     * @param array the array
     * @param n     the n
     */
    static void insertionSort(int[] array, int n) {
        for (int i = 1; i < n; i++) {
            int j;
            int tmp = array[i];
            for (j = i; j > 0 && array[j - 1] > tmp; j--)
                array[j] = array[j - 1];
            array[j] = tmp;
        }
    }

    /**
     * Insertion sort.
     *
     * @param array the array
     */
    static void insertionSort(int[] array) {
        int ins, cmp, tmp;
        printArray(array);
        for (ins = 1; ins < array.length; ins++) {
            tmp = array[ins];
            for (cmp = ins - 1; cmp >= 0; cmp--) {
                if (array[cmp] > tmp)
                    array[cmp + 1] = array[cmp];
                else
                    break;
            }
            array[cmp + 1] = tmp;
        }
        printArray(array);
    }

    /**
     * 配列の先頭要素が未使用でデータがarray[1]以降に格納されていれば、
     * array[0]を番兵とすることによって挿入処理の終了条件を緩和できる
     *
     * @param array the array
     * @param n     the n
     */
    static void sentinelInsertionSort(int[] array, int n) {
        for (int i = 2; i <= n; i++) {
            int tmp = array[0] = array[i];
            int j = i;
            for (; array[j - 1] > tmp; j--)
                array[j] = array[j - 1];
            array[j] = tmp;
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("単純挿入ソート(番兵)");

        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        // 1個余分に生成
        int[] x = new int[nx + 1];
        // x[1] ~ x[nx]に読み込む
        for (int i = 1; i <= nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        sentinelInsertionSort(x, nx);

        System.out.println("昇順にソートしました");
        for (int i = 1; i <= nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);

        System.out.print("要素数:");
        nx = stdIn.nextInt();
        x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        insertionSort(x, nx + 1);

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
        insertionSort(x);

        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}