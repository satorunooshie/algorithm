package java;

import java.util.Scanner;

/**
 * The type Int queue tester.
 */
class IntQueueTester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        IntQueue s = new IntQueue(64);
        while (true) {
            System.out.printf("現在のデータ数: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1) enque (2) deque (3) peek (4) dump (5) search (0) terminate:");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            int x;
            switch (menu) {
                case 1:
                    System.out.print("データ:");
                    x = stdIn.nextInt();
                    try {
                        s.enque(x);
                    } catch (IntQueue.OverflowIntQueueException e) {
                        System.out.println("キューが満杯です");
                    }
                    break;
                case 2:
                    try {
                        s.deque();
                    } catch (IntQueue.EmptyIntQueueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 3:
                    try {
                        x = s.peek();
                        System.out.println("ピークしたデータは" + x);
                    } catch (IntQueue.EmptyIntQueueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 4:
                    s.dump();
                    break;
                case 5:
                    System.out.print("データ:");
                    x = stdIn.nextInt();
                    int n = s.search(x);
                    if (n != 0)
                        System.out.printf("%d番目のデータでインデックス%dの位置に格納されています\n", n, s.indexOf(x));
                    else
                        System.out.println("そのデータは登録されていません");
                    break;
            }
        }
    }
}