package java;

import java.util.Scanner;

/*
 * Scannerクラスのnextメソッド
 * nextBoolean(): boolean true|false
 * nextByte(): byte -128~127
 * nextShort(): short -32768~32767
 * nextInt(): int -2147483648~2147483647
 * nextLong(): long -9223372036854775808~9223372036854775807
 * nextFloat(): float +-3.40282347E+38~+-1.40239846E-45
 * nextDouble(): double +-1.79769313486231507E+378~+-4.94065645841246544E-324
 * next(): String スペースと改行で区切られた文字列
 * nextLine(): String 一行の文字列
 */

/**
 * The type Max and min.
 */
class MaxAndMin {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        test();
        System.out.println("三つの整数の最大値を求める");
        System.out.print("a:");
        int a = stdIn.nextInt();
        System.out.print("b:");
        int b = stdIn.nextInt();
        System.out.print("c:");
        int c = stdIn.nextInt();
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;
        System.out.println("最大値は" + max);
    }

    /**
     * Test.
     */
    static void test() {
        System.out.println(max(1, 2, 3));
        System.out.println(max(2, 4, 5, 6));
        System.out.println(min(0, 2, 4));
        System.out.println(min(4, 5, 6, 7));
    }

    /**
     * Max int.
     *
     * @param a the a
     * @param b the b
     * @param c the c
     * @return the int
     */
    static int max(int a, int b, int c) {
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;
        return max;
    }

    /**
     * Max int.
     * 4つの最大値を求めるメソッドを作成
     *
     * @param a the a
     * @param b the b
     * @param c the c
     * @param d the d
     * @return the int
     */
    static int max(int a, int b, int c, int d) {
        int max = max(a, b, c);
        if (d > max) max = d;
        return max;
    }

    /**
     * Min int.
     * 3つの最小値を求めるメソッドを作成
     *
     * @param a the a
     * @param b the b
     * @param c the c
     * @return the int
     */
    static int min(int a, int b, int c) {
        int min = a;
        if (b < min) min = b;
        if (c < min) min = c;
        return min;
    }

    /**
     * Min int.
     * 4つの最小値を求めるメソッドを作成
     *
     * @param a the a
     * @param b the b
     * @param c the c
     * @param d the d
     * @return the int
     */
    static int min(int a, int b, int c, int d) {
        int min = min(a, b, c);
        if (d < min) min = d;
        return min;
    }
}