package java;

import java.util.Scanner;

/**
 * O(n^2)
 */
class BubbleSort {
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
     * 隣接する二つの要素を比較して先頭側が大きければ交換する作業を末尾側から先頭側へ操作しながら行う
     * 要素の比較回数はn(n - 1) / 2
     * ただし実際の要素の交換回数は配列の要素の値によって左右されるのでn(n - 1) / 4となる
     * swap内で代入が3回行われるので移動回数の平均は3n(n - 1) / 4
     *
     * @param array the array
     * @param n     the n
     */
    static void bubbleSort1(int[] array, int n) {
        for (int i = 0; i < n - 1; i++)
            for (int j = n - 1; j > i; j--)
                if (array[j - 1] > array[j])
                    swap(array, j - 1, j);
    }

    /**
     * 先頭側から末尾側へ走査
     * 各パスでは最大要素が末尾側へと移動する
     *
     * @param array the array
     * @param n     the n
     */
    static void bubbleSort1_reverse(int[] array, int n) {
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++)
                if (array[j] > array[j + 1])
                    swap(array, j, j + 1);
        }
    }

    /**
     * ソートが完了すればそれ以降のパスで交換が行われることはないのでそれ以降は打ち切りにする
     * 交換回数による打ち切りを導入することでソート済みの配列やそれに近い状態の配列に対して行う必要のない比較が大幅に省略される
     *
     * @param array the array
     * @param n     the n
     */
    static void bubbleSort2(int[] array, int n) {
        for (int i = 0; i < n - 1; i++) {
            int exchg_cnt = 0;
            for (int j = n - 1; j > i; j--)
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                    exchg_cnt++;
                }
            if (exchg_cnt == 0) break;
        }
    }

    /**
     * 走査範囲を限定する
     * 一連の比較・交換を行うパスにおいてある時点以降に交換がなければそれより先頭はソート済み
     * lastは各パスで最後に交換した2要素の右側要素のインデックスを格納するための変数
     * パスが終了した時点でlastの値をkに代入することで次の走査範囲がa[k]までに限定されて
     * 次のパスで最後に比較される要素はa[k]とa[k + 1]になる(最後に交換された位置は走査済みになるので)
     * 冒頭でĸを初期化しているのは先頭までの全要素を走査するため
     *
     * @param array the array
     * @param n     the n
     */
    static void bubbleSort3(int[] array, int n) {
        // array[k]より前はソート済み
        int k = 0;
        while (k < n - 1) {
            // 最後に交換した位置
            int last = n - 1;
            for (int j = n - 1; j > k; j--)
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                    last = j;
                }
            k = last;
        }
    }

    /**
     * 単純選択ソート
     * 未ソート部分から最小の要素を選択して未ソート部分の先頭要素と交換する操作を繰り返す
     * (n^2 - n) / 2
     * 離れた要素を交換するため安全ではない
     *
     * @param array the array
     * @param n     the n
     */
    static void selectionSort(int[] array, int n) {
        for (int i = 0; i < n - 1; i++) {
            // 未ソート部の最小要素のインデックス
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (array[j] < array[min])
                    min = j;
            // 末ソート部の先頭要素と最小要素を交換
            swap(array, i, min);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("単純交換ソート(バブルソート)");
        System.out.print("要素数: ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        // bubbleSort1(x, nx);
        // bubbleSort1_reverse(x, nx);
        // bubbleSort2(x, nx);
        bubbleSort3(x, nx);
        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}