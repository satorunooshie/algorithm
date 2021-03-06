package main
import (
    "bufio"
    "math"
    "fmt"
    "os"
    "sort"
)
const (
    SCANNER_BUF_SIZE = 2 << 20
    INF = int64(math.MaxInt64)
    MOD = 1e9 + 7
)
var (
    writer *bufio.Writer
    scanner *bufio.Scanner
)
func init() {
    writer = bufio.NewWriter(os.Stdout)
    scanner = bufio.NewScanner(os.Stdin)
    scanner.Split(bufio.ScanWords)
    scanner.Buffer(make([]byte, SCANNER_BUF_SIZE), SCANNER_BUF_SIZE)
}
func printf(f string, a ...interface{}) {
    fmt.Fprintf(writer, f, a...)
}
func sint64() int64 {
    scanner.Scan()
    bb := scanner.Bytes()
    i := int64(0)
    if bb[0] == '-' {
        for _, b := range bb[1:] {
            i *= 10
            i -= int64(b - '0')
        }
        return i
    }
    for _, b := range bb {
        i *= 10
        i += int64(b - '0')
    }
    return i
}
func suint64() uint64 {
    scanner.Scan()
    bb := scanner.Bytes()
    i := uint64(0)
    for _, b := range bb {
        i *= 10
        i += uint64(b - '0')
    }
    return i
}
func sstring() string {
    scanner.Scan()
    return scanner.Text()
}
func gcd(a, b int64) int64 {
    for b != 0 {
        a, b = b, a % b
    }
    return a
}
func lcm(a, b int64) int64 {
    return a * b / gcd(a, b)
}
func abs(a int64) int64 {
    if a < 0 {
        return a * -1
    }
    return a
}
func min(a int64, rest ...int64) int64 {
    for _, r := range rest {
        if r < a {
            a = r
        }
    }
    return a
}
func max(a int64, rest ...int64) int64 {
    for _, r := range rest {
        if r > a {
            a = r
        }
    }
    return a
}
func main() {
    defer writer.Flush()
    n := sint64()
    a := make([]int64, n)
    for i := range a {
        a[i] = sint64()
    }
    printf("%d\n", sol(a))
}
func sol(a []int64) int64 {
    if len(a) < 2 {
        return 0
    }
    sort.Slice(a, func(i, j int) bool {
        return a[i] < a[j]
    })
    ans := int64(0)
    count := 0;
    p :=len(a) - 1
    for count < len(a) - 1 {
        ans += a[p]
        count++
        if count % 2 == 1 {
            p--
        }
    }
    return ans
}
