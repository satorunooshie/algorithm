package java;

import java.util.Scanner;

/**
 * The type Card conv.
 */
class CardConv {
    /**
     * Card conv int.
     * 読み込んだ10進整数を2進数~36進数へと基数変換して表示する
     * 整数値xをr進数に変換した数字文字の並びを配列dに格納して桁数を返却
     *
     * @param x the x
     * @param r the r
     * @param d the d
     * @return the int
     */
    static int cardConv(int x, int r, char[] d) {
        //変換後の桁数
        int digits = 0;
        String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        do {
            // rで割った剰余をインデックスとする文字を配列dの要素d[digits]に格納し、直後にdigitsの値をインクリメントする
            d[digits++] = dchar.charAt(x % r);
            x /= r;
        } while (x != 0);
        // 配列dの並びを反転
        // 剰余を求めた順に格納していくため、配列dの先頭側が下位桁となるため
        for (int i = 0; i < digits / 2; i++) {
            char tmp = d[i];
            d[i] = d[digits - i - 1];
            d[digits - i - 1] = tmp;
        }
        return digits;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        // 変換する整数、基数、変換後の桁数、もう一度する？
        int no, cd, dno, retry;
        // 変換後の各桁を格納する文字の配列
        char[] cno = new char[32];
        System.out.println("10進数を基数変換する");
        do {
            do {
                System.out.print("変換する非負の整数:");
                no = stdIn.nextInt();
            } while (no < 0);
            do {
                System.out.print("何進数に変換？(2-36):");
                cd = stdIn.nextInt();
            } while (cd < 2 || cd > 36);
            dno = cardConv(no, cd, cno);
            System.out.print(cd + "進数では");
            for (int i = 0; i < dno; i++)
                System.out.print(cno[i]);
            System.out.println();
            System.out.print("もう一度しますか(yes = 1, no = 0):");
            retry = stdIn.nextInt();
        } while (retry == 1);
    }
}