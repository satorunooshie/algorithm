import java.util.Scanner;

class SumBetween {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("nからmまでの総和を求める");
        System.out.print("n:");
        int n = stdIn.nextInt();
        System.out.print("m:");
        int m = stdIn.nextInt();
        System.out.println(Sum(n, m));
    }

    static int Sum(int n, int m) {
        int sum = 0;
        int max, min;
        max = (n > m) ? n : m;
        min = (max == n) ? m : n;
        for (; min <= max; min++)
            sum += min;
        return sum;
    }
}