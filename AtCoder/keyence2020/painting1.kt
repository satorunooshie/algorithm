import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
fun main(args: Array<String>) {
    val sc = FastScanner(System.`in`)
    val h = sc.nextInt()
    val w = sc.nextInt()
    val n = sc.nextInt()
    val b = if (w > h) w else h
    println((n + b - 1) / b)
}
class FastScanner(s: InputStream) {
    private var st = StringTokenizer("")
    private val br = BufferedReader(InputStreamReader(s))

    fun next(): String {
        while (!st.hasMoreTokens()) st = StringTokenizer(br.readLine())
            return st.nextToken()
    }

    fun nextInt() = next().toInt()
    fun nextLong() = next().toLong()
    fun nextLine() = br.readLine()
    fun nextDouble() = next().toDouble()
    fun ready() = br.ready()
}

