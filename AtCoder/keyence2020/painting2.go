package main
import (
    "fmt"
)
func main() {
    var h, w, n int
    fmt.Scanf("%d", &h)
    fmt.Scanf("%d", &w)
    fmt.Scanf("%d", &n)
    ans := 0
    if h > w {
        ans = n / h
        if n % h > 0 {
            ans++
        }
    } else {
        ans = n / w
        if n % w > 0 {
            ans++
        }
    }
    fmt.Println(ans)
}
