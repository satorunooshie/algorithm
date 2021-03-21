package java;

import java.util.Random;
import java.util.Scanner;

/**
 * The type Max of array rand.
 */
class MaxOfArrayRand {
    /**
     * Max int.
     *
     * @param a the a
     * @return the int
     */
    static int max(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > max) max = a[i];
        return max;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner stdIn = new Scanner(System.in);
        System.out.println("身長の最大値を求める");
        System.out.print("人数は");
        int num = stdIn.nextInt();
        int[] height = new int[num];
        System.out.println("身長は次のようになっている");
        for (int i = 0; i < num; i++) {
            //0~n-1以下のランダムを返す
            height[i] = 100 + rand.nextInt(90);
            System.out.println(height[i]);
        }
        System.out.println("最大値は" + max(height));
    }
}