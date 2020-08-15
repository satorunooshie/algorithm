package main
import (
    "fmt"
)

func main() {
    var N int
    fmt.Scan(&N)
    if N % 1000 == 0 {
        fmt.Println(0)
    } else {
        fmt.Println(1000 - N % 1000)
    }
}
