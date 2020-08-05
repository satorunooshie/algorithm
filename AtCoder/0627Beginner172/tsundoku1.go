package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
    "strings"
)
func main() {
    var n, m, k int
    fmt.Scanf("%d %d %d", &n, &m, &k)
    lines, _ := scanLongIntSlices(2, 11 * 200000)
    booksA := lines[0]
    booksB := lines[1]
    time := 0
    for _, t := range booksA {
        time += t
    }
    numA := n
    numB := 0
    maxCount := 0
    changed := true
    for changed {
        changed = false
        if time <= k && numA + numB > maxCount {
            maxCount = numA + numB
        }
        if numB < m && time <= k {
            time += booksB[numB]
            numB++
            changed = true
        } else if numA > 0 && time > k {
            time -= booksA[numA - 1]
            numA--
            changed = true
        }
    }
    fmt.Printf("%d\n", maxCount)
}

func scanIntSlice(size int) []int {
    input := []int{}
    for i := 0; i < size; i++ {
        var in int
        fmt.Scanf("%d", &in)
        input = append(input, in)
    }
    return input
}

func scanLongIntSlices(lines int, maxBufSize int) ([][]int, error) {
    res := [][]int{}
    sc := bufio.NewScanner(os.Stdin)
    buf := make([]byte, bufio.MaxScanTokenSize)
    sc.Buffer(buf, maxBufSize)

    for sc.Scan() {
        line := sc.Text()
        strs := strings.Split(line, " ")
        ints := make([]int, len(strs))
        for i, c := range strs {
            n, err := strconv.Atoi(c)
            if err != nil {
                return nil, err
            }
            ints[i] = n
        }
        res = append(res, ints)
    }
    if err := sc.Err(); err != nil {
        return nil, err
    }
    return res, nil
}
