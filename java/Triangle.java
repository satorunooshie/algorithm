import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        /*
        Scanner stdIn = new Scanner(System.in);
        int n;
        System.out.println("左下直角の二等辺三角形を表示");
        do {
            System.out.print("短辺の長さ:");
            n = stdIn.nextInt();
        } while (n <= 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
         */
        Scanner stdIn = new Scanner(System.in);
        int n;
        System.out.println("左下直角の二等辺三角形を表示");
        do {
            System.out.print("短辺の長さ:");
            n = stdIn.nextInt();
        } while (n <= 0);
        System.out.println("左下側が直角の二等辺三角形");
        triangleLB(n);
        System.out.println("左上側が直角の二等辺三角形");
        triangleLU(n);
        System.out.println("右上側が直角の二等辺三角形");
        triangleRU(n);
        System.out.println("右下側が直角の二等辺三角形");
        triangleRB(n);
    }
    //左下側が直角の二等辺三角形
    static void triangleLB(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
    }
    //左上側が直角の二等辺三角形
    static void triangleLU(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = n; j >= i; j--)
                System.out.print("*");
            System.out.println();
        }
    }
    //右上側が直角の二等辺三角形
    static void triangleRU(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++)
                System.out.print(" ");
            for (int j = 1; j <= n - i + 1; j++)
                System.out.print("*");
            System.out.println();
        }
    }
    //右下側が直角の二等辺三角形
    static void triangleRB(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++)
                System.out.print(" ");
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
    }
}