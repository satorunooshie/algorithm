import java.util.Scanner;

/**
 * クイックソート
 * 配列から任意の一つの要素を取り出して、
 * その要素以上のグループと、その要素以下のグループに分ける
 * グループ分けの基準を枢軸という。
 * 各グループに対して枢軸を設定して分割を繰り返していき、全てのグループの要素数が1になるとソートが終了
 *
 * ここでは分割する手順を実装する
 * 分割を行うには枢軸以下の要素を配列の左側に、
 * 枢軸以上の要素を配列の右側に移動させる必要がある。
 * ・array[pl] >= xが成立するまでplを右方向へ操作
 * ・array[pr] <= xが成立するまでprを右方向へ操作
 * そうするとカーソルが交差するので以下のようにグループ分けできる
 * 枢軸以下のグループ:array[0], ..., array[pl - 1]
 * 枢軸以上のグループ:array[pr + 1], ..., array[n - 1]
 * 但し、pl > pr + 1の時のみ
 * array[pr + 1], ..., array[pl - 1]ができる
 *
 * 枢軸の選択は分割とソートのパフォーマンスに影響するが、とりあえずは配列の中央要素とする
 * plとprの両方が同一要素上に位置していた時、同一要素を交換することになる
 * しかし、最大で一回しか行われず、同一要素の交換を回避するのであれば
 * 要素の交換を行おうとするたびにplとprが同じ要素上にあるかどうかをチェックする必要があり、
 * そのチェックを毎回行う方が最大で一回しか行われない同一要素の交換を行うよりもコストが小さい
 * その結果中央要素ができる
 */
class Partition {
    static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    static void partition(int[] array, int n) {
        //左カーソル
        int pl = 0;
        //右カーソル
        int pr = n -1;
        //枢軸はとりあえず配列の中央要素
        int x = array[n / 2];
        //配列を枢軸で分割
        do {
            while(array[pl] < x) pl++;
            while(array[pr] > x) pl--;
            if (pl <= pr)
                swap(array, pl++, pr--);
        } while (pl <= pr);

        System.out.println("枢軸の値は" + x);
        //array[0] ~ array[pl - 1]
        System.out.println("枢軸以下のグループ");
        for (int i = 0; i <= pl - 1; i++)
            System.out.print(array[i] + " ");
        System.out.println();

        if (pl > pr + 1) {
            //array[pr + 1] ~ array[pl - 1]
            System.out.println("枢軸と一致するグループ");
            for (int i = pr + 1; i <= pl - 1; i++)
                System.out.print(array[i] + " ");
            System.out.println();
        }

        //array[pr + 1] ~ array[n - 1]
        System.out.println("枢軸以下のグループ");
        for (int i = pr + 1; i < n; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("配列を分割します");
        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        partition(x, nx);
    }
}