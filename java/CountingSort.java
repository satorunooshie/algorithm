import java.util.Scanner;

/**
 * 配列の走査を末尾側からやれば安定
 *  先頭側から走査をすると同一キー値の順序関係がソート前後で反転するから
 * 度数分布表が必要なのでデータの最小値とデータの最大値があらかじめ分かっている場合にしか適用できない
 */

/**
 * 度数ソート
 * 分布数え上げソートとも呼ばれる
 * 要素の大小関係を判定することなく高速なソートを行うアルゴリズム
 * if文を使わずfor文のみ
 * 1.度数分布表を作成
 *  for (int i = 0; i < n; i++) freq[array[i]]++;
 *      例えば10点満点のテストの学生9人分の点数を考える
 *      点数の配列scores[9]を元に各点数の学生が何人いるかを別の配列freq[11]に格納
 * 2.累積度数分布表を作成
 *  for (int i = 1; i <= max; i++) freq[i] += freq[i - 1];
 *      0点からその点数までに何人の学生がいるかをfreqに上書きする
 * 3.目的配列を作成
 *  int[] tmp = new int[n];
 *      各点数の学生が何番目に位置するのか分かったので
 *      残るは配列scoresの各要素の値と累積度数分布表freqとをつき合わせてソート済みの配列を作るのみ
 *      ただ配列scoresと同じ要素数を持った作業用の配列tmp[9]が必要
 *  for (int i = n - 1; i >= 0; i--) tmp[--freq[array[i]]] = array[i];
 *      配列scoresの要素の末尾要素から走査しながらつき合わせを行う
 *      例えばscores[8](8番目の生徒)が3点でfreq[3](0~3点までの人数)が5人の時、
 *      0点から3点までに5人いるので、tmp[4]に3を格納する
 *          scoresのインデックスは(わかりにくいので暫定的に)生徒の出席番号
 *          freqのインデックスは点数の分布なのでfreq[scores[8]]
 *          freqの値をデクリメントしたものをtmpのインデックスとして入力
 *          配列の5番目はインデックスは4であることに注意
 *          このとき、freq[3]の値を5->4にデクリメント
 *      走査を続けscores[6](6番目の生徒)が3点の時、freq[3](0~3点までの人数)で、3点の学生を格納するのが2回目だった場合、
 *      先ほど(scores[8]の3を目的配列に格納するとき)はfreq[3]の値をデクリメントして5から4にしたので
 *      目的配列の4番目の要素であるtmp[3]に3を格納する
 *
 *      目的配列に値を格納する際に参照した配列tmpの要素の値をデクリメントするのは
 *      同じ値の要素が複数存在するときに格納先が重複しないようにするための配慮
 * 4.配列のコピー
 *  for (int i = 0; i < n; i++) array[i] = tmp[i];
 *      ソートが完了したと言ってもソート結果が格納されたのはtmpなのでtmpの要素をscoresにコピーし直す
 */
class CountingSort {
    static void countingSort(int[] array, int n, int max) {
        //度数分布及び累積度数を格納するための配列
        int[] freq = new int[max + 1];
        //ソートした配列を一時的に格納するための目的配列
        int[] tmp = new int[n];

        for (int i = 0; i < n; i++) freq[array[i]]++;
        for (int i = 1; i <= max; i++) freq[i] += freq[i - 1];
        for (int i = n - 1; i >= 0; i--) tmp[--freq[array[i]]] = array[i];
        for (int i = 0; i < n; i++) array[i] = tmp[i];
    }
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("度数ソート");
        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }

        //度数分布及び累積度数の要素数を決定するために配列xの最大値を求める
        int max = x[0];
        for (int i = 1; i < nx; i++)
            if (x[i] > max) max = x[i];
        countingSort(x, nx, max);
        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}