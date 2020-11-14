/* 10億個の数の中で小さいもの100万個を見つけるアルゴリズム */
/*
 * ソート
 * 要素を昇順にソートし最初の100万個を取り出す
 * 計算量はO(n log(n))となる
 */
/*
 * 最小ヒープ
 * 最初の100万個について最大ヒープ(最大値が先頭に来るヒープ)を生成する
 * リストの残りの要素を順にヒープに追加して最大の要素を取り除くという操作を繰り返す
 * 走査し終えると小さい方から100万個の要素からなるヒープが得られ、
 * m個の要素を見つけ出すにはO(n log(m))の計算が必要になる
 */
/*
 * 選択アルゴリズム(元の配列を変形できる場合)
 * 配列の中のi番目に小さい数(あるいは大きい数)を線形時間で見つけることができる
 * 重複する要素がない場合はi番目に小さい数を平均O(n)の時間で見つけることができる
 * 1.配列の中の要素を一つランダムに選び、そこをピボットする
 *  ピボットの左にピボットより小さい値、右に大きい値がくるように配列を分割し、左側の要素の数に注目する
 * 2.左側にちょうどi個の要素があればその中の最大値を返す
 * 3.左側の要素がiより大きい場合は左側の配列にこのアルゴリズムをもう一度適用する
 * 4.左側の要素の数がiより小さい場合は右側にこのアルゴリズムをもう一度適用する
 *  ただし、この場合は右側の配列のi-(左のサイズ)番目の要素を見つける
 */
public int partition(int[] array, int left, int right, int pivot) {
    while (true) {
        while (left <= right && array[left] <= pivot) {
            left++;
        }
        while (left <= right && array[right] > pivot) {
            right--;
        }
        if (left > right) {
            return left - 1;
        }
        swap(array, left, right);
    }
}
public int rank(int[] array, int left, int right, int rank) {
    int pivot = array[randomIntInRange(left, right)];
    //分割を行い分割の左半分の終端を返す
    int leftEnd = partition(array, left, right, pivot);
    int leftSize = leftEnd - left + 1;
    if (leftSize == rank + 1) {
        return max(array, left, leftEnd);
    } else if (rank < leftSize) {
        return rank(array, left, leftEnd, rank);
    } else {
        return rank(array, leftEnd + 1, right, rank - leftSize);
    }
}
/*
 * i番目に小さい要素を見つけると最終的な配列の最初を見るだけでそれ以下の値を全て見つけることができる
 * もし要素に重複するものがあれば、少し変更を加える必要がある
 * しかし、そうなると実行時間の保証はできなくなる
 * 重複する市内に関係なく、i番目に小さい数を線形時間で見つけるアルゴリズムは存在するが難易度が高すぎる
 */

