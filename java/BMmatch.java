import java.util.Scanner;

class BMmatch {
    static int bmMatch(String txt, String pat) {
        int pt, pp;
        int txtLen = txt.length();
        int patLen = pat.length();
        int[] skip = new int[Character.MAX_VALUE + 1];

        for (pt = 0; pt <= Character.MAX_VALUE; pt++)
            skip[pt] = patLen;
        for (pt = 0; pt < patLen - 1; pt++)
            skip[pat.charAt(pt)] = patLen - pt - 1;

        while (pt < txtLen) {
            pp = patLen - 1;

            while (txt.charAt(pt) == pat.charAt(pp)) {
                if (pp == 0)
                    return pt;
                pp--;
                pt--;
            }
            pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("テキスト：");
        String s1 = stdIn.next();
        System.out.print("パターン：");
        String s2 = stdIn.next();

        int index = bmMatch(s1, s2);
        if (index == -1)
            System.out.println("テキスト中にパターンは存在しません");
        else {
            int len = 0;
            for (int i = 0; i < index; i++)
                len += s1.substring(i, i + 1).getBytes().length;
            len += s2.length();
            System.out.println((index + 1) + "文字目にマッチします");
            System.out.printf(String.format("パターン: %%%ds\n", len), s2);
        }
    }
}