import java.util.Scanner;

class Difference {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("b-a(a<b)を求める");
        System.out.print("a:");
        int a = stdIn.nextInt();
        System.out.print("b:");
        int b;
        while (true) {
            b = stdIn.nextInt();
            if (a < b) break;
            System.out.println("aより大きな数を入力せよ");
            System.out.print("b:");
        }
        System.out.println(b-a);
    }
}