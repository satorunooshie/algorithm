package main
import "fmt"
func main() {
    var (A,B,C,N,count int)
    fmt.Scan(&A,&B,&C,&N)
    for i := 0; i <= A; i++ {
        for j := 0; j<= B; j++ {
            for k := 0; k <= C; k++ {
                if i * 500 + j * 100 + k * 50 == N {
                    count++
                }
            }
        }
    }
    fmt.Println(count)
}
