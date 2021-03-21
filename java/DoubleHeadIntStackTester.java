package java;

import java.util.Scanner;

/**
 * The type Double head int stack tester.
 */
public class DoubleHeadIntStackTester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        DoubleHeadIntStack s = new DoubleHeadIntStack(64);

        while (true) {
            System.out.printf("現在のデータ数:\n A: %s\t B: %s\n", s.size(DoubleHeadIntStack.AorB.StackA), s.size(DoubleHeadIntStack.AorB.StackB));
            System.out.print("(1) Aにpush (2) Aからpop (3) Aからpeek (4) Aをdump (5) Aをsearch (6) Aをclear (7) Bにpush (8) Bからpop (9) Bからpeek (10) Bをdump (11) Bからsearch (12) Bをclear (13) show (0) terminate:");

            int menu = stdIn.nextInt();
            if (menu == 0) break;
            int n, x = 0;

            switch (menu) {
                case 1:
                    System.out.print("データ:");
                    x = stdIn.nextInt();
                    try {
                        s.push(DoubleHeadIntStack.AorB.StackA, x);
                    } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
                        System.out.println("スタックが満杯です");
                    }
                    break;
                case 2:
                    try {
                        x = s.pop(DoubleHeadIntStack.AorB.StackA);
                        System.out.println("ポップしたデータは" + x + "です");
                    } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 3:
                    try {
                        x = s.peek(DoubleHeadIntStack.AorB.StackA);
                        System.out.println("ピークしたデータは" + x + "です");
                    } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 4:
                    s.dump(DoubleHeadIntStack.AorB.StackA);
                    break;
                case 5:
                    System.out.print("探索するデータ:");
                    x = stdIn.nextInt();
                    n = s.indexOf(DoubleHeadIntStack.AorB.StackA, x);
                    if (n >= 0)
                        System.out.printf("頂上から%d番目に存在します\n", s.size(DoubleHeadIntStack.AorB.StackA) - n);
                    else
                        System.out.println("そのデータはありません");
                    break;
                case 6:
                    s.clear(DoubleHeadIntStack.AorB.StackA);
                    break;
                case 7:
                    System.out.print("データ:");
                    x = stdIn.nextInt();
                    try {
                        s.push(DoubleHeadIntStack.AorB.StackB, x);
                    } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
                        System.out.println("スタックが満杯です");
                    }
                    break;
                case 8:
                    try {
                        x = s.pop(DoubleHeadIntStack.AorB.StackB);
                        System.out.println("ポップしたデータは" + x + "です");
                    } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 9:
                    try {
                        x = s.peek(DoubleHeadIntStack.AorB.StackB);
                        System.out.println("ピークしたデータは" + x + "です");
                    } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
                        System.out.println("スタックが空です");
                    }
                    break;
                case 10:
                    s.dump(DoubleHeadIntStack.AorB.StackB);
                    break;
                case 11:
                    System.out.print("探索するデータ:");
                    x = stdIn.nextInt();
                    n = s.indexOf(DoubleHeadIntStack.AorB.StackB, x);
                    if (n >= 0)
                        System.out.printf("頂上から%d番目に存在します\n", s.size(DoubleHeadIntStack.AorB.StackB) - n);
                    else
                        System.out.println("そのデータはありません");
                    break;
                case 12:
                    s.clear(DoubleHeadIntStack.AorB.StackB);
                    break;
                case 13:
                    System.out.println("容量:" + s.getCapacity());
                    System.out.println("Aのデータ数:" + s.size(DoubleHeadIntStack.AorB.StackA));
                    System.out.println("Bのデータ数:" + s.size(DoubleHeadIntStack.AorB.StackB));
                    System.out.println("Aは空" + (s.isEmpty(DoubleHeadIntStack.AorB.StackA) ? "です" : "ではありません"));
                    System.out.println("Bは空" + (s.isEmpty(DoubleHeadIntStack.AorB.StackB) ? "です" : "ではありません"));
                    System.out.println("満杯" + ((s.isFull()) ? "です" : "ではありません"));
                    break;
            }
        }
    }
}
