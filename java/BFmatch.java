import java.util.Scanner;

class BFmatch {
    static int bfMatch(String txt, String pat) {
        int pt = 0;
        int pp = 0;

        while (pt != txt.length() && pp != pat.length()) {
            if (txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else {
                pt = pt - pp + 1;
                pp = 0;
            }
        }
        if (pp == pat.length())
            return pt - pp;
        return -1;
    }
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("テキスト:");
        String s1 = stdIn.next();
        System.out.print("パターン:");
        String s2 = stdIn.next();

        int index = bfMatch(s1, s2);
        if (index == -1)
            System.out.println("テキスト中にパターンは存在しません");
        else {
            int len = 0;
            for (int i = 0; i < index; i++)
                len += s1.substring(i, i + 1).getBytes().length;
            len += s2.length();

            System.out.println((index + 1) + "文字目にマッチします");
            System.out.println("テキスト:" + s1);
            System.out.printf(String.format("パターン:%%%ds\n", len), s2);
        }
    }
}