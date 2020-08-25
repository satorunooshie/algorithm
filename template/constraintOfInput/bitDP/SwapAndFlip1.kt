/*
N枚のカードがあり1, 2..., Nの番号がついています
カードi(1<=i<=N)の片方の面には赤い文字で整数A_iが
もう片方の面には青い文字で整数B_iが書かれています
最初これらの科＝度は赤い文字が書かれた面を表にして
左から右に番号順位一列に並んでいます
以下の操作を繰り返すことで
カードの表側に面に書かれた整数の列が
左から右に広義単調増加となる
すなわち各i(1<=i<=N-1)に対して
左からi+1枚目のカードの表側の面にかかれた整数が
i枚目のカードの表側の面に書かれた整数以上である
ようにすることが可能かどうか判定してください
さらに可能である場合必要な操作の回数の最小値を求めてください
1<=N<=18
1<=A_i, B_i<=50(1<=i<=N)
*/
import java.lang.Integer
import java.lang.Math

fun main(args: Array<String>) {
    val INF = 1000000000
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val b = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val m = 1 shl n
    val dp = Array(m) { IntArray(51) { INF } }
    dp[0][0] = 0
    for (k in 0 until m) {
        for (i in 1..50) dp[k][i] = Math.min(dp[k][i], dp[k][i - 1])
            val x = Integer.bitCount(k)
            for (i in 0 until n) {
                if (((k shr i) and 1) == 1) continue
                    val v = if ((x - i) % 2 == 0) a[i] else b[i]
                    val j = k or (1 shl i)
                    dp[j][v] = Math.min(dp[j][v], dp[k][v] + Integer.bitCount(k shr i))
            }
    }
    val x = dp[m - 1].min()!!
    println(if (x == INF) - 1 else x)
}
