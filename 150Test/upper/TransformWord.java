/*
 * 辞書にある文字数が等しい2つの単語が与えられるとき
 * 1ステップに1文字だけ変える変形を繰り返し、
 * 一方の単語から他方に変形させるメソッドをかく
 * 各ステップで得られる新しい単語は辞書の中になければならない
 */

import java.util.*;

/*
 * 幅優先探索の変形
 * 辞書内の各単語に対してその単語の1文字違いの単語に辺を張ったグラフを作る
 * もっと簡単な解法はbacktrack map
 * 単語wの1文字を変えてvに移動した時にB[v] = wとすることでどの単語からvに移動してきたかを知ることができる
 * 目的の単語に辿り着いた後にこのマップを繰り返し遡ることでパスを復元することができる
 */

/*
 * nをスタートの単語の長さ、mを辞書内の長さがnの単語の個数とすると、whileループ内でキューから最大m単語取り出すことになり、
 * このアルゴリズムはO(nm)の実行時間になる
 * forループは単語ないの各文字を文字の種類分置き換えた単語の個数、つまりO(n)回回る
 */
public class TransformWord {
    LinkedList<String> transform(String startWord, String stopWord, Set<String> dictionary) {
        startWord = startWord.toUpperCase();
        stopWord = stopWord.toUpperCase();
        Queue<String> actionQueue = new LinkedList<String>();
        Set<String> visitedSet = new HashSet<String>();
        Map<String, String> backtrackMap = new TreeMap<String, String>();

        actionQueue.add(startWord);
        visitedSet.add(startWord);

        while (!actionQueue.isEmpty()) {
            String w = actionQueue.poll();
            /* wを一文字変えることで作られるvを回す */
            for (String v : getOneEditWords(w)) {
                if (v.equals(stopWord)) {
                    // 目的の単語が見つかったのでbacktrack
                    LinkedList<String> list = new LinkedList<String>();
                    // vをlistに追加
                    list.add(v);
                    while (w != null) {
                        list.add(0, w);
                        w = backtrackMap.get(w);
                    }
                    return list;
                }
                /* vが辞書にある単語の場合 */
                if (dictionary.contains(v)) {
                    if (!visitedSet.contains(v)) {
                        actionQueue.add(v);
                        // 訪れたことを記録
                        visitedSet.add(v);
                        backtrackMap.put(v, w);
                    }
                }
            }
        }
        return null;
    }
    Set<String> getOneEditWords(String word) {
        Set<String> words = new TreeSet<String>();
        for (int i = 0; i < word.length(); i++) {
            char[] wordArray = word.toCharArray();
            // ある文字を他の文字に変化させる
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != word.charAt(i)) {
                    wordArray[i] = c;
                    words.add(new String(wordArray));
                }
            }
        }
        return words;
    }
}
