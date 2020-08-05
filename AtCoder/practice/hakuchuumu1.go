package main
import (
    "bufio"
    "fmt"
    "os"
    "strings"
)

var sc = bufio.NewScanner(os.Stdin)

func init() {
    sc.Split(bufio.ScanWords)
    sc.Buffer(make([]byte, 1024 * 1024), bufio.MaxScanTokenSize)
}

func nextString() string {
    sc.Scan()
    return sc.Text()
}

func main() {
    s := nextString()
    //dreameraser
    //dreamerase
    //dream
    //eraser
    //erase
    for len(s) >= 5 {
        if strings.HasPrefix(s, "dreameraser") {
            s = s[11:]
            continue
        } else if strings.HasPrefix(s, "dreamerase") {
            s = s[10:]
            continue
        } else if strings.HasPrefix(s, "dreamer") {
            s = s[7:]
            continue
        } else if strings.HasPrefix(s, "dream") {
            s = s[5:]
            continue
        } else if strings.HasPrefix(s, "eraser") {
            s = s[6:]
            continue
        } else if strings.HasPrefix(s, "erase") {
            s = s[5:]
            continue
        } else {
            fmt.Println("NO")
            return
        }
    }
    if len(s) == 0 {
        fmt.Println("YES")
        return
    }
    fmt.Println("NO")
    return
}
