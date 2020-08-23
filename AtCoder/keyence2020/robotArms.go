package main
import (
    "bufio"
    "fmt"
    "os"
    "sort"
    "strconv"
)
var sc = bufio.NewScanner(os.Stdin)
func scanInt() int {
    sc.Scan()
    iv, _ := strconv.Atoi(sc.Text())
    return iv
}
func init() {
    sc.Split(bufio.ScanWords)
}
func main() {
    n := scanInt()
    type arm struct{ l, r int }
    as := make([]arm, n)
    for i := range as {
        x, l := scanInt(), scanInt()
        as[i].l = x - l
        as[i].r = x + l
    }
    sort.Slice(as, func(i, j int) bool { return as[i].r < as[j].r })
    ans := 1
    r := as[0].r
    for i := 1; i < n; i++ {
        if as[i].l >= r {
            ans++
            r = as[i].r
        }
    }
    fmt.Println(ans)
}
