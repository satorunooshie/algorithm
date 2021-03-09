import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 黒板にN個の整数A1,A2,A3,…,ANが書かれています。
 * あなたは次の操作をN−1回行います。
 * 黒板に書かれている数を2つ選んで消す。消した数をxとyとして、gcd(x, y)とmin(x, y)のどちらか一方を黒板に書く
 * N−1回の操作を終えた後、黒板にはただ一つの整数が残りますが、この整数として考えられるものはいくつありますか?
 * 標準入力
 * N
 * A A A ... A
 */
public class GcdOrMin {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] sa = br.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(sa[i]);
        }
        br.close();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = divList(a[i]);
            for (Integer e : list) {
                map.put(e, gcd(map.getOrDefault(e, 0), a[i]));
            }
        }

        int min = a[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, a[i]);
        }

        int ans = 0;
        for (Integer k : map.keySet()) {
            if (k <= min && k.equals(map.get(k))) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static List<Integer> divList(int n) {
        List<Integer> list = new ArrayList<>();
        int end = (int) Math.sqrt(n);
        for (int i = 1; i <= end; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        int i = end * end == n ? list.size() - 2 : list.size() - 1;
        for (; i >= 0; i--) {
            list.add(n / list.get(i));
        }
        return list;
    }
}