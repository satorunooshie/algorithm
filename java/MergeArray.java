package java;

/**
 * O(n)
 */
class MergeArray {
    /**
     * 各配列の着目要素の値を比較して小さい方の値を持つ要素を取り出して別の配列に格納する
     * 三つの配列を同時に走査する
     * 最初は先頭要素に着目するため0で初期化
     *
     * @param array1 the array 1
     * @param len1   the len 1
     * @param array2 the array 2
     * @param len2   the len 2
     * @param array3 the array 3
     */
    static void merge(int[] array1, int len1, int[] array2, int len2, int[] array3) {
        int p1, p2, p3;
        p1 = p2 = p3 = 0;
        // 小さい方を3に格納
        while (p1 < len1 && p2 < len2)
            array3[p3++] = (array1[p1] <= array2[p2]) ? array1[p1++] : array2[p2++];
        // 1に残った要素をコピー
        while (p1 < len1)
            array3[p3++] = array1[p1++];
        // 2に残った要素をコピー
        while (p2 < len2)
            array3[p3++] = array2[p2++];
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[] array1 = {2, 4, 6, 8, 11, 13};
        int[] array2 = {1, 2, 3, 4, 9, 16, 21};
        int[] array3 = new int[13];

        System.out.println("二つの配列をマージ");

        merge(array1, array1.length, array2, array2.length, array3);

        System.out.println("配列1:");
        for (int i = 0; i < array1.length; i++)
            System.out.println("array1[" + i + "]=" + array1[i]);

        System.out.println("配列2:");
        for (int i = 0; i < array2.length; i++)
            System.out.println("array2[" + i + "]=" + array2[i]);

        System.out.println("配列3:");
        for (int i = 0; i < array3.length; i++)
            System.out.println("array3[" + i + "]=" + array3[i]);
    }
}