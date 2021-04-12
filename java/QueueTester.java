package java;

import java.util.Scanner;

/**
 * The type Queue tester.
 */
public class QueueTester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Queue<String> s = new Queue<>(64);

        while (true) {
            System.out.printf("現在のデータ数: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1) enque (2) deque (3) peek (4) dump (5) search (0) terminate:");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            int index;
            String x;

            switch (menu) {
                case 1:
                    System.out.print("データ:");
                    x = stdIn.next();
                    try {
                        s.enque(x);
                    } catch (Queue.OverflowQueueException e) {
                        System.out.println("キューが満杯です");
                    }
                    break;
                case 2:
                    try {
                        x = s.deque();
                        System.out.printf("デキューしたデータは%sです\n", x);
                    } catch (Queue.EmptyQueueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 3:
                    try {
                        x = s.peek();
                        System.out.printf("ピークしたデータは%sです\n", x);
                    } catch (Queue.EmptyQueueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 4:
                    s.dump();
                    break;
                case 5:
                    System.out.print("データ:");
                    String str = stdIn.next();
                    int n = s.search(str);
                    if (n != 0)
                        System.out.printf("%d番目のデータでインデックス%dの位置に格納されています\n", n, s.indexOf(str));
                    else
                        System.out.println("そのデータは登録されていません");
                    break;
            }
        }
    }
}
