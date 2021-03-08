import java.util.Scanner;

class LastNElements {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        final int N = 10;
        int[] a = new int[N];
        int cnt = 0;
        int retry;
        System.out.println("整数を入力してください");
        do {
            System.out.printf("%d個目の整数:", cnt + 1);
            a[cnt++ % N] = stdIn.nextInt();

            System.out.print("続けますか(1:Yes/0:No)");
            retry = stdIn.nextInt();
        } while (retry == 1);
        int i = cnt - N;
        if (i < 0) i = 0;
        for (; i < cnt; i++)
            System.out.printf("%2d個目=%d\n", i + 1, a[i % N]);
    }
}