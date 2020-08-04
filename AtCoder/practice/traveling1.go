package main
import (
    "fmt"
    "strconv"
    "strings"
    "math"
    "os"
    "bufio"
)
var sc = bufio.NewScanner(os.Stdin)
func nextLine() string {
    sc.Scan()
    return sc.Text()
}
func main() {
    n, _ := strconv.Atoi(nextLine())
    var t, x, y int
    var tc, xc, yc int
    for i := 0; i < n; i++ {
        s := nextLine()
        arrStr := strings.Split(s, " ")
        t, _ = strconv.Atoi(arrStr[0])
        x, _ = strconv.Atoi(arrStr[1])
        y, _ = strconv.Atoi(arrStr[2])
        tdiff := t - tc
        xydiff := int(math.Abs(float64(x - xc)) + math.Abs(float64(y - yc)))
        txydiff := tdiff - xydiff
        if txydiff == 0 || (txydiff > 0 && txydiff % 2 == 0) {
            tc = t
            xc = x
            yc = y
        } else {
            fmt.Println("No")
            return
        }
    }
    fmt.Println("Yes")
}
