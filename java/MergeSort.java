import java.util.Scanner;

/**
 * O(n log n)
 * 安定
 */

/**
 * マージソート
 * ソート済み配列のマージを応用して分割統治法でソートを行う
 *
 * 前半部と後半部のそれぞれをソートすればそれらをマージするだけで配列全体がソートできる
 * ・配列の前半部分をマージソートによってソートする
 * ・配列後半部分をマージソートによってソートする
 * ・配列の前半部と後半部をマージする
 */
class MergeSort {
    //作業用配列
    static int[] buff;
    /**
     * array[left] ~ array[right]を再帰的にマージソート
     * (配列前半部と後半部をそれぞれソート済みにする)(,left,center)(,center+1,right)
     * @param array ソートする配列
     * @param left  ソート対象の先頭要素のインデックス
     * @param right ソート対象の末尾要素のインデックス
     */
    static void __mergeSort(int[] array, int left, int right) {
        if (left > right) {
            int center = (left + right) / 2;
            int i, p, j;
            p = j = 0;
            int k = left;
            //前半部
            __mergeSort(array, left, center);
            //後半部
            __mergeSort(array, center + 1, right);
            /**
             * 1.配列の前半部array[left] ~ array[center]をbuff[0] ~ buff[center - left]にコピー
             *   for終了時のpはコピーした要素の個数center - left + 1となる
             * 2.配列後半部array[center + 1] ~ array[right]とbuffにコピーした配列の前半部のp個をマージした結果をarrayに格納
             * 3.配列buffに残った未格納部分の要素を配列arrayにコピー
             *
             * つまりbuffにはソートされた前半部、マージ前の配列arrayは、ソートされた後半部が入っていてそれをarrayでマージ
             */
            //1
            for (i = left; i <= center; i++)
                buff[p++] = array[i];
            //2
            while (i <= right && j < p)
                array[k++] = (buff[j] <= array[i]) ? buff[j++] : array[i++];
            //3
            while (j < p)
                array[k++] = buff[j++];
        }
    }

    /**
     * マージ結果を一時的に格納するための作業用配列buffを生成する
     * ソート作業を行うメソッド__mergeSortを呼び出す
     * 作業用配列を解放・破棄する
     *
     */
    static void mergeSort(int[] array, int n) {
        //作業用配列の作成
        buff = new int[n];
        //配列全体をマージソート
        __mergeSort(array, 0, n - 1);
        //作業用配列を開放
        buff = null;
    }
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("マージソート");
        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        mergeSort(x, nx);
        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}