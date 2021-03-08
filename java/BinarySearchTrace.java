import java.util.Scanner;

public class BinarySearchTrace {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int[] array = { 39, 41, 56, 43, 53, 75, 68, 84, 99, 77, 88, 97, 92 };
        int x, pos, left, right, middle;

        System.out.print("x = ");
        x = stdIn.nextInt();
        pos = -1;
        left = 0;
        right = array.length - 1;
        System.out.printf("ループの前: x = %d\n", x);
        System.out.printf("ループの前: pos = %d, \tleft = %d, \tmiddle = ?, \tright = %d\n", pos, left, right);
        while (pos == -1 && left <= right) {
            middle = (left + right) / 2;
            if (array[middle] == x)
                pos = middle;
            else if (array[middle] > x)
                right = middle - 1;
            else
                left = middle + 1;
            System.out.printf("ループの中: pos = %d, \tleft = %d, \tmiddle = %d, \tright = %d\n", pos, left, middle, right);
        }
        System.out.printf("pos = %d\n", pos);
    }
}