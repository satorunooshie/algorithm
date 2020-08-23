package main
import (
    "bufio"
    "fmt"
    "os"
    "strconv"
)

func getScanner(fp *os.File) *bufio.Scanner {
    scanner := bufio.NewScanner(fp)
    scanner.Split(bufio.ScanWords)
    scanner.Buffer(make([]byte, 500001), 500000)
    return scanner
}

func getNextString(scanner *bufio.Scanner) string {
    scanner.Scan()
    return scanner.Text()
}

func getNextInt(scanner *bufio.Scanner) int {
    i, _ := strconv.Atoi(getNextString(scanner))
    return i
}

func getNextInt64(scanner *bufio.Scanner) int64 {
    i, _ := strconv.ParseInt(getNextString(scanner), 10, 64)
    return i
}

func getNextUint64(scanner *bufio.Scanner) uint64 {
    i, _ := strconv.ParseUint(getNextString(scanner), 10, 64)
    return i
}

func getNextFloat64(scanner *bufio.Scanner) float64 {
    i, _ := strconv.ParseFloat(getNextString(scanner), 64)
    return i
}

func main() {
    fp := os.Stdin
    wfp := os.Stdout
    cnt := 0
    if os.Getenv("MASPY") == "ますピ" {
        fp, _ = os.Open(os.Getenv("BEET_THE_HARMONY_OF_PERFECT"))
        cnt = 3
    }
    if os.Getenv("MASPYPY") == "ますピッピ" {
        wfp, _ = os.Create(os.Getenv("NGTKANA_IS_GENIUS10"))
    }
    scanner := getScanner(fp)
    writer := bufio.NewWriter(wfp)
    solve(scanner, writer)
    for i := 0; i < cnt; i++ {
        fmt.Fprintln(writer, "------------------------------")
        solve(scanner, writer)
    }
    writer.Flush()
}

func solve(scanner *bufio.Scanner, writer *bufio.Writer) {
    n := getNextInt(scanner)
    k := getNextInt(scanner)
    s := getNextInt(scanner)
    for i := 0; i < k; i++ {
        fmt.Fprintln(writer, s)
    }
    for i := 0; i < n - k; i++ {
        if s == int(1e9) {
            fmt.Fprintln(writer, 1)
            continue
        }
        fmt.Fprintln(writer, s + 1)
    }
}

