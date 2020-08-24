/*
N頂点M辺の連結な無向グラフがあります
このグラフの辺i(1<=i<=M)は頂点U_iと頂点V_iを双方向に結んでいます
またN個の整数D_1, ...,D_2が与えられます
このグラフの各頂点に白または黒の色を割り当て
さらに各辺に1以上10^9以下の整数の重みを割り当てる方法であって
以下の条件を満たすものが存在するかどうか判定してくださいv
さらに存在する場合、そのような割り当てを一つ求めてください
白及び黒が割り当てられた頂点がそれぞれ少なくとも一個以上存在する
各頂点v(1<=v<=N)に対して以下の条件が成り立つ
頂点vからグラフの辺を通って頂点vと異なる色が割り当てられた頂点に
移動する際にかかる最小のコストはちょうでD_vである
なおグラフ上の移動にかかるコストとは移動の際に通る辺の重みの和のことです
2<=N<=100,000
1<-M<-200,000
1<-D_i<=10^9
1<=U_i,V_i<=N
与えられるグラフは連結であり
自己ループや多重辺を持たない
入力値はすべて整数である
*/
package main
import (
    "bufio"
    "os"
    "strconv"
    "strings"
    "fmt"
    "sort"
)
var reader = bufio.NewReaderSize(os.Stdin, 1000000)
var writer = bufio.NewWriter(os.Stdout)
func NextLine() string {
    buffer := make([]byte, 0)
    for true {
        line, isPrefix, err := reader.ReadLine()
        if err != nil { panic(err) }
        buffer = append(buffer, line...)
        if !isPrefix { break }
    }
    return string(buffer)
}
func NextInt() int {
    n, _ := strconv.Atoi(NextLine())
    return n
}
func NextIntVec() []int {
    L := strings.Split(NextLine(), " ")
    N := make([]int, len(L))
    for i := range N {
        N[i], _ = strconv.Atoi(L[i])
    }
    return N
}
func Write(s interface{}) {
    fmt.Fprintln(writer, s)
}
func WriteIntVec(A []int) {
    S := make([]string, len(A))
    for i, a := range A {
        S[i] = strconv.Itoa(a)
    }
    Write(strings.Join(S, " "))
}
func Output() {
    _ = writer.Flush()
}
type Node [][]int
func (N Node) Len() int { return len(N) }
func (N Node) Less(i, j int) bool { return N[i][0] < N[j][0] }
func (N Node) Swap(i, j int) { N[i], N[j] = N[j], N[i] }
func DFC(V *[]string, H *[][]int, v int) {
    for _, adj := range (*H)[v] {
        if (*V)[adj] != "" { continue }
        if (*V)[v] == "B" {
            (*V)[adj] = "W"
        } else {
            (*V)[adj] = "B"
        }
        DFC(V, H, adj)
    }
}
func Solve() {
    NM := NextIntVec()
    N, M := NM[0], NM[1]
    D := NextIntVec()
    G := make([][][]int, N)
    for i := range G {
        G[i] = make([][]int, 0)
    }
    E := make([]int, M)
    for i := range E {
        UV := NextIntVec()
        u, v := UV[0] - 1, UV[1] - 1
        G[u] = append(G[u], []int{v, i})
        G[v] = append(G[v], []int{u, i})
    }
    V := make([]string, N)
    H := make([][]int, N)
    for i := range H {
        H[i] = make([]int, 0)
    }
    for u, g := range G {
        sort.Sort(Node(g))
        adj := u
        for _, v := range g {
            if D[u] < D[v[0]] { continue }
            adj = v[0]
            E[v[1]] = D[u]
            H[u] = append(H[u], v[0])
            H[v[0]] = append(H[v[0]], u)
            break
        }
        if adj == u {
            Write(-1)
            return
        }
    }
    for v := range V {
        if V[v] == "" { V[v] = "B" }
        DFC(&V, &H, v)
    }
    Write(strings.Join(V, ""))
    for i, e := range E {
        if e < 1 { E[i] = 10000000000 }
        Write(E[i])
    }
    return
}
func main() {
    Solve()
    Output()
}
