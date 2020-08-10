package main
import (
    "bufio"
    "os"
    "strconv"
    "strings"
    "fmt"
)
func Solve() {
    var N, d int
    NextInt(&N)
    ans := 0
    for k := 1; k <= N; k++ {
        d = N / k
        if d > 1 {
            ans += k * d * (d + 1) / 2
        } else {
            ans += (N - k) * (N - k + 1) / 2 + k * (N - k + 1)
            break
        }
    }
    Write(ans)
}

func main() {
    Solve()
    Output()
}

var reader = bufio.NewReaderSize(os.Stdin, 1000000)
var writer = bufio.NewWriter(os.Stdout)
func NextLine() string {
    buffer := make([]byte, 0)
    for true {
        line, isPrefix, err := reader.ReadLine()
        if err != nil {
            panic(err)
        }
        buffer = append(buffer, line...)
        if !isPrefix {
            break
        }
    }
    return string(buffer)
}

func NextInt(A ...*int) {
    L := strings.Split(NextLine(), " ")
    for i, a := range A {
        *a, _ = strconv.Atoi(L[i])
    }
}

func NextIntSlice(A *[]int) {
    L := strings.Split(NextLine(), " ")
    (*A) = make([]int, len(L))
    for i, l := range L {
        (*A)[i], _ = strconv.Atoi(l)
    }
}

func NextFloat(A ...*float64) {
    L := strings.Split(NextLine(), " ")
    for i, a := range A {
        *a, _ = strconv.ParseFloat(L[i], 64)
    }
}

func NextFloatSlice(A *[]float64) {
    L := strings.Split(NextLine(), " ")
    (*A) = make([]float64, len(L))
    for i, l := range L {
        (*A)[i], _ = strconv.ParseFloat(l, 64)
    }
}

func Write(S ...interface{}) {
    fmt.Fprintln(writer, S...)
}

func WriteIntSlice(A []int) {
    B := make([]interface{}, len(A))
    for i, a := range A {
        B[i] = a
    }
    Write(B...)
}

func Output() {
    _ = writer.Flush()
}

func MaxInt(A ...int) int {
    max := A[0]
    for _, a := range A {
        if max < a {
            max = a
        }
    }
    return max
}

func MinInt(A ...int) int {
    min := A[0]
    for _, a := range A {
        if a < min {
            min = a
        }
    }
    return min
}

func sumInt(A ...int) int {
    sum := 0
    for _, a := range A {
        sum += a
    }
    return sum
}

func AbsInt(x int) int {
    if x < 0 {
        x = -x
    }
    return x
}
