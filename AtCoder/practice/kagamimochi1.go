package main
import (
    "fmt"
    "sort"
)
func main() {
    var N, count int
    fmt.Scan(&N)
    mochi := make([]int, N)
    for (i := range mochi {
        fmt.Scan(&mochi[i])
    }
    sort.Ints(mochi)
    //fmt.Println(mochi)
    for i := range mochi {
        if i > 0 {
            if mochi[i - 1] < mochi[i] {
                count++
            }
        } else {
            count++
        }
    }
    fmt.Println(count)
}
