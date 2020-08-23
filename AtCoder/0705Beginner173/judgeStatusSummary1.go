package main
import (
    "bufio"
    "fmt"
    "os"
)
func main() {
    scanner := bufio.NewScanner(os.Stdin)
    var n, ac, wa, tle, re int
    scanner.Scan()
    fmt.Sscan(scanner.Text(), &n)
    for i := 0; i < n; i++ {
        scanner.Scan()
        switch scanner.Text() {
            case "AC":
                ac++
            case "WA":
                wa++
            case "TLE":
                tle++
            case "RE":
                re++
        }
    }
    fmt.Printf("AC * %d\n", ac)
    fmt.Printf("WA * %d\n", wa)
    fmt.Printf("TLE * %d\n", tle)
    fmt.Printf("RE * %d\n", re)
}
