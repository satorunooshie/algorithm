fun longList() = readLine()?.split(" ")?.map(String::toLong) ?: TODO()
fun main(args: Array<String>) {
    val n = readLine()?.toInt() ?: return
    var c: Pair<Long, Long>? = null
    var count = 1
    0.until(n).map {
        val (x, l) = longList()
        Pair(x - l, x + l)
    }
        .sortedBy { it.second }
        .forEach { next ->
            c?.let {
                if (it.second <= next.first) {
                    count++
                    c = next
                }
            } ?: run {
                c = next
            }
        }
    println(count.toString())
}
