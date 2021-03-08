import java.util.Scanner;

class DigitsNo {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("何桁の整数か判定");
        /*
        int n;
        do {
        System.out.println("整数を入力してください");
            n = stdIn.nextInt();
        } while (n <= 0);

        int cnt = 0;
        while (n > 0) {
            n /= 10;
            cnt++;
        }
         */
        System.out.println("整数を入力してください");
        int n = stdIn.nextInt();
        System.out.println(countDigits(n));
    }
    static int countDigits(int n) {
        int cnt = 0;
        while (true) {
            if (n <= 0) break;
            n /= 10;
            cnt++;
        }
        return cnt;
    }
}