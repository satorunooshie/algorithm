package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
)
var sc = bufio.NewScanner(os.Stdin)

func init() {
    sc.Split(bufio.ScanWords)
    sc.Buffer(make([]byte, 1024 * 1024), bufio.MaxScanTokenSize)
}

func nextInt() int {
    sc.Scan()
    i, e := strconv.Atoi(sc.Text())
    if e != nil {
        panic(e)
    }
    return i
}

func Abs(x int) int {
    if x < 0 {
        return -x
    }
    return x
}

func main() {
    n := nextInt()
    t := make([]int, n + 1)
    x := make([]int, n + 1)
    y := make([]int, n + 1)
    t[0] = 0
    x[0] = 0
    y[0] = 0

    for i := 1; i <= n; i++ {
        t[i] = nextInt()
        x[i] = nextInt()
        y[i] = nextInt()
    }

    for i := 1; i <= n; i++ {
        dt := t[i] - t[i - 1]
        dx := Abs(x[i] - x[i - 1])
        dy := Abs(y[i] - y[i - 1])

        if dx + dy == 0 {
            fmt.Println("No")
            return
        } else if dt >= dx + dy {
            if (dt - dx - dy) % 2 == 0 {
                continue
            } else {
                fmt.Println("No")
                return
            }
        } else {
            fmt.Println("No")
            return
        }
    }
    fmt.Println("Yes")
}
