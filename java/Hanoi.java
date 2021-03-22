package java;

import java.util.Scanner;

/**
 * The type Hanoi.
 */
class Hanoi {
    /**
     * The Name.
     */
    static String[] name = {"A軸", "B軸", "C軸"};

    /**
     * Move.
     *
     * @param no the no
     * @param x  the x
     * @param y  the y
     */
    static void move(int no, int x, int y) {
        //底の円盤を除いたグループを開始軸から中間軸へ移動
        if (no > 1)
            move(no - 1, x, 6 - x - y);
        //底の円盤noを開始軸から目的軸へ移動した旨を表示
        System.out.printf("円盤[%d]を%d軸から%d軸へ移動\t", no, x, y);
        System.out.printf("円盤[%d]を%s軸から%s軸へ移動\n", no, name[x - 1], name[y - 1]);
        //底の円盤を除いたグループを中間軸から目的軸へ移動
        if (no > 1)
            move(no - 1, 6 - x - y, y);
    }

    /**
     * Move without recur.
     *
     * @param no the no
     * @param x  the x
     * @param y  the y
     */
    static void moveWithoutRecur(int no, int x, int y) {
        // stack
        int[] xstk = new int[100];
        int[] ystk = new int[100];
        int[] sstk = new int[100];
        // stack pointer
        int ptr = 0;
        int sw = 0;

        while (true) {
            if (sw == 0 && no > 1) {
                // push
                xstk[ptr] = x;
                ystk[ptr] = y;
                sstk[ptr] = sw;
                ptr++;
                no -= 1;
                y = 6 - x - y;
                continue;
            }
            System.out.printf("円盤[%d]を%d軸から%d軸へ移動\t", no, x, y);
            System.out.printf("円盤[%d]を%s軸から%s軸へ移動\n", no, name[x - 1], name[y - 1]);

            if (sw == 1 && no > 1) {
                xstk[ptr] = x;
                ystk[ptr] = y;
                sstk[ptr] = sw;
                ptr++;
                no -= 1;
                x = 6 - x - y;
                if (++sw == 2) sw = 0;
                continue;
            }
            do {
                // empty
                if (ptr-- == 0) return;
                // pop x
                x = xstk[ptr];
                // pop y
                y = ystk[ptr];
                // pop sw
                sw = sstk[ptr] + 1;
                no++;
            } while (sw == 2);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("ハノイの塔");
        System.out.print("円盤の枚数:");
        int n = stdIn.nextInt();
        //第一軸に積まれたn枚を第三軸に移動
        move(n, 1, 3);
        moveWithoutRecur(n, 1, 3);
    }
}