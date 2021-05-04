package CS;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Pair of cards.
 * Pair of Cards というゲームをプレイしています。このゲームは以下のルールになっています。
 *
 * - 同じランク（※カードの強さ）のカードの枚数が多いプレイヤーが勝利する（2 が 3 枚 > 10 が 2 枚）
 *
 * - 上記枚数が同じ場合は、そのカードのランクによって勝敗が決まる（2 が 2 枚 < 10 が 2 枚）
 *
 * - 上記も同じ場合は、次に枚数の多いカードを同様に比べ、勝敗が決まるまですべてのカードを比べる
 *
 * - 最終的に勝敗が決まらない場合は draw とする
 *
 * - カードの強さ : 2 < 3 < 4 < 5 < 6 … J < Q < K < A
 *
 * プレイヤー1、2 の手札を表す配列 player1、player2 が与えられるので、勝利プレイヤー文字列で返す、winnerPairOfCards という関数を作成してください。
 *
 * 例1 : プレイヤー1 が ["♣4","♥8","♥8","♠8","♣J"] で、プレイヤー2 が ["♣4","♥J","♥J","♠Q","♣3"] の場合、プレイヤー1 は 8 を 3 枚持ち、プレイヤー2 は J を 2 枚持っているので、プレイヤー1 が勝利します。
 *
 * 例2 : プレイヤー1 が ["♣4","♥8","♥8","♠4","♣J"] で、プレイヤー2 が ["♣4","♥J","♥J","♠Q","♣3"] の場合、プレイヤー1 は 4 を 2 枚、8 を 2 枚持ち、プレイヤー2 は J を 2 枚持つので、両者のランクの数は同じです。一方、両者のカードを比較してみると、プレイヤー2 のカード（J）の方がプレイヤー1（8）よりも強いので、プレイヤー2 の勝利となります。
 *
 * 例3 : プレイヤー1 が ["♣4","♥7","♥7","♠Q","♣J"] で、プレイヤー2 が ["♥7","♥7","♣K","♠Q","♦2"] の場合、プレイヤー1 は 7 を 2 枚持ち、プレイヤー2 も 7 を 2 枚持つので、両者のランクの数もカードの強さも同じです。次に 1 枚のカードを見ると、プレイヤー2 のカード K の方がプレイヤー1 のカード Qよりも強いので、プレイヤー2 の勝利となります。
 */
public class PairOfCards {
    /**
     * Winner pair of cards string.
     *
     * @param player1 the player 1
     * @param player2 the player 2
     * @return the string
     */
    public static String winnerPairOfCards(String[] player1, String[] player2) {
        // 強い順に並び替えた連想配列
        int[][] cards = new int[13][2];
        for (String player : player1) {
            player = player.replace("A", "14").replace("J", "11").replace("Q", "12").replace("K", "13");
            cards[14 - Integer.parseInt(player.substring(1))][0] += 1;
        }
        for (String player : player2) {
            player = player.replace("A", "14").replace("J", "11").replace("Q", "12").replace("K", "13");
            cards[14 - Integer.parseInt(player.substring(1))][1] += 1;
        }
        /*
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print("A: " + cards[i][0]);
                System.out.println(" B: " + cards[i][1]);
            }
        }
         */

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        return winnerHelper(cards, list1, list2);
    }

    /**
     * Winner helper string.
     *
     * @param cards the cards
     * @param list1 the list 1
     * @param list2 the list 2
     * @return the string
     */
    public static String winnerHelper(int[][] cards, List<Integer> list1, List<Integer> list2) {
        int max1 = 0, max2 = 0, index1 = 0, index2 = 0;
        // 強い順に走査すると値が書き変わってしまう。。
        for (int i = cards.length - 1; i >= 0; i--) {
            // 走査済みかどうか
            if (cards[i][0] >= max1 && !list1.contains(i)) {
                max1 = cards[i][0];
                index1 = i;
            }
            if (cards[i][1] >= max2 && !list2.contains(i)) {
                max2 = cards[i][1];
                index2 = i;
            }
        }
        // System.out.println("max: " + max1 + " " + max2);
        if (max1 > max2) return "player1";
        if (max2 > max1) return "player2";
        // = の場合は強さ比べ
        // System.out.println("index: " + index1 + " " + index2);
        // 降順で並んでいるので、インデックスは小さい方が強い
        if (index1 < index2) return "player1";
        if (index2 < index1) return "player2";
        // 全ての走査終了時
        if (max1 == 0 || max2 == 0) return "draw";
        list1.add(index1);
        list2.add(index2);
        return winnerHelper(cards, list1, list2);
    }
}
