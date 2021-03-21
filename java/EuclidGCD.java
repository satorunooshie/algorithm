package java;

import java.util.Scanner;

/**
 * The type Euclid gcd.
 */
class EuclidGCD {
    /**
     * 二つの整数値が与えられたとき、大きい方の値を小さい方の値で割ってみて
     * 割り切れる場合は小さい方の値が最大公約数
     * 割り切れない場合は小さい方の値と得られた剰余に対して同様の手続きを割り切れるまで
     * 再帰的に繰り返す
     *
     * イメージは長方形を正方形で埋め尽くすときの最大の辺の長さ
     * 22, 8であれば
     * 8*8*2+8*6
     * 8*6=6*6+2*6
     * 2*6=2*2*3
     *
     * 他の解法
     * 整数の大きい方から小さい方を引くことを両者が等しくなるまで繰り返す
     * 等しくなった値が最大公約数
     *
     * @param x the x
     * @param y the y
     * @return the int
     */
    static int gcd1(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd1(y, x % y);
    }

    /**
     * Gcd 2 int.
     *
     * @param x the x
     * @param y the y
     * @return the int
     */
    static int gcd2(int x, int y) {
        while (x != y) {
            if (x > y)
                x -= y;
            else
                y -= x;
        }
        return x;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("二つの整数の最大公約数を求める");
        System.out.print("整数を入力せよ");
        int x = stdIn.nextInt();
        System.out.print("整数を入力せよ");
        int y = stdIn.nextInt();
        System.out.println("最大公約数は" + gcd1(x, y));
        System.out.println("最大公約数は" + gcd2(x, y));
    }
}