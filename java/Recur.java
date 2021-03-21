package java;

import java.util.Scanner;

/**
 * トップダウン解析よりもボトムアップ解析
 * NEED: IntStack.class
 */
public class Recur {
    /**
     * 以前までのメソッドでは重複した計算を行なっていた
     * 同一の計算は一回きりにして複数回行わないようにするためにメモ化を行う
     * nに受け取る引数の値とメモよう配列のmemoのインデックスは1ズレる
     */
    static String[] memo;

    /**
     * Recur.
     *
     * @param n the n
     */
    static void recur(int n) {
        if (n > 0) {
            recur(n - 1);
            System.out.println(n);
            recur(n - 2);
        }
    }

    /**
     * Recur 1.
     *
     * @param n the n
     */
    static void recur1(int n) {
        if (n > 0) {
            recur(n - 1);
            recur(n - 2);
            System.out.println(n);
        }
    }

    /**
     * これ(recur1)を非再帰的に実現する
     *
     * @param n the n
     */
    static void recur2(int n) {
        int[] nstk = new int[100];
        int[] sstk = new int[100];
        int sw = 0, ptr = -1;
        while (true) {
            if (n > 0) {
                ptr++;
                nstk[ptr] = n;
                sstk[ptr] = sw;
                if (sw == 0)
                    n -= 1;
                else if (sw == 1) {
                    n -= 2;
                    sw = 0;
                }
                continue;
            }
            do {
                n = nstk[ptr];
                sw = sstk[ptr--] + 1;
                if (sw == 2) {
                    System.out.println(n);
                    if (ptr < 0) return;
                }
            } while (sw == 2);
        }
    }

    /**
     * 非再帰的表現(末尾再起の除去)
     *
     * 末尾再帰の除去は容易だが、先頭の再帰の除去は難しい
     * というのも、変数nの値を出力する前にrecur(n - 1)が行う処理を完了させねばならないから
     * そのため次のように単純に置き換えることはできない
     * nの値をn - 1に更新してメソッドの先頭に戻る
     * これはnが4であった場合再帰呼び出しrecur(3)の処理が完了するまでn=4の保存が必要だから
     * そのため現在のnの値を一時的に保存しておく処理が必要
     * さらにrecur(n - 1)の処理が完了してnの値を表示する際は保存していたnを取り出してその値を表示するという手順が必要になる
     *
     * @param n the n
     */
    static void tailRecur(int n) {
        while (n > 0) {
            tailRecur(n - 1);
            System.out.println(n);
            n -= 2;
        }
    }

    /**
     * NEED: IntStack.class
     *
     * スタックを用いて変数の一時的な保存を行なって再帰を除去したメソッド
     *
     * n = 4の場合
     * 4をスタックにプッシュする
     * nの値を1減らして3にする
     * continueでwhileの先頭に戻る
     * 結果スタックに4,3,2,1が積まれた状態になる
     * そのあとはnの値が1だけ減らされて0となって
     * while文の先頭に戻ってくる
     * 前のif文は実行されないので次の処理が行われる
     * スタックからポップした値1をnに取り出す
     * n = 1を表示する
     * nの値を2減らして-1とする
     * continueでwhileの先頭に戻る
     * そこでnの値は-1となるので後ろのif文が実行される
     * スタックから2がポップされ、、、
     *
     * @param n the n
     */
    static void noRecur(int n) {
        IntStack s = new IntStack(n);
        while (true) {
            if (n > 0) {
                s.push(n);
                n -= 1;
                continue;
            }
            if (!s.isEmpty()) {
                n = s.pop();
                System.out.println(n);
                n -= 2;
                continue;
            }
            break;
        }
    }

    /**
     * Memo recur.
     *
     * @param n the n
     */
    static void memoRecur(int n) {
        //既定値nullで初期化されている場合はメモをとっていない
        if (memo[n + 1] != null)
            //メモを既にとっている場合はそのまま出力
            System.out.print(memo[n + 1]);
        else {
            if (n > 0) {
                //ここは今までと同じ
                memoRecur(n - 1);
                System.out.println(n);
                memoRecur(n - 2);
                //メモ用の配列に表示内容の文字列を代入する
                memo[n + 1] = memo[n] + n + "\n" + memo[n - 1];
            } else {
                //n = 0, -1は空文字列
                memo[n + 1] = "";
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("整数を入力せよ:");
        int n = stdIn.nextInt();
        recur(n);

        System.out.print("整数を入力せよ:");
        n = stdIn.nextInt();
        tailRecur(n);

        System.out.print("整数を入力せよ:");
        n = stdIn.nextInt();
        noRecur(n);

        System.out.print("整数を入力せよ:");
        n = stdIn.nextInt();
        memo = new String[n + 2];
        memoRecur(n);

        System.out.println("-------------------");

        System.out.print("整数を入力せよ:");
        n = stdIn.nextInt();
        recur1(n);

        System.out.print("整数を入力せよ:");
        n = stdIn.nextInt();
        recur2(n);
    }
}