package java;

import java.util.Scanner;

/*
 * O(n log n)
 * 配列が次々と分割されてより小さい問題を解く処理が繰り返されるため
 * ただ遅くなる場合もある
 * 最悪の時間計算量はO(n^2)
 */

/**
 * クイックソート
 * 配列から任意の一つの要素を取り出して、
 * その要素以上のグループと、その要素以下のグループに分ける
 * グループ分けの基準を枢軸という。
 * 各グループに対して枢軸を設定して分割を繰り返していき、全てのグループの要素数が1になるとソートが終了
 *
 * prが先頭より右側に位置する(left < pr)であれば左グループを分割する
 * plが末尾よりも左側に位置する(pl < right)であれば右グループを分割する
 * 中央グループ(array[pr + 1] ~ array[pl - 1])ができた場合(pl > pr + 1)は分割対象から外す
 *
 * 8王妃問題同様分割統治法なので再帰呼び出しを用いて簡潔にできる
 *
 * 遠く離れた要素を交換するので安定ではない
 */
class QuickSort {
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
     * Print array.
     *
     * @param array the array
     */
    public static void printArray(int[] array) {
        for (int j : array) System.out.print("[" + j + "]");
        System.out.println();
    }

    /**
     * 枢軸を配列の中心とせず、配列の先頭とする
     *
     * @param array the array
     * @param head  the head
     * @param tail  the tail
     * @return the int
     */
    public static int divideArray(int[] array, int head, int tail) {
        int left, right;
        left = head + 1;
        right = tail;
        // 基準値より小さい要素を前側に大きい要素を後ろ側に移動する
        while (true) {
            // 後ろに向かって辿って基準値よりも大きい要素を見つける
            while (left < tail && array[head] > array[left]) {
                left++;
            }
            while (array[head] < array[right]) {
                right--;
            }
            if (left >= right) break;
            swap(array, left, right);
            left++;
            right++;
        }
        swap(array, head, right);
        return right;
    }

    /**
     * Sort array.
     *
     * @param array the array
     * @param start the start
     * @param end   the end
     */
    public static void sortArray(int[] array, int start, int end) {
        int pivot;
        if (start < end) {
            // 基準値の大小関係に応じてグループ分け
            pivot = divideArray(array, start, end);
            // 基準値よりも小さい前側のグループに同じ処理を適用する
            sortArray(array, start, pivot - 1);
            sortArray(array, pivot + 1, end);
        }
    }

    /**
     * ここまで2メソッドはセット
     * sortArray(array, 0, array.length - 1);
     *
     * @param array the array
     * @param left  the left
     * @param right the right
     */
    // NOTE: デフォルト引数使えないのでオーバーロード
    static void quickSort1(int[] array, int left, int right) {
        quickSort1(array, left, right, false);
    }

    /**
     * Quick sort 1.
     *
     * @param array    the array
     * @param left     the left
     * @param right    the right
     * @param getTrace the get trace
     */
    static void quickSort1(int[] array, int left, int right, boolean getTrace) {
        // 左カーソル
        int pl = left;
        // 右カーソル
        int pr = right;
        // 枢軸
        int x = array[(pl + pr) / 2];

        if (getTrace) {
            System.out.printf("array[%d]~array[%d] : {", left, right);
            for (int i = left; i < right; i++)
                System.out.printf("%d , ", array[i]);
            System.out.printf("%d}\n", array[right]);
        }

        do {
            while (array[pl] < x) pl++;
            while (array[pr] > x) pr--;
            if (pl <= pr) swap(array, pl++, pr--);
        } while (pl <= pr);

        /* 要素数が小さい方のグループを優先的に分割する(+α) */
        if (pr - left < right - pl) {
            int tmp;
            tmp = left;
            left = pl;
            pl = tmp;
            tmp = right;
            right = pr;
            pr = tmp;
        }

        // 左右の各グループを再分割するため
        if (left < pr) quickSort1(array, left, pr);
        if (pl < right) quickSort1(array, pl, right);
    }

    /**
     * getTraceでプッシュ、ポップ、分割の様子を詳細に表示する
     *
     * @param array the array
     * @param left  the left
     * @param right the right
     */
    // NOTE: デフォルト引数使えないのでオーバーロード
    static void quickSort2(int[] array, int left, int right) {
        quickSort2(array, left, right, false);
    }

    /**
     * 非再帰的クイックソート
     *
     * @param array    the array
     * @param left     the left
     * @param right    the right
     * @param getTrace the get trace
     */
    static void quickSort2(int[] array, int left, int right, boolean getTrace) {
        // 分割すべき範囲の先頭要素のインデックスを保存するスタック
        IntStack lstack = new IntStack(right - left + 1); // 分割すべき配列の要素数に同じ
        // 分割すべき範囲の末尾要素のインデックスを保存するスタック
        IntStack rstack = new IntStack(right - left + 1);

        // 分割すべき配列の範囲である先頭要素のインデックスと末尾要素のインデックスをプッシュ
        lstack.push(left);
        rstack.push(right);

        if (getTrace) {
            System.out.printf("array[%d]~array[%d]を分割する問題をスタックにプッシュ\n", left, right);
            System.out.print("Lstack:");
            lstack.dump();
            System.out.print("Rstack:");
            rstack.dump();
        }

        // スタックに入っているのは分割すべき配列の範囲
        while (!lstack.isEmpty()) {
            // 左端のインデックス
            int pl = left = lstack.pop();
            // 右端のインデックス
            int pr = right = rstack.pop();
            int x = array[(left + right) / 2];

            if (getTrace) System.out.printf("スタックから分割する問題を取り出しました。array[%d]~array[%d]を分割\n", left, right);

            do {
                while (array[pl] < x) pl++;
                while (array[pr] > x) pr--;
                if (pl <= pr)
                    swap(array, pl++, pr--);
            } while (pl <= pr);

            /* 要素数の大きい方を先にプッシュ(+α) */
            if (pr - left < right - pl) {
                int tmp;
                tmp = left;
                left = pl;
                pl = tmp;
                tmp = right;
                right = pr;
                pr = tmp;
            }

            if (left > pr) {
                lstack.push(left);
                rstack.push(pr);

                if (getTrace) {
                    System.out.printf("array[%d]~array[%d]を分割する問題をスタックにプッシュ\n", left, right);
                    System.out.print("Lstack:");
                    lstack.dump();
                    System.out.print("Rstack:");
                    rstack.dump();
                }
            }
            if (pl < right) {
                lstack.push(pl);
                rstack.push(right);

                if (getTrace) {
                    System.out.printf("array[%d]~array[%d]を分割する問題をスタックにプッシュ\n", left, right);
                    System.out.print("Lstack:");
                    lstack.dump();
                    System.out.print("Rstack:");
                    rstack.dump();
                }
            }
        }
    }

