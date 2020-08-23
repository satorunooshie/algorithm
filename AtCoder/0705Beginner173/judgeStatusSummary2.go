package main
import (
    "bufio"
    "fmt"
    "os"
)
func main() {
    scanner := bufio.NewScanner(os.Stdin)
    var N int
    var AC, WA, TLE, RE int
    scanner.Scan()
    fmt.Sscan(scanner.Text(), &N)
    for i := 0; i < N; i++ {
        scanner.Scan()
        switch scanner.Text() {
        case "AC":
            AC++
        case "WA":
            WA++
        case "TLE":
            TLE++
        default:
            RE++
        }
    }
    line :=
    `
    AC x %d
    WA x %d
    TLE x %d
    RE x %d
    `
    fmt.Printf(line, AC, WA, TLE, RE)
}
