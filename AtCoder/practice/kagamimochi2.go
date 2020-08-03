package main
import (
    "fmt"
)
func main() {
    var N int
    fmt.Scanf("%d", &N)
    A := make([]int, N)
    for i := range A {
        fmt.Scanf("%d", &A[i])
    }
    m := make(map[int]bool)
    for _, v := range A {
        m[v] = true
    }
    fmt.Println(len(m))
}
