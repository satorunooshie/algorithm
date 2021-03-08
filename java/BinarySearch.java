import java.util.Scanner;
import java.util.Arrays;

class BinarySearch {
    /**
     * 繰り返しのたびに探索範囲がほぼ半分になるので要素の比較回数の平均はlog(n)
     * 探索失敗時はceiling(log(n+1))となり、探索成功時はlog(n-1)となる
     * ceilingは天井関数で、x以上の最小の整数を表す
     * @param a
     * @param n
     * @param key
     * @return
     */
    static int binarySearch(int a[], int n, int key) {
        int pl = 0;
        int pr = n - 1;
        do {
            //center
            int pc = (pl + pr) / 2;
            if (a[pc] == key)
                return pc;
            //後半に絞る
            else if (a[pc] < key)
                pl = pc + 1;
            //前半に絞る
            else
                pr = pc - 1;
        } while (pl <= pr);
        return -1;
    }

    //public static void main(String[] args) {
    static void binarySearchTest1() {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("要素数:");
        int num = stdIn.nextInt();
        int[] x = new int[num];
        System.out.println("昇順に入力してください");
        System.out.print("x[0]:");
        x[0] = stdIn.nextInt();
        for (int i = 1; i < num; i++) {
            do {
                System.out.print("x[" + i + "]:");
                x[i] = stdIn.nextInt();
            } while (x[i] < x[i - 1]);
        }
        System.out.print("探す値:");
        int key = stdIn.nextInt();
        int index = binarySearch(x, num, key);
        if (index == -1)
            System.out.println("その値の要素は存在しません");
        else
            System.out.println("その値はx[" + index + "]にあります");
    }
    /**
     * O(f(n))+O(g(n))=O(max(f(n),g(n)))
     * --FYI--
     * 1<log(n)<n<(n)log(n)<n^2<n^3<n^k<2^n
     */
    /**
     * java.util.Arraysクラス
     * 二分探索メソッドの自作が不要
     * あらゆる要素方の配列からの探索を行える
     * 昇順にソート済みでない場合の結果は定義されない
     * --成功した場合--
     * keyと一致する要素のインデックスを返すが、一致する要素が複数存在する場合ランダム
     * --失敗した場合--
     * keyが入るべき位置を示唆する値が返却される
     * 挿入ポイントをxとすると返却されるのは-x-1
     * 挿入ポイントはソートされた状態を維持するようにkeyが挿入できる位置のインデックスで
     * keyより大きな要素の中で最も先頭の要素のインデックス
     * 但し、配列の前要素がkeyより小さければ、配列の要素数となる
     * ex)
     *      key=31
     * {5,7,15,28,29,32,39,58,59,89}
     * この場合は-6を返す
     *      key=97
     * この場合は-11を返す
     * static int binarySearch(byte[] a, byte key)
     * static int binarySearch(char[] a, char key)
     * static int binarySearch(double[] a, double key)
     * static int binarySearch(float[] a, float key)
     * static int binarySearch(int[] a, int key)
     * static int binarySearch(long[] a, long key)
     * static int binarySearch(short[] a, short key)
     * static int binarySearch(Object[] a, Object key)
     * static <T> int binarySearch(T[] a, T key, Comperator<? super T> c)
     */
    //public static void main(String[] args) {
    static void binarySearchTest2() {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("要素数:");
        int num = stdIn.nextInt();
        int[] x = new int[num];
        System.out.println("昇順に入力してください");
        System.out.print("x[0]:");
        x[0] = stdIn.nextInt();
        for (int i = 1; i < num; i++) {
            do {
                System.out.print("x[" + i + "]:");
                x[i] = stdIn.nextInt();
            } while (x[i] < x[i - 1]);
        }
        System.out.print("探す値:");
        int key = stdIn.nextInt();
        int index = Arrays.binarySearch(x, key);
        if (index < 0)
            System.out.println("その値の要素は存在しません");
        else
            System.out.println("その値はx[" + index + "]にあります");
    }

    /**
     * binarySearchメソッドが受け取る配列の要素型はObjectで
     * クラス方変数は同一型のインスタンスだけでなく、下位クラス型のインスタンスも参照できることになっているので
     * 全ての最上位クラスであるObject型の引数はあらゆるクラス型のインスタンスを受け取れる
     *
     * 配列とキー値を渡すだけで探索を行えるのはStringクラスがComperable<T>インターフェースを実装するとともに
     * compareToメソッドを実装しているから
     * 自然な順序付けを定義する必要がある場合は以下の形式で宣言する
     * class A implements Comparable<A> {
     *     public int compareTo(A c) {
     *         //thisがcより大きければ生の値を
     *         //thisがcより小さければ負の値を
     *         //thisがcと等しければ0を返す
     *     }
     *     public boolean equals(Object c) {
     *         //thisがcと等しければtrueを
     *         //thisがcと等しくなければfalseを返す
     *     }
     * }
     */
    //public static void main(String[] args) {
    static void binarySearchTest3() {
        Scanner stdIn = new Scanner(System.in);
        String[] x = {
                "abstract", "assert", "boolean", "break", "byte",
                "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else",
                "enum", "extends", "final", "finally", "float",
                "for", "goto", "if", "implements", "import",
                "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while"
        };
        System.out.print("何を探しますか:");
        String key = stdIn.next();
        int index = Arrays.binarySearch(x, key);
        if (index < 0)
            System.out.println("その値の要素は存在しません");
        else
            System.out.println("その値はx[" + index + "]にあります");
    }
    public static void main(String[] args) {
        binarySearchTest1();
        binarySearchTest2();
        binarySearchTest3();
    }
}