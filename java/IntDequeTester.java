package java;

import java.util.Scanner;

/**
 * The type Int deque tester.
 */
public class IntDequeTester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        IntDeque s = new IntDeque(64);

        while (true) {
            System.out.printf("現在のデータ数: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1) 先頭にenque (2) 先頭からdeque (3) 先頭からpeek (4) 末尾にenque (5) 末尾からdeque (6)末尾からpeek (7) dump (8) search (0) terminate:");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            int x;

            switch (menu) {
                case 1:
                    System.out.print("データ:");
                    x = stdIn.nextInt();
                    try {
                        s.enqueFront(x);
                    } catch (IntDeque.OverflowIntDequeueException e) {
                        System.out.println("キューが満杯です");
                    }
                    break;
                case 2:
                    try {
                        x = s.dequeFront();
                        System.out.printf("デキューしたデータは%dです\n", x);
                    } catch (IntDeque.EmptyIntDequeueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 3:
                    try {
                        x = s.peekFront();
                        System.out.printf("ピークしたデータは%dです\n", x);
                    } catch (IntDeque.EmptyIntDequeueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 4:
                    System.out.print("データ:");
                    x = stdIn.nextInt();
                    try {
                        s.enqueRear(x);
                    } catch (IntDeque.OverflowIntDequeueException e) {
                        System.out.println("キューが満杯です");
                    }
                    break;
                case 5:
                    try {
                        x = s.dequeRear();
                        System.out.printf("デキューしたデータは%dです\n", x);
                    } catch (IntDeque.EmptyIntDequeueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 6:
                    try {
                        x = s.peekRear();
                        System.out.printf("ピークしたデータは%dです\n", x);
                    } catch (IntDeque.EmptyIntDequeueException e) {
                        System.out.println("キューが空です");
                    }
                    break;
                case 7:
                    s.dump();
                    break;
                case 8:
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
