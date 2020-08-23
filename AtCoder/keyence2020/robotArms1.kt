import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

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
    val lrList = Array(n) {
        val (x, l) = readLongList()
        Pair(x - l, x + l)
    }.sortedWith(comparedBy { it.second })

    var last = Long.MIN_VALUE
    var ans = 0
    for (lr in lrList) {
        if (last <= lr.first) {
            ans++
            last = lr.second
        }
    }
    out.println(ans)
}
fun readInteger() = Integer.parseInt(br.readLine())
fun readLong() = java.lang.Long.parseLong(br.readLine())
