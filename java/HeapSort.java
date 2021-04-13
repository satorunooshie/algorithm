package java;

import java.util.Scanner;

/*
 * 最大要素選択時間計算量
 *    O(log n)
 * ソート全体に要する時間計算量
 *    O(n log n)
 * (補足)
 *    単純選択ソートにおける最大要素選択時間計算量
 *       O(n)
 *    単純選択ソートにおけるソート全体にかかる時間計算量
 *       O(n^2)
 *    単純選択ソートでは未ソート部の全要素を対象として最大値を選択するが
 *    ヒープソートでは先頭要素を取り出すだけで最大値が求められる(残った要素の再ヒープ化は必須)
 *    (根を適切な位置までおろしていくのは2分探索と似ていて、走査のたびに選択の幅が半分になるため)
 */

/*
 * ヒープソート
 * 親の値が子の値以上であるという条件を満たす完全2分木のヒープを用いてソートを行う
 * そのため、ヒープの最上流に位置する根は最大値
 * (一貫していれば、大小関係は逆でも良い)
 * なお、兄弟の大小関係は任意なので、半順序木(partial ordered tree)とも呼ばれる
 *
 * ヒープ上の要素は根から下流に降って要素を左から右へなぞって配列に格納していく
 * そのため任意の要素array[i]に対して
 * ・親       array[(i - 1) / 2] *剰余切り捨て
 * ・左の子 　 array[i * 2 + 1]
 * ・右の子  　array[i * 2 + 2]
 *
 * ヒープソートは最大値が根に位置することを利用してソートする
 *    ・ヒープから最大値である根を取り出す
 *    ・根以外の部分をヒープする
 * この過程で取り出した値を並べていけば、ソート済みの配列が完成する
 * すなわち、選択ソートの応用的なアルゴリズム
 *
 * 但し、ヒープから最大値である根を取り出した後は、残った要素から再び最大値を求める必要がある
 *    例えば、ヒープとなっている10個の要素から最大値を取り除くと、
 *    残り9個の要素から最大値を求めて、9個の要素から構成される木もヒープとなるようにしなければならない
 *
 * --根を削除したヒープの再構築
 *    1.根を取り出す
 *    2.最後の要素(最下流の最も右側に位置する要素)を根に移動する
 *    3.自分より大きい方の子と交換して、一つ下流に降りる作業を根から始めて、次の条件のいずれかが成立するまで繰り返す
 *       ・子の方が小さい
 *       ・葉に到達した
 *
 * --ヒープソートへの拡大
 *    1.変数iの値をn - 1で初期化する(nは要素数)
 *    2.array[0]とarray[i]を交換する
 *    3.array[0], array[1], array[i - 1]をヒープ化する
 *    4.iの値をデクリメントして0になれば終了し、そうでなければ2に戻る
 * *但しこれは配列の初期状態がヒープの要件を満たしているという保証がないので、
 *  これを適用する前に配列をヒープ化する必要がある
 *
 * ヒープになっていない場合は根を削除した時と同じように、最後の要素を根に移動させて、それを適切な位置まで降ろす同じ手順が使える
 * すなわち下流側の小さい部分木からボトムアップ的に積み上げればいい
 */
/**
 * The type Heap sort.
 */
class HeapSort {

   /**
    * Swap.
    *
    * @param array  the array
    * @param index1 the index 1
    * @param index2 the index 2
    */
   static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

   /**
    * Down heap.
    *
    * @param array             the array
    * @param last_parent_index the last parent index
    * @param last_index        the last index
    */
    // last_parentはデクリメントされていく配列の末尾の子の親(swapされる可能性がある)
    static void downHeap(int[] array, int last_parent_index, int last_index) {
        //スワップされる可能性のある親/根
        int temp = array[last_parent_index];
        int large_child_index, parent_index;
        /*
         * NOTE: それぞれインデックス番号が入っていることに注意 tempのみ実数
         */
        // parent < (last_index + 1) / 2は少なくとも子が一人はいる親であるかを確認している
        // parent_index = large_child_indexはswapできるところまでswapする
        for (parent_index = last_parent_index; parent_index < (last_index + 1) / 2; parent_index = large_child_index) {
            // 以前のポインタ2ずつデクリメントされていく(木なので)
            int left_child_index = parent_index * 2 + 1;
            int right_child_index = left_child_index + 1;
            // 右の子のインデックスとn - 1(配列の長さ/要素数-1)が比較されているので右の子の存在チェック(上で左の子は確認したので)
            large_child_index = (right_child_index <= last_index && array[right_child_index] > array[left_child_index]) ? right_child_index : left_child_index;
            // 親子を確認して問題がなければ、変更不要なので、forを抜けてheapSort()のfor文へ
            if (temp >= array[large_child_index])
                break;
            // 2回目(再ヒープ化する際、swapされた末端要素を下ろして大きい子(親)の子供にする)
            array[parent_index] = array[large_child_index];
        }
        // 上記のfor文によってparent_index => large_child_index
        array[parent_index] = temp;
    }

   /**
    * Heap sort.
    *
    * @param array the array
    * @param n     the n
    */
   static void heapSort(int[] array, int n) {
        // array[i] ~ array[n - 1]をヒープ化
        // 最下流ではなく子を持つ末端を選択してデクリメント
        for (int i = (n - 1) / 2; i >= 0; i--) {
            downHeap(array, i, n - 1);
        }
        /*
         * ここまででヒープ化されたので、最大値を(根から)順に取り出して、配列の末端から(昇順にするために)格納していきたいが、
         * 配列の初期状態がヒープの要件を満たしていない可能性があるため、再ヒープ化
         */
        for (int i = n - 1; i > 0; i--) {
            // 最大要素(常に根は0)と未ソート部末尾要素を交換
            swap(array, 0, i);
            // array[0](swapされた末尾要素) ~ array[i - 1](根をswapした後なので一つ減らす必要がある)をヒープ化
            downHeap(array, 0, i - 1);
        }
    }

   /**
    * The entry point of application.
    *
    * @param args the input arguments
    */
   public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("ヒープソート");

        System.out.print("要素数:");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];
        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]:");
            x[i] = stdIn.nextInt();
        }
        heapSort(x, nx);
        System.out.println("昇順にソートしました");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}