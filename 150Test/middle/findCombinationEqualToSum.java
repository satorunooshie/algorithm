/* 配列において二つの要素の合計値が指定した値と等しくなる組み合わせを全て見つけるアルゴリズム */
//好ましい解法は時間効率、メモリ効率、コードの複雑さのどれを重視するかによって決まる
//シンプルな解法
/*
   時間的に効率がいいのはハッシュマップを用いる方法で
   配列を順番に見て、要素xについて合計値をsumとすると
   sum - xをハッシュテーブルから探す
   もし見つかれば(x, sum - x)を表示する
   そしてハッシュテーブルに追加し、次の要素について調べる
*/
//その他の解法
/*
   合計がzになる数の組みを見つけたいとき、xに対応する値は
   z-x(xを加えてzになる数)なので、例えば合計が12になる数を探そうとする場合
   -5に対応する数は17になる

   {-2 -1 0 3 5 6 7 9 13 14}というソート済みの配列を使って考え、
   firstを配列の先頭、lastを配列の最後尾、合計値をsumとすると
   firstに対応する値を見つけるにはlastから戻りながら探していく
   first+last<sumになればfirstに対応する値が存在しないということになり、
   firstを一つ次に動かし、firstがlastより大きくなれば停止する
   配列はソート済みで、firstとlastの合計値がsumより小さければ
   lastをより小さくしても、対応地は見つからないのは明らかでfirstを進めることになる
   逆に合計がsumを超えているのであればfirstを進めても意味がないのでlastを戻していく
   これを繰り返していけば合計がsumになる値の組み合わせが全て求まることになる
*/
void printPairSums(int[] array, int sum) {
    Arrays.sort(array);
    int first = 0;
    int last = array.length - 1;
    while (first < last) {
        int s = array[first] + array[last];
        if (s == sum) {
            System.out.println(array[first] + " " + array[last]);
            first++;
            last--;
        } else {
            if (s < sum) first++;
            else last--;
        }
    }
}
