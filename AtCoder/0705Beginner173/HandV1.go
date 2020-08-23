package main
import "fmt"
func main() {
    var h, w, k int
    count := 0
    fmt.Scan(&h, &w, &k)
    c := make([]string, h, h)
    for i := range c {
        fmt.Scan(&c[i])
    }
    for i := 0; i < 1 << h; i++ {
        for l := 0; l < 1 << w; l++ {
            sharpNum := countSharp(c, i, l)
            if sharpNum == k {
                count++
            }
        }
    }
    fmt.Println(count)
}

func countSharp(c []string, h, w int) int {
    count := 0
    for i := 0; i < len(c); i++ {
        if (h >> i & 1) == 1 {
            continue
        }
        for k := 0; k < len(c[0]); k++ {
            if (w >> k & 1) == 1 {
                continue
            }
            if c[i][k] == '#' {
                count++
            }
        }
    }
    return count
}
