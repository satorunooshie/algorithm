package main
import (
    "fmt"
    "sort"
)
func main() {
    var n int
    fmt.Scan(&n)
    a := make([]int, n)
    for i := 0; i < n; i++ {
        fmt.Scan(&a[i])
    }
    sort.Sort(sort.Reverse(sort.IntSlice(a)))
    alice := 0
    bob := 0
    for i, v := range a {
        if i % 2 == 0 {
            alice += v
        } else {
            bob += v
        }
    }
    fmt.Println(alice - bob)
}
