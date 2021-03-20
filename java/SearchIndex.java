package java;

import java.util.Scanner;

/**
 * The type Search index.
 */
public class SearchIndex {
    /**
     * Search index int.
     *
     * @param array the array
     * @param n     the n
     * @param key   the key
     * @param index the index
     * @return the int
     */
    /*
     * 配列arrayの先頭要素n個の要素からkeyと一致する全要素のインデックスを
     * 配列indexの先頭から順に格納して一致した要素数を返す
     * e.g){1, 9, 2, 9, 4, 6, 7, 9}でkey=9ならindex{1, 3, 7}を格納するとともに3を返却
     */
    static int searchIndex(int[] array, int n, int key, int[] index) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] == key) {
                index[count++] = i;
            }
        }
        return count;
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
        int[] y = new int[num + 1];
        for (int i = 0; i < num; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        System.out.print("探す値:");
        int key = stdIn.nextInt();

        int index = searchIndex(x, num, key, y);
        if (index == -1)
            System.out.println("その値の要素は存在しません");
        else
            System.out.println("その値はx[" + index + "]にあります");
    }
}
