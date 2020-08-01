package main
import "fmt"
func main() {
    var line string
    count := 0
    _, _ = fmt.Scanf("%s", &line)
    for _, v := range []rune(line) {
        if string(v) == "1" {
            count++
        }
    }
    fmt.Printf(count)
}
