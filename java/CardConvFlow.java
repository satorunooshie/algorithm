package java;

import java.util.Scanner;

/**
 * The type Card conv flow.
 */
public class CardConvFlow {
    /**
     * 整数値xをr進数に変換して配列dに下位桁から格納して桁数を返却
     *
     * @param x the x
     * @param r the r
     * @param d the d
     * @return int
     */
    static int cardConv(int x, int r, char[] d) {
        // 変換前の桁数
        int n = ((Integer) x).toString().length();
        // 変換後の桁数
        int digits = 0;
        String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.printf(String.format("%%2d | %%%dd\n", n), r, x);
        do {
            System.out.print("  +");
            for (int i = 0; i < n + 2; i++) {
                System.out.print('-');
            }
            System.out.println();
            if (x / r != 0) {
                System.out.printf(String.format("%%2d | %%%dd    ...  %%d\n", n), r, x / r, x % r);
            } else {
                System.out.printf(String.format("     %%%dd    ...  %%d\n", n), x / r, x % r);
            }
            // rで割った剰余を格納
            d[digits++] = dchar.charAt(x % r);
            x /= r;
        } while (x != 0);
        // d[0] ~ d[digits - 1]の並びを反転
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
        //変換する整数、基数、変換後の桁数、もう一度する？
        int no, cd, dno, retry;
        //変換後の各桁を格納する文字の配列
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
