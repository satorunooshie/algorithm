import java.util.Scanner;

class PrintStars {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int n, w;
        System.out.println("記号文字*をw個ごとに改行しながらn個表示");
        System.out.print("n:");
        n = stdIn.nextInt();
        System.out.print("w:");
        w = stdIn.nextInt();
        /**
         * 繰り返しはn
         * if文の判定はn+1
         */
        /*
        for (int i = 0; i < n; i++) {
            System.out.print("*");
            if (i % w == w - 1)
                System.out.println(); //break
        }
        //ちょうど倍数で終わった場合は改行されているが、それ以外の場合は改行が必要
        if (n % w != 0)
            System.out.println();
         */
        /**
         * 繰り返しはn/w
         * if文の判定は1
         */
        for (int i = 0; i < n / w; i++)
            System.out.println("*".repeat(w));
        int rest = n % w;
        if (rest != 0)
            System.out.println("*".repeat(rest));
    }
}