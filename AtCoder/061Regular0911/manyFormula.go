import (
	"fmt"
	"strconv"
)

func main() {
	var S string
	fmt.Scan(&S)

	count := 0
	for i := 0; i < int(1<<uint32(len(S))); i++ {
		tmp := i
		cur := tmp & 1
		start := 0
		for j := 0; j < len(S); j++ {
			if cur != tmp&1 {
				val, _ := strconv.ParseInt(S[start:j], 10, 64)
				count += int(val)
				start = j
			}
			tmp >>= 1
		}
		val, _ := strconv.ParseInt(S[start:len(S)], 10, 64)
		count += int(val)
	}
	fmt.Println(count / 2)
}