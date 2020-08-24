package main
import (
    "fmt"
    "strconv"
    "strings"
)
const INF = 1000000000
func main() {
    var n int
    fmt.Scan(&n)
    a := make([]int, n)
    for i := 0; i < n; i++ { fmt.Scan(&a[i]) }
    b := make([]int, n)
    for i := 0; i < n; i++ { fmt.Scan(&b[i]) }
    m := 1 << uint(n)
    dp := make([][]int, m)
    for i := 0; i < m; i++ {
        dp[i] = make([]int, 51)
        for j := 0; j < 51; j++ { dp[i][j] = INF }
    }
    dp[0][0] = 0
    for k := 0; k < m; k++ {
        for i := 1; i < 51; i++ {
            dp[k][i] = min(dp[k][i], dp[k][i - 1])
        }
        x := popcnt(k)
        for i := 0; i < n; i++ {
            if (k >> uint(i)) & 1 == 1 { continue }
            v := b[i]
            if (x - i) % 2 == 0 { v = a[i] }
            j := k|(1 << uint(i))
            dp[j][v] = min(dp[j][v], dp[k][v] + popcnt(k >> uint(i)))
        }
    }
    x := INF
    for i := 0; i < 51; i++ {
        x = min(x, dp[m - 1][i])
    }
    if x == INF {
        x = -1
    }
    fmt.Println(x)
}

func min(x, y int) int {
    if x < y {
        return x
    }
    return y
}

func popcnt(x int) int {
    s := strconv.FormatInt(int64(x), 2)
    return strings.Count(s, "1")
}

