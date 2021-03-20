package java;

import java.util.Scanner;

/**
 * The type Pyramid.
 */
class Pyramid {
   /**
    * The entry point of application.
    *
    * @param args the input arguments
    */
   public static void main(String[] args) {
      Scanner stdIn = new Scanner(System.in);
      System.out.println("n段のピラミッドを表示");
      int n;
      do {
         System.out.print("n:");
         n = stdIn.nextInt();
      } while(n <= 0);
      pyramid(n);
   }

   /**
    * Pyramid.
    *
    * @param n the n
    */
   static void pyramid(int n) {
      for (int i = 1; i <= n; i++) {
         int space_cnt = (n - i) / 2;
         System.out.print(" ".repeat(space_cnt));
         System.out.print("*".repeat(i));
         System.out.println(" ".repeat(space_cnt));
      }
      System.out.println();
   }
}