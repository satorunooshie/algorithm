import java.util.Arrays;
import java.util.Scanner;

/**
 * いずれも配列を昇順にソート
 * 引数が3つのものはソートの対象が限定できる
 * 改良されたクイックソートで安定ではない
 *  static void sort(byte[] a)
 *  static void sort(byte[] a, int fromIndex, int toIndex)
 *  static void sort(char[] a)
 *  static void sort(char[] a, int fromIndex, int toIndex)
 *  static void sort(double[] a)
 *  static void sort(double[] a, int fromIndex, int toIndex)
 *  static void sort(float[] a)
 *  static void sort(float[] a, int fromIndex, int toIndex)
 *  static void sort(int[] a)
 *  static void sort(int[] a, int fromIndex, int toIndex)
 *  static void sort(long[] a)
 *  static void sort(long[] a, int fromIndex, int toIndex)
 *  static void sort(short[] a)
 *  static void sort(short[] a, int fromIndex, int toIndex)
 * ここから下は改良されたマージソートで安定
 * 自然な順序で要素の代償関係を判定してソートするのでInteger型やString型の配列などのソートに適している
 *  static void sort(Object[] a)
 *  static void sort(Object[] a, int fromIndex, int toIndex)
 * 自然な順序でない順序で並べられた配列や自然な順序を論理的に持たないクラスの配列のソートを行う
 * 要素の代償関係はコンパレータcを用いて行われる
 *  static <T> void sort(T[] a, Comparator<? super T> c)
 *  static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c)
 */
class ArraySortTester {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("要素数:");
        int num = stdIn.nextInt();
        int[] x = new int[num];

        for (int i = 0; i < num; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        Arrays.sort(x);
        System.out.println("昇順にソートしました");
        for (int i= 0; i < num; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}