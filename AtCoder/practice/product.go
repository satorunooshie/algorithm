package main
import "fmt"
func main() {
    var a, b int
    fmt.Scanf("%d %d", &a, &b)
    if a * b % 2 == 0 {
        fmt.Printf("Even")
    } else {
        fmt.Printf("Odd")
    }
}
