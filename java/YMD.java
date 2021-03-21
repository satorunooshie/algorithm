package java;

import java.util.Scanner;

/**
 * The type Ymd.
 */
public class YMD {
    /**
     * The Mdays.
     */
    static int[][] mdays = {
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
            // 閏年
            {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
    };
    /**
     * The Y.
     */
    int y, /**
     * The M.
     */
    m, /**
     * The D.
     */
    d;

    /**
     * Instantiates a new Ymd.
     *
     * @param y the y
     * @param m the m
     * @param d the d
     */
    YMD(int y, int m, int d) {
        if (m > 12 || m < 1) {
            m = 1;
        }
        if (d > 31 || d < 1) {
            d = 1;
        }
        this.y = y;
        this.m = m;
        this.d = d;
    }

    /**
     * Is leap int.
     *
     * @param year the year
     * @return the int
     */
    static int isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("日付を入力してください");
        System.out.print("年: ");
        int y = stdIn.nextInt();
        System.out.print("月: ");
        int m = stdIn.nextInt();
        System.out.print("日: ");
        int d = stdIn.nextInt();
        YMD date = new YMD(y, m, d);
        System.out.print("何日前/何日後の日付を求めますか: ");
        int n = stdIn.nextInt();
        YMD d1 = date.after(n);
        System.out.printf("%d日後の日付は%d年%d月%d日です\n", n, d1.y, d1.m, d1.d);
        YMD d2 = date.before(n);
        System.out.printf("%d日前の日付は%d年%d月%d日です\n", n, d2.y, d2.m, d2.d);
    }

    /**
     * After ymd.
     *
     * @param n the n
     * @return the ymd
     */
// n日後の日付を返す
    YMD after(int n) {
        YMD tmp = new YMD(this.y, this.m, this.d);
        if (n < 0)
            return before(-n);
        tmp.d += n;
        while (tmp.d > mdays[isLeap(tmp.y)][tmp.m - 1]) {
            tmp.d -= mdays[isLeap(tmp.y)][tmp.m - 1];
            if (++tmp.m > 12) {
                tmp.y++;
                tmp.m = 1;
            }
        }
        return tmp;
    }

    /**
     * Before ymd.
     *
     * @param n the n
     * @return the ymd
     */
    YMD before(int n) {
        YMD tmp = new YMD(this.y, this.m, this.d);
        if (n < 0)
            return after(-n);
        tmp.d -= n;
        while (tmp.d < 1) {
            if (--tmp.m < 1) {
                tmp.y--;
                tmp.m = 12;
            }
            tmp.d += mdays[isLeap(tmp.y)][tmp.m - 1];
        }
        return tmp;
    }
}