package java;

import java.util.Scanner;

/**
 * The type Int stack tester.
 * NEED: IntStack.class
 */
class IntStackTester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        // max64個プッシュできるスタック
        IntStack s = new IntStack(64);
        while (true) {
            System.out.printf("現在のデータ数: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1)push (2)pop (3)peek (4)dump (5)terminate:");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            int x;
            switch (menu) {
                case 1:
                    System.out.print("データ:");
                    x = stdIn.nextInt();
                    try {
                        s.push(x);
                    } catch (IntStack.OverflowIntStackException e) {
                        System.out.println("スタックが満杯です");
                    }
                    break;
                case 2:
                    try {
                        x = s.pop();
                        System.out.println("ポップしたデータは" + x);
                    } catch (IntStack.EmptyIntStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 3:
                    try {
                        x = s.peek();
                        System.out.println("ピークしたデータは" + x);
                    } catch (IntStack.EmptyIntStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 4:
                    s.dump();
                    break;
            }
        }
    }
}