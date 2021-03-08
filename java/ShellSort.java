import java.util.Scanner;

/**
 * O(n^1.25)
 * 離れた要素を交換するため安定ではない
 */

/**
 * 単純挿入ソートには
 * ・ソート済みかそれに近い状態では高速
 * ・挿入先が遠く離れている場合は移動回数が多くなる
 * という特徴があった
 * シェルソートでは
 * まず最初に離れた要素をグループ化して大まかなソートを行い、
 * そのグループを縮小しながらソートを繰り返すことによって
 * 移動回数を減らすというアルゴリズム
 * ソートの回数は増えても全体としての要素の移動回数を少なくすることが目的
 */
class ShellSort {
    /**
     * h = 4 -> 2 -> 1
     */
    static void shellSort1(int[] array, int n) {
        for (int h = n / 2; h > 0; h /= 2)
            //ここから単純挿入ソートと同じ
            for (int i = h; i < n; i++) {
                int j;
                int tmp = array[i];
                for (j = i - h; j >= 0 && array[j] > tmp; j -= h)
                    array[j + h] = array[j];
                array[j + h] = tmp;
            }
    }

    /**
     * 同じ要素で構成されるグループばかりをソートしているため、
     * グループ分けが十分に機能していなかった
     * hの値が互いに倍数を取らないようにすれば、要素が十分にかき混ぜられて
     * 良い結果が得られるはず
     * h = ... -> 121 -> 40 -> 13 -> 4 -> 1
     * 1から初めて3倍した値に1を加える数列
     */
    static void shellSort2(int[] array, int n) {
        int h;
        for (h = 1; h < n; h = h * 3 + 1)
            ;
        for (; h > 0; h /= 3)
            for (int i = h; i < n; i++) {
                int j;
                int tmp = array[i];
                for (j = i - h; j >= 0 && array[j] > tmp; j -= h)
                    array[j + h] = array[j];
                array[j + h] = tmp;
            }
    }
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("シェルソート");
        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        //shellSort1(x, nx);
        shellSort2(x, nx);
        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}