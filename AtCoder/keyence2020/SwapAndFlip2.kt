fun main(args: Array<String>) {
    val INF = 1001001001
    val N = nextInt()
    val A = listOfInt()
    val B = listOfInt()
    val M = 1 shl N
    val dp = Array(M) { IntArray(51) { INF } }
    dp[0][0] = 0
    for (m in 0 until M) {
        for (i in 1 .. 50) dp[m][i] = Math.min(dp[m][i], dp[m][i - 1])
            val x = Integer.bitCount(m)
            for (n in 0 until N) {
                if (((m shr n) and 1) == 1) continue
                    val v = if ((x - n) % 2 == 0) A[n] else B[n]
                    val j = m or (1 shl n)
                    dp[j][v] = Math.min(dp[j][v], dp[m][v] + Integer.bitCount(m shr n))
            }
    }
    val x = dp[M - 1].min()!!
    println(if (x == INF) - 1 else x)
}
fun next() =readLine()!!
fun nextInt(delta: Int = 0) = Integer.parseInt(next()) + delta
fun listOfString() = next().split(" ")
fun listOfInt(delta: Int = 0) = listOfString().map { Integer.parseInt(it) + delta }
