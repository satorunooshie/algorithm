package main
import (
    "fmt"
)
func main() {
    var h, w, k int
    ans := 0
    fmt.Scan(&h, &w, &k)
    c := make([]string, h)
    for i := 0; i < h; i++ {
        fmt.Scan(&c[i])
    }

    //赤に塗る行列のパターン
    for y := 0; y < 1 << uint(h); y++ {
        for x := 0; x < 1 << uint(w); x++ {
            //赤く塗られないマス目の黒の数をカウントする
            count := 0
            for q := 0; q < h; q++ {
                for p := 0; p < w; p++ {
                    if (y >> uint(q)) & 1 == 0 && (x >> uint(p)) & 1 == 0 {
                        if string(c[q][p]) == "#" {
                            count++
                        }
                    }
                }
            }
            if count == k {
                ans++
            }
        }
    }
    fmt.Println(ans)
}
