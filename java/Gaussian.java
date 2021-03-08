import java.util.Scanner;

class Gaussian {
    public static void main(String[] args) {
       Scanner stdIn = new Scanner(System.in);
       System.out.println("1からnまでの総和を求める");
       System.out.print("n:");
       int n = stdIn.nextInt();
       System.out.println("総和は" + sum(n));
    }
    static int sum(int n) {
        if (n < 0) return 0;
        //int sum = (1 + n) * (n / 2) + (n % 2 == 1 ? (n + 1) / 2 : 0);
        int sum = (1 + n) * n / 2;
        return sum;
    }
}