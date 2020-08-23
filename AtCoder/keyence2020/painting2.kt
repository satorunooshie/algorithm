import java.util.*
fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val h = sc.nextInt()
    val w = sc.nextInt()
    val n = sc.nextInt()
    val mx = Math.max(h, w)
    println((n + mx - 1) / mx)
}
