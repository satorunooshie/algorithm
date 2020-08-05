package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
)
var sc = bufio.NewScanner(os.Stdin)
var out = bufio.NewWriter(os.Stdout)
func main() {
    buf := make([]byte, 1024 * 1024)
    sc.Buffer(buf, bufio.MaxScanTokenSize)
    sc.Split(bufio.ScanWords)
    defer out.Flush()
    N := nextInt()

    fmt.Fprintln(out, N + N * N + N * N * N)
}
func next() string {
    sc.Scan()
    return sc.Text()
}
func nextInt() int {
    a, _ := strconv.Atoi(next())
    return a
}
func nextInts(n int) []int {
    ret := make([]int, n)
    for i := 0; i < n; i++ {
        ret[i] = nextInt()
    }
    return ret
}
func chars(s string) []string {
    ret := make([]string, len([]rune(s)))
    for i, v := range []rune(s) {
        ret[i] = string(v)
    }
    return ret
}

