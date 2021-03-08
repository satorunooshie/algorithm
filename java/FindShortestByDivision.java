import java.util.Arrays;
import java.util.Scanner;

/**
 * 二つに分けた時に最短になるような大きい方の数を出力せよ
 * 10 15 15 20 => 30
 */
public class FindShortestByDivision {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        String[] input = stdIn.nextLine().split(" ");
        int len = input.length;
        Arrays.sort(input);
        int no1 = 0, no2 = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (no1 < no2) {
                no1 += Integer.parseInt(input[i]);
            } else {
                no2 += Integer.parseInt(input[i]);
            }
        }
        System.out.println(Math.max(no1, no2));
    }
}
