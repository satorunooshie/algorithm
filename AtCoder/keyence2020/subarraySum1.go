package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
)
func main() {
    var n, k, s int
    fmt.Scan(&n, &k, &s)

    const d9 = 1000000000
    var x, y int
    if s == d9 {
        x = d9
        y = 1
    } else {
        x = s
        y = d9
    }
    var wr = bufio.NewWriter(os.Stdout)
    for i := 0; i < k; i++  {
        wr.WriteString(strconv.Itoa(x))
        if i < k - 1 {
            wr.WriteString(" ")
        }
    }
    for i := 0; i < n - k; i++ {
        wr.WriteString(" ")
        wr.WriteString(strconv.Itoa(y))
    }
    wr.WriteString("\n")
    wr.Flush()
}
