/* ある本について任意の単語の出現頻度を求めるメソッドの設計 */
//1語だけの場合
/*
   この場合は前から一単語ごとに調べ出現頻度を数えるだけでO(n)になるので
   少なくとも一回は全ての単語を調べなければならず改善の余地はない
*/
//複数語の場合
/*
   複数回の操作が必要になる場合は、本のデータに対して時間とメモリを使って前処理をする余裕がある
   単語をキーとしてその出現頻度にマップするハッシュテーブルを生成することで
   O(1)の計算時間で解を得ることができる
*/
Hashtable<String, Integer> setupDictionary(String[] book) {
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    for (String word : book) {
        word = word.toLowerCase();
        if (word.trim() != "") {
            if (!table.containKey(word)) {
				table.put(word, 0);
			}
            table.put(word, table.get(word) + 1);
        }
    }
    return table;
}
int getFrequency(Hashtable<String, Integer> table, String word) {
    if (table == null || word == null) return -1;
    word = word.toLowerCase();
    if (table.containsKey(word)) {
        return table.get(word);
    }
    return 0;
}