    /*
     * スタックの容量を考察する
     * スタックのプッシュの順序の方針
     * 1.要素数の大きい方のグループを先にプッシュ
     * 2.要素数の小さい方をグループを先にプッシュ
     *  1.第一段階でスタックに同時に積まれる数は最大で二個
     *  2.第一段階でスタックに同時に積まれる数は最大で四個
     * 要素数が小さい配列ほど少ない回数での分割終了が期待できるので
     * 1の要素数が大きいグループの分割を後回しにして、小さいグループの分割を先に行ったほうが
     * 同時に積まれる値は少なくなる
     * 但し、スタックに対する出し入れの回数はどちらも同じで
     * 同時に積まれるデータ数の最大値が異なるだけ
     * 例えば配列の要素数がnであれば同時に積まれるデータの数はlog(n)で収まる
     * n = 1000000でもスタックの容量は20で十分
     */

    /**
     * 枢軸の選択法
     * 偏った分割を繰り返すのは高速なソートにならない
     * 配列が偏ることなく半分の大きさに分割されるように
     * 配列のソート後に中央に位置する値、すなわち、値としての中央値を枢軸とするのが理想
     *
     * 1.分割すべき配列の要素数が3以上であれば、
     * 任意の三つの要素を取り出してその中央値を持つ要素を枢軸として採用する
     * 2.分割すべき配列の先頭要素/中央要素/末尾要素の3要素をソートして
     * さらに中央要素と末尾から2番目の要素を交換する
     * 枢軸として末尾から2番目の要素の値array[right - 1]を採用するとともに
     * 分割の対象をarray[left + 1] ~ array[right - 2]に絞り込む
     * そこでarray[left]は枢軸以下の値で
     * array[right - 1]とarray[right]は枢軸以上の値であるため、走査のためのカーソルの位置を変更する
     * ・左カーソルplの開始位置
     * left -> left + 1
     * ・右カーソルprの開始位置
     * right -> right - 2
     *
     * --tips--
     * グループ分けをして要素数が足りなくなったら三つの要素の真ん中を選ぶ処理が無駄になるので
     * クイックソートでグループ分けをして要素数が10個程度になったらそこから先は挿入法でソートを行うことがある
     *
     * @param x the x
     * @param a the a
     * @param b the b
     * @param c the c
     * @return the int
     */
    static int sort3elem(int[] x, int a, int b, int c) {
        if (x[b] < x[a]) swap(x, b, a);
        if (x[c] < x[b]) swap(x, c, b);
        if (x[b] < x[a]) swap(x, b, a);
        return b;
    }

    /**
     * 単純挿入ソート
     * クイックソートは要素数が小さい配列に対してはそれほど高速ではないので
     * 分割されたグループの要素数が9以下であれば、単純挿入ソートに切り替える
     *
     * @param array the array
     * @param left  the left
     * @param right the right
     */
    static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int tmp = array[i];
            int j;
            for (j = i; j > left && array[j - 1] > tmp; j--)
                array[j] = array[j - 1];
            array[j] = tmp;
        }
    }

    /**
     * Quick sort 3.
     *
     * @param array the array
     * @param left  the left
     * @param right the right
     */
    static void quickSort3(int[] array, int left, int right) {
        if (right - left < 9)
            insertionSort(array, left, right);
        else {
            int pl = left;
            int pr = right;
            // 先頭・中央・末尾をソート
            int m = sort3elem(array, pl, (pl + pr) / 2, pr);
            // 枢軸
            int x = array[m];
            // 中央と末尾から2番目を交換
            swap(array, m, right - 1);
            // 左カーソルを一個右に
            pl++;
            // 右カーソルを二個左に
            pr -= 2;

            do {
                while (array[pl] < x) pl++;
                while (array[pr] > x) pr--;
                if (pl <= pr)
                    swap(array, pl++, pr--);
            } while (pl <= pr);
            if (left < pr) quickSort3(array, left, pr);
            if (pl < right) quickSort3(array, pl, right);
        }
    }

    /**
     * 受け取る引数を2つにする
     *
     * @param array the array
     * @param n     the n
     */
    static void qsort(int[] array, int n) {
        quickSort3(array, 0, n - 1);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("クイックソート");

        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        printArray(x);
        // sortArray(x, 0, nx - 1);
        // quickSort1(x, 0, nx - 1);
        // quickSort2(x, 0, nx - 1);
        // quickSort3(x, 0, nx - 1);
        qsort(x, nx);
        System.out.println("昇順にソートしました");
        printArray(x);
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}