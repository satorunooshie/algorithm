fun main(args: Array<String>) {
    val (n, k, s) = readIntList()
    val ans = StringBuilder()
    repeat(k) {
        ans.append("$s ")
    }
    if (s == 1000000000) {
        repeat(n - k) {
            ans.append("1 ")
        }
    } else {
        repeat(n - k) {
            ans.append("1000000000 ")
        }
    }
    println(ans.toString())
}
//read
fun readString() = readLine()!!
fun readInt() = readLine()!!.toInt()
fun readLong() = readLine()!!.toLong()
fun readStringList() = readLine()!!.split(" ")
fun readIntList() = readLine()!!.split(" ").map(String::toInt)
fun readLongList() = readLine()!!.split(" ").map(String::toLong)
