/*
1からNまでの番号が付いたN人までの人がいます
彼らはみな必ず正しい証言を言う正直者か、真偽不明の証言を行う不親切な人のいずれかです
人iはA_i個の証言を言っています
人iのj個目の証言は2津の整数x_ij,y_ijで表され
y_ij=1のときは人x_ijは正直者であるという証言であり
y_ij=0のときは人x_ijは不親切な人であるという証言です
このN人の中には最大で何人の正直者が存在し得るでしょうか
*/
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max

val br = BufferedReader(InputStreamReader(System.`in`))
val out = PrintWriter(System.out)

fun main() {
    br.use { out.use {
        solve()
        out.flush()
    } }
}

fun solve() {
    val n = readInteger()
    val map = mutableMapOf<Int, Map<Int, Int>>()
    repeat(n) {
        val a = readInteger()
        val tmp = mutableMapOf<Int, Int>()
        repeat(a) {
            val (x, y) = readIntegerList()
            tmp[x - 1] = y
        }
        map[it] = tmp
    }
    var ans = 0
    for (state in 0 until (1 shl n)) {
        if (check(state, n, map)) {
            ans = max(ans, Integer.bitCount(state))
        }
    }
    out.println(ans)
}

fun check(state: Int, n: Int, map: MutableMap<Int, Map<Int, Int>>): Boolean {
    for (i in 0 until n) {
        if (((state shr i) and 1) == 1) {
            for (j in map[i]!!) {
                if (((state shr j.key) and 1) != j.value) {
                    return false
                }
            }
        }
    }
    return true
}

fun readInteger() = Integer.parseInt(br.readLine())
fun readLong() = java.lang.Long.parseLong(br.readLine())
fun readStringList() = br.readLine()!!.split(' ')
fun readIntegerList() = readStringList().map(Integer::parseInt)
fun readLongList() = readStringList().map(java.lang.Long::parseLong)
fun readDoubleList() = readStringList().map(java.lang.Double::parseDouble)
