package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
    "strings"
)
func main() {
    var n int
    fmt.Scanf("%d", &n)
    stdin := bufio.NewScanner(os.Stdin)
    stdin.Scan()
    s := stdin.Text()
    split := strings.Split(s, " ")
    list := convStringListToIntList(split)
    count := 0
    for {
        if !isEvenNumberList(list) {
            break
        }
        list = divideByTwo(list)
        count += 1
    }
    fmt.Println(count)
}
func convStringListToIntList(list []string) []int {
    newList := make([]int, len(list))
    for i, v := range list {
        a, _ := strconv.Atoi(v)
        newList[i] = a
    }
    return newList
}
func divideByTwo(list []int) []int {
    newList := make([]int, len(list))
    for i, v := range list {
        newList[i] = v / 2
    }
    return newList
}
func isEvenNumberList(list []int) bool {
    result := true
    for _, v := range list {
        if v % 2 != 0 {
            result = false
            break
        }
    }
    return result
}
