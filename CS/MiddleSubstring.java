package CS;

import java.util.Scanner;

/**
 * The type Middle substring.
 * 文字列 string を受け取り、文字数の半分を文字列の真ん中から返す
 * （文字数が 2 以下の場合は、最初の文字を返します。）
 * 返却する文字列の長さは、引数に与えられた文字列の長さを2で割って、小数を切り捨てた値、
 * 前方で削除する文字数は、返却する文字列の長さを2で割って、小数を切り上げた値になる。
 */
class middleSubstring {
    /**
     * Middle substring string.
     *
     * @param input the input
     * @return the string
     */
    public static String middleSubstring(String input) {
        int len = input.length();
        if (len <= 2) return input.substring(0, 1);
        double middle = len / 2;
        return input.substring((int) Math.ceil(middle / 2), (int) Math.ceil(middle / 2) + (int) Math.floor(middle));
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println(middleSubstring(stdIn.next()));
    }
}
