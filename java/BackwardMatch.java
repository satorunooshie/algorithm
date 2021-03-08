import java.util.Scanner;

/**
 * 文字列の末尾が一致している場合、その末尾を出力せよ
 * チーズケーキ チーズボール => ""
 * ショートケーキ チーズケーキ => "ケーキ"
 */
public class BackwardMatch {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        String[] input = stdIn.nextLine().split(" ");
        String dish1 = input[0];
        String dish2 = input[1];
        if (dish1.equals(dish2)) {
            System.out.println(dish1);
        } else {
            // 文字列の長さが長い方
            int longer = dish2.length();
            if (dish1.length() > dish2.length()) {
                longer = dish1.length();
            }

            String[] target = new String[longer];
            int i = 1;
            for (i = 1; i < longer; i++) {
                target[i - 1] = dish1.substring(dish1.length() - i);
                // 最後の文字列が一致していたら
                if (!dish2.endsWith(target[i - 1])) {
                    break;
                }
            }
            if (i != 1) {
                System.out.println(target[i - 2]);
            }
        }
    }
}
