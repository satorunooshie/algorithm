package java;

import java.util.Scanner;

/**
 * The type Seq search.
 */
/*
 * 線形検索
 * 逐次検索
 *
 * 探索アルゴリズム
 * ・配列からの探索
 *  1.線形探索
 *      ランダムに並んだデータの集まりからの探索を行う
 *  2.二分探索
 *      一定の規則で並んだデータの集まりから高速な探索を行う
 *  3.ハッシュ法
 *      追加・削除が高速に行えるデータの集まりからの高速な探索を行う
 *   ・チェインハッシュ法
 *          同一ハッシュ値のデータを線形リストで繋ぐ手法
 *   ・オープンアドレス法
 *          衝突時に再ハッシュを行う手法
 * ・線形リストからの探索
 * ・二分探索木からの探索
 *
 * ハッシュ法はデータの探索だけでなく、追加や削除などを効率よく行うための総合的な手法
 * データの集合から探索さえ行えば良いのであれば探索に要する計算時間が短いアルゴリズムを選択
 * とはいえ探索以外の操作に要するコストなども含めて総合的に評価を行なった上でアルゴリズムを選択する必要がある
 *
 * ある目的に対して複数のアルゴリズムが存在する場合は用途や目的、実行速度、対象となるデータ構造などを考慮してアルゴリズムを選択する
 */
class SeqSearch {
    /**
     * Seq search int.
     *
     * @param a   the a
     * @param n   the n
     * @param key the key
     * @return the int
     */
    static int seqSearch(int[] a, int n, int key) {
        /*
        無限ループ
        for(;;;)
        do{}while(true);
        while(true){}
         */
        for (int i = 0; i < n; i++)
            if (a[i] == key)
                return i;
        return -1;
        /* 上の方が簡潔
        int i = 0;
        while (true) {
            if (i == n)
                return -1;
            if (a[i] == key)
                return i;
            i++;
        }
         */
    }

    /**
     * Seq search trace.
     */
    public static void seqSearchTrace() {
        Scanner stdIn = new Scanner(System.in);
        int[] array = {85, 389, 20, 348, 38, 84, 22, 48, 95, 34, 59, 28, 55, 99};
        int x, pos, i;
        System.out.print("探す値:");
        x = stdIn.nextInt();
        pos = -1;
        System.out.printf("ループの前: x = %d\n", x);
        System.out.printf("ループの前: pos = %d\n", pos);
        for (i = 0; i < array.length && pos == -1; i++) {
            if (array[i] == x) pos = i;
            System.out.printf("ループの中: pos = %d, \ti = %d\n", pos, i);
        }
        System.out.printf("pos = %d\n", pos);
        System.out.printf("ループの後: pos = %d, \ti = %d\n", pos, i);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("要素数:");
        int num = stdIn.nextInt();
        int[] x = new int[num];
        for (int i = 0; i < num; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        System.out.print("探す値:");
        int key = stdIn.nextInt();
        int index = seqSearch(x, num, key);
        if (index == -1)
            System.out.println("その値は存在しません");
        else
            System.out.println("その値はx[" + index + "]にあります");
    }
}