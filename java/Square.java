import java.util.Scanner;

class Square {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("正方形を表示");
        int len;
        do {
            System.out.print("辺の長さ:");
            len = stdIn.nextInt();
        } while (len <= 0);
        for (int i = 0; i < len; i++)
            System.out.println("*".repeat(len));
    }
}