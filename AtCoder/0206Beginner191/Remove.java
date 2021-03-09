import java.util.*;
import java.io.*;

import static java.lang.Math.*;

/**
 * 長さNの整数列Aと整数Xが与えられます。
 * AからXと等しい要素を全て取り除き、残った要素をそのままの順序で並べた数列A′を出力してください。
 * 標準入力
 * N X
 * A A A ... A
 */
public class Remove {
    static Scanner sc;
    static PrintWriter out;

    static void solve() {
        // 入力
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // 計算
        String result = "";
        for (int i = 0; i < n; i++) {
            if (a[i] == x) continue;
            out.print(a[i] + " ");
        }

        // 出力
        out.println(result);
    }

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        solve();
        out.flush();
    }

    public static class Scanner {
        static InputStream is;
        static int lenbuf = 0, ptrbuf = 0;
        private byte[] inbuf = new byte[1024];

        public Scanner(InputStream is) {
            this.is = is;
        }

        private int readByte() {
            if (lenbuf == -1) throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0) return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b)) ;
            return b;
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char nextChar() {
            return (char) skip();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }
}