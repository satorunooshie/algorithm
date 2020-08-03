package main
import (
    "fmt"
    "sort"
)
func main() {
    var (
        n int
    )
    fmt.Scanln(&n)
    a := make([]int, n)
    for i := 0; i < n; i++ {
        var ae int
        fmt.Scan(&ae)
        a[i] = ae
    }
    sort.IntSlice(a).Sort()
    sort.Sort(sort.Reverse(sort.IntSlice(a)))
    var prod int
    for i, v := range a {
        if i % 2 == 0 {
            prod += v
        } else {
            prod -=v
        }
    }
    fmt.Println(prod)
}
