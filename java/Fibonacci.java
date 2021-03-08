import java.util.Scanner;

public class Fibonacci {
    private static Scanner stdIn = new Scanner(System.in);
    /**
     * 再帰によるフィボナッチ
     */
    public static int fibonacciRecur(int n) {
        return switch (n) {
            case 0 -> 0;
            case 1 -> 1;
            default -> fibonacciRecur(n - 1) + fibonacciRecur(n - 2);
        };
    }

    /**
     * 動的計画法によるフィボナッチ
     * 再帰によるフィボナッチは再帰呼び出しで階乗を返す関数の処理には無駄がないが、何度も同じ引数で階乗を呼び出すので計算に無駄がある
     * 動的計画法は分割された問題の答えを記憶しておきそれを再利用する
     */
    private static int[] fibonacciNumbers = new int[100];
    public static int fibonacciDP(int n) {
        int i;
        for (i = 0; i <= n; i++) {
            fibonacciNumbers[i] = switch (i) {
                case 0 -> 0;
                case 1 -> 1;
                default -> fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
            };
        }
        return fibonacciNumbers[n];
    }

    /**
     * 再帰と動的計画法を組み合わせて効率化する
     * fibonacciNumbersを-1で初期化することで
     * 同じ引数で何度もfibonacci関数を呼び出す無駄を避けることができる
     * つまり、動的計画法を使って再帰呼び出しの無駄を避ける
     */
    public static void initFibonacciNumbers() {
        for (int i = 0; i < fibonacciNumbers.length; i++)
            fibonacciNumbers[i] = -1;
    }
    public static int fibonacci(int n) {
        int i = 0;
        System.out.printf("fibonacci(%d)が呼ばれました\n", n);
        if (fibonacciNumbers[n] == -1) {
            fibonacciNumbers[n] = switch (n) {
                case 0 -> 0;
                case 1 -> 1;
                default -> fibonacci(n - 1) + fibonacci(n - 2);
            };
        }
        return fibonacciNumbers[n];
    }
    public static void main(String[] args) {
        System.out.print("nまでのフィボナッチ数を求める:");
        int n = stdIn.nextInt();
        System.out.println("Recur");
        for (int i = 0; i <= n; i++) {
            System.out.printf("%d, ", fibonacciRecur(i));
        }
        System.out.println();
        System.out.println("DP");
        for (int i = 0; i <= n; i++) {
            System.out.printf("%d, ", fibonacciDP(i));
        }
        System.out.println();
        System.out.println("Hybrid");
        initFibonacciNumbers();
        System.out.printf("%d番目のフィボナッチ数 = %d", n, fibonacci(n));
    }
}