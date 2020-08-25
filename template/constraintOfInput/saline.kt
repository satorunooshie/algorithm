/*
食塩水が入った容器がN個あります
容器には1からNまでの番号がついています
i番の容器に濃度p_iパーセントの食塩水がw_iグラムはいっています
高橋君はK個の容器を選び、
選んだ容器の中に入っているすべて混ぜ合わせることにしました
高橋君の混ぜた食塩水の濃度として考えられる最大値を求めてください
1<=K,N<=1000
1<=w_i<=10^9
0<=p_i<=100
*/
package main
import java.io.*
import java.util.*
import java.lang.*
import java.lang.Math.*

fun bsCondClop(WP: ArrayList<IntPair>, L: Double, R: Double, K: Int): Double {
    val N = WP.size
    fun isCondition(WP: ArrayList<IntPair>, m: Double): Boolean {
        val priority = ArrayList<Pair<Int, Double>>()
        for (i in 0 until N) {
            priority.add(Pair(i, (WP[i].second-m) * WP[i].first))
        }
        priority.sortByDescending { it.second }

        var diff = 0.0
        for (i in 0 until K) {
            diff += priority[i].second
        }
        return diff >= 0
    }
    var l = L;
    var r = R;
    while (l < r-EPS) {
        val m = (l + r) / 2.0
        if (isCondition(WP, m)) {
            l = m
        } else {
            r = m
        }
    }
    return l
}

fun solve() {
    val (N, K) = rd.readIntArray()
    val WP = ArrayList<IntPair>(N)
    for (i in 0 until N) {
        val (w, p) = rd.readIntArray()
        WP.add(IntPair(w, p))
    }

    val ans = bsCondClop(WP, 0.0, 100+EPS, K)
    println("%.9f".format(ans))
}

//val rd = debug.Reader("abc", "034", "d", "simple-1")
val rd = Reader()

fun main(args: Array<String>) {
    try {
        var exception: Throwable? = null
        val sth = Thread(null, SolveThread(), "solveThread", 128 * 1024 * 1024)
        sth.setUncaughtExceptionHandler { _, e -> exception = e }
        sth.start();
        sth.join();
        if (exception != null) throw exception!!
    } catch (e: Exception) { throw e }
}
const val MOD = 1_000_000_007L
const val INF = Int.MAX_VALUE / 2
const val LINF = Long.MAX_VALUE / 2
val EPS = 1e-12
fun DBLEQ(a: Double, b: Double): Boolean { return abs(a - b) < EPS }
data class IntPair(val first: Int, val second: Int)
data class LongPair(val first: Long, val second: Long)
fun <T>edge(V: Int): Array<ArrayList<T>> { return Array(V){ArrayList<T>()} }
val errPrintln: (Any) -> Unit = { msg -> System.err.println(msg) }
fun rAssert(exp: Boolean, lazyMsg: () -> String = {""}) {
    if (!exp) {
        val msg = lazyMsg()
        errPrintln("EAssert Error: $msg")
        throw Error(msg)
    }
}
fun tAssert(exp: Boolean, lazyMsg: () -> String = {""}) {
    if (!exp) {
        val msg = lazyMsg()
        errPrintln("TAssert Error; $msg")
        Thread.sleep(3000)
    }
}
class SolveThread : Runnable {
    override fun run() { solve() }
}
class Reader {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    val readString: () -> String = { br.readLine() }
    val readInt: () -> Int = { br.readLine().toInt() }
    val readLong: () -> Long = { br.readLine().toLong() }
    val readIntArray: () -> IntArray = {
        br.readLine().split(' ').map {
            it.toInt()
        }.toIntArray()
    }
    val readLongArray: () -> LongArray = {
        br.readLine().split(' ').map {
            it.toLong()
        }.toLongArray()
    }
    val readListInt: () -> List<Int> = {
        br.readLine().split(' ').map {
            it.toInt()
        }
    }
    val readListLong: () -> List<Long> = {
        br.readLine().split(' ').map {
            it.toLong()
        }
    }
}
