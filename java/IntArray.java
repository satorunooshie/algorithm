import java.util.Scanner;


class IntArray {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int[] a = new int[10];
        a[0] = 33;
        a[1] = a[0] * 2;
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);

        int[] b = {1, 3, 5, 7, 9};
        for (int i = 0; i < b.length; i++)
            System.out.println(b[i]);

        System.out.println("身長の最大値を求める");
        System.out.print("人数は");
        int num = stdIn.nextInt();
        int[] height = new int[num];
        for (int i = 0; i < num; i++) {
            System.out.print("身長:");
            height[i] = stdIn.nextInt();
        }
        System.out.println("最大値は" + max(height));
    }
    static int max(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > max) max = a[i];
        return max;
    }
}