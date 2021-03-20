package java;

/**
 * The type Prime number.
 */
// 1000以下の素数を列挙
class PrimeNumber {
    /*
     * nが素数の時:forは最後まで実行されてiはnと等しい値になる
     * nが合成数の時:forは中断されてiはnよりも小さい値になる
     * 除算を行った回数: 78022
     *
     * 2で割り切れなければ2*3で割る必要はない
     *
     * nが素数であるかどうかは以下の条件を満たす
     *  2からn-1までのいずれの素数でも割り切れない
     */
    // public static void main(String[] args) {
    static void primeNumber1() {
        //除算の回数
        int counter = 0;
        for (int n = 2; n <= 1000; n++) {
            int i;
            for (i = 2; i < n; i++) {
                counter++;
                // 割り切れると素数ではないのでそれ以上は不要
                if (n % i == 0) break;
            }
            // 最後まで割り切れなかった
            if (n == i) System.out.println(n);
        }
        System.out.println("除算を行った回数:" + counter);
    }

    /*
     * 除算を行った回数: 14622
     * 高速なアルゴリズムはより多くの記憶域を必要とする傾向がある
     */
    // public static void main(String[] args) {
    static void primeNumber2() {
        int counter = 0;
        int ptr = 0;
        int[] prime = new int[500];
        prime[ptr++] = 2;

        // 4以上の偶数は2で割り切れるため対象を奇数のみに絞る
        for (int n = 3; n <= 1000; n += 2) {
            int i;
            // iのインクリメントを0ではなく1から始めているのは判定対象となるnが奇数であるため、prime[0]に格納されている2で割る必要がないから
            // 既に得られた素数で割ってみる
            for (i = 1; i < ptr; i++) {
                counter++;
                if (n % prime[i] == 0) break;
            }
            // 最後までわりきれなかったら素数として配列に格納
            if (ptr == i) prime[ptr++] = n;
        }
        // 求めたptr個の素数を表示
        for (int i = 0; i < ptr; i++) System.out.println(prime[i]);
        System.out.println("除算を行った回数:" + counter);
    }

    /*
     * 乗除算を行った回数: 3774
     *
     * nは次の条件を満たせば素数である
     *  nの平方根以下のいずれの整数でも割り切れない
     */
    // public static void main(String[] args) {
    static void primeNumber3() {
        int counter = 0;
        int ptr = 0;
        // 偶数は素数でないことが明らかであり、少なくとも半分を用意しておけば素数は必ず配列に収まるから500
        int[] prime = new int[500];
        prime[ptr++] = 2;
        prime[ptr++] = 3;
        for (int n = 5; n <= 1000; n += 2) {
            boolean flag = false;
            // prime[i]がnの平方根以下であるか確認するために、prime[n]の二乗がn以下であるかを確認している(nの平方根の値を求めるよりも遥かに単純で高速)
            for (int i = 1; prime[i] * prime[i] <= n; i++) {
                // 乗算と除算をカウントするために2
                counter += 2;
                if (n % prime[i] == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                prime[ptr++] = n;
                // for文内のprime[i] * prime[i] <= nが成立しない場合はカウントされないためその分をここでカウントする
                // flagがtrueであった場合はカウント済みなのでnが素数であった場合のみカウントアップ
                counter++;
            }
        }
        for (int i = 0; i < ptr; i++) System.out.println(prime[i]);
        System.out.println("乗除算を行った回数:" + counter);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        primeNumber1();
        primeNumber2();
        primeNumber3();
    }
}