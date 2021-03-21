package java;

import java.util.Scanner;

/**
 * The type Stack tester.
 */
public class StackTester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Stack<String> s = new Stack<>(64);

        while (true) {
            System.out.printf("現在のデータ数: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1) push (2) pop (3) peek (4) dump (5) search (6) clear (7) show (0) terminate:");
            int menu = stdIn.nextInt();
            if (menu == 0) break;
            String x;
            switch (menu) {
                case 1:
                    System.out.print("データ:");
                    x = stdIn.next();
                    try {
                        s.push(x);
                    } catch (Stack.OverflowStackException e) {
                        System.out.println("スタックが満杯です");
                    }
                    break;
                case 2:
                    try {
                        x = s.pop();
                        System.out.printf("ポップしたデータは%sです\n", x);
                    } catch (Stack.EmptyStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 3:
                    try {
                        x = s.peek();
                        System.out.printf("ピークしたデータは%sです\n", x);
                    } catch (Stack.EmptyStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 4:
                    s.dump();
                    break;
                case 5:
                    System.out.print("探索するデータ:");
                    x = stdIn.next();
                    int n = s.indexOf(x);
                    if (n >= 0)
                        System.out.printf("頂上から%d番目に存在します", s.size() - n);
                    else
                        System.out.println("そのデータはありません");
                    break;
                case 6:
                    s.clear();
                    break;
                case 7:
                    System.out.println("容量:" + s.getCapacity());
                    System.out.println("データ数" + s.size());
                    System.out.println("空" + (s.isEmpty() ? "です" : "ではありません"));
                    System.out.println("満杯" + (s.isFull() ? "です" : "ではありません"));
                    break;
            }
        }
    }
}
