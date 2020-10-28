/* マスターマインドゲーム */
/*
   コンピュータには四つのスロットがあり、各スレッドには赤、黄、緑、青の四色のボールがあります。
   例えばコンピュータはRGGB(スロット1が赤、スロット2と3が緑、スロット4が青)のような状態になっているとします
   プレイヤーは正解がYRGBと思ったとした時
   もしスロットの位置と色が一致していればヒット、その色はあるがスロットの場所が違う場合はブローが得られます
   ただしヒットの時はブローをカウントしません
   このゲームで予測と正解を与えられた時にヒットとブローの数を返すメソッドを書く
*/
//各文字が正解に出現する回数をヒットの時をのぞいて保持する頻度配列を用意して予測の文字列を順に調べてブローの数を数える
public class Result {
    public int hits = 0;
    public int pseudoHits = 0;
    public String toString() {
        return "(" + hits + ", " + pseudoHits + ")";
    }
}
public int code(char c) {
    switch (c) {
        case 'B':
            return 0;
        case 'G':
            return 1;
        case 'R':
            return 2;
        case 'Y':
            return 3;
        default:
            return -1;
    }
}
public static int MAX_COLORS = 4;
public Result estimate(String guess, String solution) {
    if (guess.length() != solution.length()) return null;
    Result res = new Result();
    int[] frequencies = new int[MAX_COLORS];
    //ビット数を数えて頻度配列を作成する
    for (int i = 0; i < guess.length(); i++) {
        if (guess.charAt(i) == solution.charAt(i)) {
            res.hits++;
        } else {
            //ヒットでない場合のみ頻度配列(これは擬似ヒットの計算に使われる)を増やす
            //ヒットの場合はそのスロットはすでに使われている
            int code = code(solution.charAt(i));
            frequencies[code]++;
        }
    }
    //擬似ヒットを計算する
    for (int i = 0; i < guess.length(); i++) {
        int code = code(guess.charAt(i));
        if (code >= 0 && frequencies[code] > 0 &&
                guess.charAt(i) != solution.charAt(i)) {
            res.pseudoHits++;
            frequencies[code]--;
                }
    }
    return res;
}
//アルゴリズムが簡単であればあるほど綺麗なコードが重要
//code(char c)をメソッドとして切り出してResultクラスに結果を持たせるようにした
