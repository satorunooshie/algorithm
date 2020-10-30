/*
   しまった！長い文章のファイルでとんでもない置換ミスをしてしまった。
   文章中の空白やアルファベット以外の文字が全て消え、大文字も全て小文字になってしまいました。
   "I reset the computer. It still didn't boot!'という文章は'iresetthecomputeritstilldidntboot'のようになってしまっています
   一度個々の単語に切り分けてその跡の痕跡を辿り大文字への変換を行いたいとします。
   ほとんどの単語は辞書の中にありますが、固有名詞のように辞書に含まれていない単語もあります
   辞書(単語のリスト)が与えられるとき繋がってしまった文章を単語ごとにうまく切り分ける最適なアルゴリズムを設計してください
   ここでいう最適とは判別できない文字数が最小限になるようにするという意味です
   例えば'jesslookedjustliketimherbrother'を最適に変換すると
   'JESS looked just like TIM her brother'のようになり、判別できない文字はわかりやすいように大文字にします
*/
/*
   この問題では一つの文字列をなるだけ判別できない単語が少なくなるように単語ごとに分割するということが要点
   ただし文字列の意味を理解しようとする必要はないことに注意する
   この問題のキーポイントは部分問題に対して解(つまり単語に切り分けた文字列)を構築する方法を見つけるところ
   一つの方法としては文字列に対して再帰的にパースしていく手法がある
   各地点で次の二つの可能性のうち最適な方を解とする
   1.その文字の後ろに空白文字を挿入する
   2.空白文字を挿入しない
   最初は文字列を分割し、次に接続する
*/
public int parseSimple(int wordStart, int wordEnd) {
    if (wordEnd >= sententce.length()) {
        return wordEnd - wordStart;
    }
    String word = sentence.substring(wordStart, wordEnd + 1);
    //現在の単語を分断する
    int bestExact = parseSimple(wordEnd + 1, wordEnd + 1);
    if (!dictionay.contains(word)) {
        bestExact += word.length();
    }
    //現在の単語を伸ばす
    int bestExtend = parseSimple(wordStart, wordEnd + 1);
    //最適な方を求める
    return Math.min(bestExact, bestExtend);
}
/* このコードには大きく分けて二つの最適化が考えられる
   再帰時に重複する処理の最適化、最初に計算したものをキャッシュとしておき二度目以降はこれを再利用する、DPを用いて実現
   利用不能な文字列の予測による最適化xtで始まる単語が存在しないことを素早く知るにはトライ木を使う
*/
public int parseOptimized(int wordStart, int wordEnd, Hashtable<Integer, Integer> cache) {
    if (wordEnd >= sentence.length()) {
        return wordEnd - wordStart;
    }
    if (cache.containsKey(wordStart)) {
        return cache.get(wordStart);
    }
    String currentWord = sentence.substring(wordStart, wordEnd + 1);
    //prefixが辞書に存在するか判定。引数のfalseは部分マッチを表す
    boolean validPartial = dictionary.contains(currentWord, false);
    //現在の単語を分析する
    int bestExact = parseOptimized(wordEnd + 1, wordEnd + 1, cache);
    //単語全体が辞書にない場合は識別不能数を増やす
    if (!validPartial || !dictionary.contains(currentWord, true)) {
        bestExact += currentWord.length();
    }
    //現在の単語を伸ばす
    int bestExtend = Integer.MAX_VALUE;
    if (validPartial) {
        bestExtend = parseOptimized(wordStart, wordEnd + 1, cache);
    }
    //最適な方を求める
    int min = Math.min(bestExact, bestExtend);
    cache.put(wordStart, min); //キャッシュに登録
    return min;
}
/*
   結果をキャッシュするのにハッシュテーブルを用いている
   キーは単語の始まりのインデックスで文字列の残りを解析するのに一番いい方法をキャッシュしている
   このコードを完全に解析されたものを返すコードにすることもやや面倒ではあるができる
   判別できなかった文字数と最適な文字列を返すことができるようにするにはResultのラッパークラスを用いる必要がある
   これをC++で実装すれば単に参照を渡すだけでできるらしい
*/
public class Result {
    public int invalid = Integer.MAX_VALUE;
    public String parsed = "";
    public Result(int inv, String p) {
        invalid = inv;
        parsed = p;
    }
    public Result clone() {
        return new Result(this.invalid, this.parsed);
    }
    public static Result min(Result r1, Result r2) {
        if (r1 == null) {
            return r2;
        } else if (r2 == null) {
            return r1;
        }
        return r2.invalid < r1.invalid ? r2 : r1;
    }
}
public Result parse(int wordStart, int wordEnd, Hashtable<Integer, Result> cache) {
    if (wordEnd >= sententce.length()) {
        return new Result(wordEnd - wordStart, sentence.substring(wordStart).toUpperCase());
    }
    if (cache.containsKey(wordStart)) {
        return cache.get(wordStart).clone();
    }
    String currentWord = sentence.substring(wordStart, wordEnd + 1);
    boolean validPartial = dictionary.contains(currentWord, false);
    boolean validExact = validPartial && dictionary.contains(currentWord, true);
    //現在の単語を分析する
    Result bestExact = parse(wordEnd + 1, wordEnd + 1, cache);
    if (validExact) {
        bestExact.parsed = currentWord + " " + bestExact.parsed;
    } else {
        bestExact.invalid += currentWord.length();
        bestExact.parsed = currentWord.toUpperCase() + " " + bestExact.parsed;
    }
    //現在の単語を伸ばす
    Result bestExtend = null;
    if (validPartial) {
        bestExtend = parse(wordStart, wordEnd + 1, cache);
    }
    //最適な方を求める
    Result best = Result.min(bestExact, bestExtend);
    cache.put(wordStart, best.clone());
    return best;
}
/*
   DPの問題におけるキャッシュのやり方に注意する
   キャッシュした値がオブジェクトでプリミティブなデータ型でなければオブジェクトのクローンが必要になる
   クローンを生成しないのであれば解析のため後から呼び出した場合にキャッシュの値が意図せず書き換えられてしまっている可能性がある
*/
