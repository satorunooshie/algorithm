package main
import "fmt"
func main() {
    var n int
    fmt.Scan(&n)
    mod := n % 1000
    change := mod
    if mod != 0 {
        change = 1000 - mod
    }
    fmt.Println(change)
}
