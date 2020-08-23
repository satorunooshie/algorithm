package main
import "fmt"
func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}

func main() {
    var H, W, N, P int
    fmt.Scan(&H, &W, &N)
    P = max(H, W)
    fmt.Println((N + P - 1) / P)
}

