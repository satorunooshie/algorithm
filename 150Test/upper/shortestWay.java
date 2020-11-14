/*
 * 単語のデータが書かれた大きなテキストファイルがあるとする
 * 任意の二つの単語に対してファイル内で最小の距離(間にある単語数)を求める
 * O(1)の実行時間で探索処理を行うことができるか
 * またどの程度の消費メモリまで抑えられるか
 */
/* 二つの語の順序を問題にしないと仮定する */
/*
 * この問題を解くにはファイル内のデータ全体を走査すれば良い
 * 走査しながら二つの単語が最後に出現した場所を記憶し、これをlastPosWord1, lastPosWord2とする
 * word1が出現したらlastPosWord2との距離を調べ必要に応じて最小値minを更新し、lastPosWord1の値も更新する
 * 同様の操作をword2についても行い、最後まで走査すれば距離の最小値が得られる
 */
public int shortest(String[] words, String word1, String word2) {
    int min = Integer.MAX_VALUE;
    int lastPosWord1 = -1;
    int lastPosWord2 = -1;
    for (int i = 0; i < words.length; i++) {
        String currentWord = words[i];
        if (currentWord.equals(word1)) {
            lastPosWord1 = i;
            //もし語の順序を考慮するなら以下の三行をコメントアウトする
            int distance = lastPosWord1 - lastPosWord2;
            if (lastPosWord2 >= 0 && min > distance) {
                min = distance;
            }
        } else if (currentWord.equals(word2)) {
            lastPosWord2 = i;
            int distance = lastPosWord2 - lastPosWord1;
            if (lastPosWord1 >= 0 && min > distance) {
                min = distance;
            }
        }
    }
    return min;
}
/*
 * 単語の集合と単語の集合の間の最小距離を求める場合は単語ごとに出現位置を記録するハッシュテーブルを作る
 * 二つの単語をキーとした出現位置のリストをlistA, listBとするとlistAの値とlistBの値の差が最小になるものを見つければ良い
 * 最小値を見つける方法はいくつかある
 * listA: {1, 2, 9, 15, 25}
 * listB: {4, 10, 19}
 * これらのリストを一つのソートされたリストにマージする
 * 正各値にはどちらのリストの値なのかがわかるタグをつけておく
 * タグ付は実際の値とリスト番号の二つのメンバ変数を持つクラスでラッピングできる
 * list: {1a, 2a, 4a, 9a, 10b, 15b, 19b, 20b}
 * 最小の距離を見つけるにはこのリストを最初から調べて連続する二つの要素でタグの種類が異なるものの差が最小になっている部分を見つければ良い
 */
