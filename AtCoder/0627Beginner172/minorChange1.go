package main
import (
    "fmt"
    "bufio"
    "os"
    "strconv"
    "strings"
)
var rdr = bufio.NewReaderSize(os.Stdin, 1000000)
func readLine() (ret string) {
    buf := make([]byte, 0, 1000000)
    for {
        l, p, e := rdr.ReadLine()
        if e != nil {
            return ""
        }
        buf = append(buf, l...)
        if !p {
            break
        }
    }
    return string(buf)
}
type Utls struct {
}
type uString string
var utls = &Utls{}
func (u *Utls) readLineU() uString {
    return uString(readLine())
}
func (u *Utls) atoi(str string) int {
    r, _ := strconv.Atoi(str)
    return r
}
func (u *Utls) itoa(n int) string {
    r := strconv.Itoa(n)
    return r
}
func (u uString) atoi() int {
    r, err := strconv.Atoi(string(u))
    if err != nil {
        return -1
    }
    return r
}
func (u uString) atoiArr() []int {
    ret := make([]int, 0)
    l := strings.Split(string(u), " ")
    for i := 0; i < len(l); i++ {
        s := (uString(l[i]))
        ret = append(ret, s.atoi())
    }
    return ret
}
func (u uString) String() string {
    return string(u)
}
func main() {
    s := readLine()
    t := readLine()
    cnt:= 0
    for i, _ := range s {
        if s[i] != t[i] {
            cnt++
        }
    }
    //r := (a) + (a * a) + (a * a * a)
    fmt.Println(cnt)
}
