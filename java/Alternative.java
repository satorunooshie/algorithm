import java.util.Scanner;

class Alternative {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int n;
        System.out.println("記号文字+-を交互にn個表示します");
        do {
            n = stdIn.nextInt();
        } while (n <= 0);
        /**
         * 繰り返しはn
         * 除算はn
         * if文の判定はn
         */
        /*
        for (int i = 0; i < n; i++)
            if (i % 2 == 0)
                System.out.print("+");
            else
                System.out.print("-");
         */
        /**
         * 繰り返しはn / 2
         * 除算は2
         * if文の判定は1
         */
        for (int i = 0; i < n / 2; i++)
            System.out.print("+-");
        if (n % 2 != 0)
            System.out.print("+");
    }
}