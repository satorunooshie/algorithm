import java.util.Scanner;
import java.util.Random;

class Array2D {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner stdIn = new Scanner(System.in);
        System.out.print("行数:");
        int h = stdIn.nextInt();
        System.out.print("列数:");
        int w = stdIn.nextInt();

        int[][] x = new int[h][w];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++) {
                x[i][j] = rand.nextInt(100);
                System.out.println("x[" + i + "][" + j + "] = " + x[i][j]);
            }
    }
}
class Matrix {
    public static void printMatrix(int[][] array, String arrayName) {
        System.out.println("行列" + arrayName);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++)
                System.out.printf("%3d", array[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Array2D.main(args);
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{6, 3, 4}, {5, 1, 2}};
        int[][] c = {{0, 0, 0}, {0, 0, 0}};
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                c[i][j] = a[i][j] + b[i][j];
        printMatrix(a, "a");
        printMatrix(b, "b");
        printMatrix(c, "c");
    }
}