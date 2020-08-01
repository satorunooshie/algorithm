package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
)
var b = bufio.NewScanner(os.Stdin)
func scanInt() int {
    b.Scan()
    num, err := strconv.Atoi(b.Text())
    if err != nil {
        fmt.Println(err)
    }
    return num
}
func scanIntN(n int) []int {
    nums := make([]int, 0, n * 2)
    for i := 0; i < n; i++ {
        ret := scanInt()
        nums = append(nums, ret)
    }
    return nums
}
func main() {
    b.Split(bufio.ScanWords)
    n := scanInt()
    array := scanIntN(n)
    c := 0
    flag := true
    for {
        if flag {
            for n, num := range array {
                if num % 2 == 0 {
                    array[n] = num / 2
                } else {
                    fmt.Println(c)
                    flag = false
                    break
                }
            }
        } else {
            break
        }
        c++
    }
}
