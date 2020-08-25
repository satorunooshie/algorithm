/*
 * onとoffの状態を持つN個のスイッチとM個の電球があります
 * スイッチには1からNの、電球には1からMの番号がついています
 * 電球iはk_iこのスイッチにつながっておりスイッチs_i1, ...s_ik_iのうち
 * onになっているスイッチの個数を2で割った余りがp_iに等しいときに点灯します
 * 全ての電球が点灯するようなスイッチのon/offの状態の組み合わせは何通りあるでしょうか
 * 1<=N,M<=10
 * 1<=k_i<=N
 * 1<=s_ij<=N
 * s_ia!=s_ib(a!=b)
 * p_i=0か1
 * 入力はすべて整数
 */
import kotlin.comparisons.*
import java.util.*

const val MOD = 1000000007L

fun readI() = readLine()!!.toInt()
fun readL() = readLine()!!.toLong()
fun readD() = readLine()!!.toDouble()
fun readIs() = readLine()!!.split(' ').map(String::toInt)
fun readLs() = readLine()!!.split(' ').map(String::toLong)
fun readDs() = readLine()!!.split(' ').map(String::toDouble)

fun main(args: Array<String>) {
    val (n, m) = readIs()
    val ss = IntArray(m)
    repeat(m) {
        val ks = readIs()
        var s = 0
        for (i in 1..ks[0]) {
            s += 1 shl (ks[i] - 1)
        }
        ss[it] = s
    }
    val pp = readIs()
    var ans = 0
    next@for (i in 0 until(1 shl n)) {
        for (j in 0 until m) {
            val x = Integer.bitCount(i and ss[j])
            if (x % 2 != pp[j]) continue@next
        }
        ans++
    }
    println(ans)
}
