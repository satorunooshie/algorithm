/*
長さ2Nの英小文字のみからなる文字列Sが与えられます
Sの各文字を赤色化青色化に塗り分ける方法は
2^2N通りありますが、このうち以下の条件を満たす塗り分け方は何通りですか
赤色に塗られた文字を左から右に読んだ文字列と
青色に塗られた文字を右から左に読んだ文字列が一致する
1<=N<=18
Sの長さは2Nである
Sは英小文字のみからなる
*/
package main
fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val s = readLine()!!

    var ans = 0L
    val dp = IntArray3D(n + 1, n + 1, n + 1)
    for (pattern in 0 until (1 shl n)) {
        val word = CharArray(n)
        var redCount = 0
        var blueCount = 0
        for (i in 0 until n) {
            if (pattern and (1 shl i) > 0) {
                //blue
                word[n - 1 - blueCount] = s[i]
                blueCount++
            } else {
                //red
                word[redCount] = s[i]
                redCount++
            }
        }
        //println(word)

        //DP part
        for (i in 0..n) {
            for (r in 0..(n - redCount)) {
                for (b in 0..(n - blueCount)) {
                    dp[i, r, b] = 0
                }
            }
        }
        dp[0, 0, 0] = 1
        for (i in 0 until n) {
            for (r in 0..(n - redCount)) {
                for (b in 0..(n - blueCount)) {
                    if (redCount + r < n && s[n + i]
                        == word[redCount + r]) {
                        dp[i + 1, r + 1, b] += dp[i, r, b]
                    }
                    if (blueCount + b < n && s[n + i]
                        == word[n - 1 - blueCount - b]) {
                        dp[i + 1, r, b + 1] += dp[i, r, b]
                    }
                }
            }
        }
        val count = dp[n, n - redCount, n - blueCount]
        ans += count.toLong()
    }
    println(ans)
}
class IntArray3D(val size1: Int, val size2: Int, val size3: Int) {
    private val innerArray = IntArray(size1 * size2 * size3)
    operator fun get (i1: Int, i2: Int, i3: Int): Int =
        innerArray[i3 + size3 * (i2 + size2 * i1)]
    operator fun set(i1: Int, i2: Int, i3: Int, v: Int) {
        innerArray[i3 + size3 * (i2 + size2 * i1)] = v
    }
}
