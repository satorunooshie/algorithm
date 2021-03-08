import java.util.Scanner;

class SumVerbose {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int n;
        System.out.println("1からnまでの総和を求める");
        do {
            System.out.print("n:");
            n = stdIn.nextInt();
        } while (n <= 0);
        int sum = 0;
        for (int i = 1; i < n; i++) {
            System.out.print(i + "+");
            sum += i;
        }
        /*
        System.out.print(n + "=");
        sum += n;
        System.out.println(sum);
         */
        System.out.println(n + "=" + (sum += n));
    }
}