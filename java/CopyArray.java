import java.util.Scanner;

class CopyArray {

    public static void printArray(int[] array, String arrayName) {
        System.out.print(arrayName + " = ");
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5 };
        int[] b = { 6, 5, 4, 3, 2, 1, 0 };

        printArray(a, "a");
        printArray(b, "b");

        /**
         * これはaの参照先をbに代入するため、参照先のコピーになる。
         * つまり代入の結果配列変数bが配列aの本体を参照することになってしまう
         * 代入演算子によって配列を代入しても全要素がコピーされるわけではなく、参照先が変更されるだけ
         *
         * ちなみに投下演算子の==と!=も演算の対象が本体ではなく、配列変数であるという点で同等なので
         * a == bは配列aとbの全要素が等しいかどうかを判断するのではなく、
         * 配列変数が同じ配列本体を参照しているかどうかを調べる
         */
        b = a;
        a[0] = 10;
        System.out.println("aをbに代入しました");
        printArray(a, "a");
        printArray(b, "b");

        Scanner stdIn = new Scanner(System.in);
        System.out.print("要素数:");
        int n = stdIn.nextInt();
        a = b = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("a[" + i + "] = ");
            a[i] = stdIn.nextInt();
        }
        /**
         * 要素数が異なる場合はコピー先の要素数をコピー元の要素数と同じにした上で全要素の値をコピーする必要がある
         * if (a.length != b.length) b = new int[a.length];
         * for (int i = 0; i < a.length; i++) b[i] = a[i];
         */
        // 配列全要素をコピー
        for (int i = 0; i < n; i++)
            b[i] = a[i];
        System.out.println("aの全要素をbにコピーしました");
        for (int i = 0; i < n; i++)
            System.out.println("b[" + i + "] = " + b[i]);
    }
}