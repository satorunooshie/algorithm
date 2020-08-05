package main
import (
    "fmt"
    "bufio"
    "os"
)
func main() {
    r := bufio.NewReader(os.Stdin)
    w := bufio.NewWriter(os.Stdout)
    defer w.Flush()
    var S string
    fmt.Fscan(r, &S)
    dp := make([]bool,len(S) + 1)
    dp[0] = true
    for i := 0; i < len(S); i++ {
        if dp[i] {
            if i < len(S) - 4 {
                s := S[i : i + 5]
                if s == "dream" || s == "erase" {
                    dp[i + 5] = true
                }
            }
            if i < len(S) - 5 {
                s := S[i : i + 6]
                if s == "eraser" {
                    dp[i + 6] = true
                }
            }
            if i < len(S) - 6 {
                s := S[i : i + 7]
                if s == "dreamer" {
                    dp[i + 7] = true
                }
            }
        }
    }
    if dp[len(S)] {
        fmt.Fprintln(w, "YES")
    } else {
        fmt.Fprintln(w, "NO")
    }
}


