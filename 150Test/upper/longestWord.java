/*
 * 単語のリストが与えられたとき、リスト内の単語であって、
 * リスト内の他の単語を並べて作ることができる最も長い単語を探すプログラム
 */

/*
 * まずは単純にする
 * リスト内の二つの単語をつなげてできる最長の単語を知りたい場合は
 * リストの最も長い単語から最も短い単語までを順に調べていけばいい
 * 各単語の可能な二単語への分割に対し、左側と右側の両方がリスト内にある単語になっているかどうか調べれば良い
 */
String getLongestWord(String[] list) {
    String[] array = list.SortByLength();
    //検索を簡単にするためにmapを作る
    HashMap<String, Boolean> map = new HashMap<String, Boolean>;
    for (String str : array) {
        map.put(str, true);
    }
    for (String s : array) {
        //二つの単語に分割する
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            //分割した両方が配列にあるか判定する
            if (map[left] == true && map[right] == true) {
                return s;
            }
        }
    }
    return str;
}
/*
 * これらのコードは単語をつなぎ合わせる場合にはうまく行くが、任意の単語数になった場合は一箇所修正する場所がある
 * 分割の右側が配列内にあるかを見るのではなく、右側自体が配列内の文字列のつなぎ合わせで作られるかを再帰的に判定する
 */
String printLongestWord(String arr[]) {
    HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    for (String str : arr) {
        map.put(str, true);
    }
    Arrays.sort(arr, new LengthComparator()); //長さでソートする
    for (String s : arr) {
        if (canBuildWord(s, true, map)) {
            System.out.println(s);
            return s;
        }
    }
    return "";
}
boolean canBuildWord(String str, boolean isOriginalWord, HashMap<String, Boolean> map) {
    if (map.containsKey(str) && !isOriginalWord) {
        return map.get(str);
    }
    for (int i = 1; i < str.length(); i++) {
        String left = str.substring(0, i);
        String right = str.substring(i);
        if (map.containsKey(left) && map.get(left) == true && canbuildWord(right, false, map)) {
            return true;
        }
    }
    map.out(str, false);
    return false;
}
/*
 * 呼び出しごとに結果をキャッシュして動的計画法を用いて最適化している
 * このようにすることで何度も判定する必要がなくなり、一度の計算で済むようになる
 */
/*
 * isOriginalWordというフラグは最適化がきちんと動くように使う
 * canBuildWordは元のリストにある単語と各部分文字列に対して呼ばれて以前計算したキャッシュがあるかどうかをチェックする
 * しかし、リストの中の文字列に対してはmapはtrueで初期化されているのでフラグがなければ分割前の元の文字列に対して、trueを返してしまうので
 * 下の単語については単純にisOriginalWordというフラグを用いてこの判定部分を避けるようにしている
 */

