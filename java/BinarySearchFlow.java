package java;

import java.util.Scanner;

/**
 * The type Binary search flow.
 */
public class BinarySearchFlow {
    /**
     * Binary search int.
     *
     * @param array the array
     * @param n     the n
     * @param key   the key
     * @return the int
     */
    static int binarySearch(int[] array, int n, int key) {
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

        // 探索範囲先頭のインデックス
        int pl = 0;
        // 末尾のインデックス
        int pr = n - 1;

        do {
            // 中央要素のインデックス
            int pc = (pl + pr) / 2;
            System.out.print("   |");
            if (pl != pc) {
                System.out.printf(String.format("%%%ds<-%%%ds+", (pl * 4) + 1, (pc - pl) * 4), "", "");
            } else {
                System.out.printf(String.format("%%%ds<-+", pc * 4 + 1), "");
            }

            if (pc != pr) {
                System.out.printf(String.format("%%%ds->\n", (pr - pc) * 4 - 2), "");
            } else {
                System.out.println("->");
            }
            System.out.printf("%3d|", pc);
            for (int k = 0; k < n; k++) {
                System.out.printf("%4d", array[k]);
            }
            System.out.println("\n   |");
            if (array[pc] == key) {
                // 成功
                return pc;
            } else if (array[pc] < key) {
                // 探索範囲を後半に絞り込む
                pl = pc + 1;
            } else {
                // 探索範囲を前半に絞り込む
                pr = pc - 1;
            }
        } while (pl <= pr);
        return -1;
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
        System.out.println("昇順に入力してください");
        System.out.print("x[0]:");
        x[0] = stdIn.nextInt();
        for (int i = 1; i < num; i++) {
            do {
                System.out.print("x[" + i + "]:");
                x[i] = stdIn.nextInt();
            } while (x[i] < x[i - 1]);
        }
        System.out.print("探す値:");
        int key = stdIn.nextInt();

        int index = binarySearch(x, num, key);
        if (index < 0)
            System.out.println("その値の要素は存在しません");
        else
            System.out.println("その値はx[" + index + "]にあります");
    }
}
