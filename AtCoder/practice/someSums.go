package main
import "fmt"
func main() {
    var (N, A, B, sum int)
    fmt.Scan(&N, &A, &B)
    for i := 1; i <= N; i++ {
        wk := i
        total := 0
        for wk > 0 {
            total += wk % 10
            wk /= 10
            //intだから小数点以下切り捨て
        }
        if total <= B && A <= total {
            sum += i
        }
    }
    fmt.Println(sum)
}
