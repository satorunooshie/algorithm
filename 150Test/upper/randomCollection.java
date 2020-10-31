/* サイズnの配列からm個の整数の集合をランダムに生成するメソッド */
/* ただし、各要素の選ばれる確率は全て等確率にすること */
/*
 * 最初の印象では配列の中からランダムに要素を選びそれを別の配列に追加していくだけで良さそうに見える
 * しかし、同じ要素を二度選んだ場合はどうか。
 * 理想は一度選んだ要素は元の配列から取り除いて配列を縮小したいところだが
 * 配列を縮小するには要素のシフトが必要になり、コストが大きくなる
 * 縮小やシフト操作の代わりに選んだ要素を配列の先頭の要素と入れ替えるようにして配列の残っている部分が
 * j番目以降だということを記憶しておく
 * つまりsubset[0]に対してarray[x]を選んだときarray[x]をarrayの先頭要素array[0]と入れ替える
 * subset[1]を選ぶときは0番目の要素は使用済みなので1からarray.size()-1の中からランダムにyを選ぶ
 * そしてsubset[1]をarray[y]にarray[y]をarray[1]にする
 * この操作をk回行うと配列の先頭からk個分の要素は選択した要素が入り、後半array.size()-k個分は未選択の要素になる
 * 後半の部分から一つ選択しては前半の末尾に追加していくようなイメージで繰り返し操作を行う
 */
//lowerからhigerの間(higherを含む)の乱数
public static int rand(int lower, int higher) {
    return lower + (int)(Math.random() * (higher - lower + 1));
}
//元の配列からM個の要素を選んで入力を破壊しないように元の配列をコピーする
public static int[] pickMRandomly(int[] original, int m) {
    int[] subset = new int[m];
    int[] array = original.clone();
    for (int j = 0; j < m; j++) {
        int index = rand(j, array.length - 1);
        subset[j] = array[index];
        array[index] = array[j]; //array[j]は選択済み
    }
    return subset;
}
