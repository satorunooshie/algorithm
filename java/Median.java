import java.util.Scanner;

class Median {
    /**
     * QuickSortで応用
     */
    static int median(int a, int b, int c) {
        if (a >= b) {
            if (b >= c) {
                return b;
            } else if (a <= c) {
                return a;
            } else {
                return c;
            }
        } else if (a > c) {
            return a;
        } else if (b > c) {
            return c;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("三つの整数の中央値を求める");
        System.out.print("a:");
        int a = stdIn.nextInt();
        System.out.print("b:");
        int b = stdIn.nextInt();
        System.out.print("c:");
        int c = stdIn.nextInt();
        System.out.println("中央値:" + median(a, b, c));
    }
    /* NG */
    /**
     *  if ((b >= a && c<= a) || (b <= a && c >= a)
     * に着目。ここでb >= aおよびb <= aの判定を裏返した判定（実質的に同一の判定）が、続くelse以降で
     *  else if ((a > b && c < b) || (b <= a && c > b)
     * と行われる。つまり、最初のifが成立しなかった場合、2番目のifでも（実質的に）同じ判定を行っているため、効率が悪くなる
     */
    static int medianNG(int a, int b, int c) {
        if ((b >= a && c <= a) || (b <= a && c >= a))
            return a;
        else if ((a > c && c < b) || (a < b && c > b))
            return b;
        return c;
    }
}