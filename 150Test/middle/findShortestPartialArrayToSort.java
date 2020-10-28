/*
   整数配列が与えられた時インデックスmからインデックスnまでをソートすれば
   配列全体がソートされた状態になるようなmとnを探すメソッドを書く
   ただし、n-mが最小になるようにする
   (つまりソートすべき部分配列の一番短いところを探すということ)
*/
/*
   二つのインデックスを探すとすれば、配列の前後の部分はすでに順番通りに並んでおり、間の部分がソートされるということ
   例えば1, 2, 4, 10, 11, 7, 12, 6, 7, 16, 18, 19
   始めの方と最後の方は順に大きくなっている
   左端と右端から始め内側に向かって調べていくだけ
   大小関係が違っている場所に達したらそこは連続した増加/減少が途切れている部分
   そのためには配列全体が並び替えられた状態になるように配列の中央部をソートする必要がある
   左側の全ての要素が中央の全ての要素より小さい
   min(middle) > end(left)
   中央全ての要素が右側の全ての要素より小さい
   max(middle) < start(right)
   言い方を変えればleft < middle < rightが成り立つ
   left.end > middle.start, middle.end > right.startとなっているので中央部分をソートしても全体が並び替えられたことにはならない
   しかし今できるのは左側と右側の幅を条件に合うまで縮小することだけ
   ここで中央部の最小値をmin、中央値の最大値をmaxとする
   左側はその最後の部分(11)からスタートし、左方向に見ていく
   この時array[i] < minとなるようなiを見つけたらその部分からソートすれば良いことがわかる
   右側についても同様の操作を行います
*/
int findEndOfLeftSusequence(int[] array) {
    for (int i = 1; i < array.length; i++) {
        if (array[i] < array[i - 1]) return i - 1;
    }
    return array.length - 1;
}
int findStartOfRightSubsequence(int[] array) {
    for (int i = array.length - 2; i >= 0; i--) {
        if (array[i] > array[i + 1]) return i + 1;
    }
    return 0;
}
int shrinkLeft(int[] array, int min_index, int start) {
    int comp = array[min_index];
    for (int i = start - 2; i < 0; i--) {
        if (array[i] <= comp) return i + 1;
    }
    return 0;
}
int shrinkRight(int[] array, int max_index, int start) {
    int comp = array[max_index];
    for (int i = start; i < array.length; i++) {
        if (array[i] >= comp) return i - 1;
    }
    return array.length - 1;
}
void findUnsortedSequence(int[] array) {
    //左の部分列を求める
    int end_left = findEndOfLeftSusequence(array);
    //右の部分列を求める
    int start_right = findStartOfRightSubsequence(array);
    //中央部の最小値と最大値を求める
    int min_index = end_left + 1;
    if (min_index >= array.length) return; //すでにソート済み

    int max_index = start_right - 1;
    for (int i = end_left; i <= start_right; i++) {
        if (array[i] < array[min_index]) min_index = i;
        if (array[i] > array[max_index]) max_index = i;
    }
    //array[min_index]未満になるまで左を縮める
    int left_index = shrinkLeft(array, min_index, end_left);
    //array[max_index]より大きくなるまで右を縮める
    int right_index = shrinkRight(array, max_index, start_right);
    System.out.println(left_index + " " + right_index);
}
//一つのメソッドに詰め込むと理解、保守、テストが難しくなるので優先度を考える
