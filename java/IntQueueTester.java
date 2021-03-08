import java.util.Scanner;

class IntQueueTester {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        IntQueue s = new IntQueue(64);
        while (true) {
            System.out.printf("現在のデータ数: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1)Enque (2)Deque (3)peek (4)dump (5)terminate:");

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
            }
        }
    }
}