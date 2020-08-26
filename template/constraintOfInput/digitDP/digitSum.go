/*
1以上K以下の整数のうち、十進表記各桁の数字の総和が
Dの倍数であるようなものは何個でしょうか
10^9+7で割った余りを求めてください
*/
package main
import "fmt"
const MAXL = 1e5+5
const MAXD = 105
const MOD = 1e9+7
var n int
var D int
var d [MAXL]int
var T [MAXL][MAXD]int
func solve(i int, prefix int, isEqual bool) int {
    if i == n {
        if prefix == 0 {
            return 1
        } else {
            return 0
        }
    }
    if !isEqual && T[i][prefix] >= 0 {
        return T[i][prefix]
    }
    maxD := 9
    if isEqual {
        maxD = d[i]
    }
    var sum int64
    for digit := 0; digit <= maxD; digit++ {
        sum += int64(solve(i + 1, (prefix + digit) % D, isEqual && digit == maxD))
    }
    sum %= MOD
    if !isEqual {
        T[i][prefix] = int(sum)
    }
    return int(sum)
}
func main() {
    var K string
    fmt.Scanln(&K)
    fmt.Scanln(&D)
    n = len(K)
    for i := 0; i < n; i++ {
        d[i] = int(K[i] - '0')
    }
    for i := 0; i < n; i++ {
        for j := 0; j < D; j++ {
            T[i][j] = -1
        }
    }
    fmt.Println((solve(0, 0, true) - 1 + MOD) % MOD)
}
