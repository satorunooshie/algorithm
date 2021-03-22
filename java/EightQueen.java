package java;

/**
 * The type Eight queen.
 */
class EightQueen {
    /*
     * i列目に配置されている王妃の位置がj行目であればpos[i]の値をjとする
     * 0000000は全ての王妃が0行目に配置されていることを表す
     *
     * [方針1]
     * 各行に一個の王妃を配置する組み合わせを再帰的に列挙
     * 次々と枝分かれを作っていくことによって配置の組み合わせを列挙するので分枝操作という
     */

    /**
     * The constant pos.
     */
// 各行の王妃の位置
    static int[] pos = new int[8];
    /**
     * [方針2]
     * 各行には王妃を一個だけ配置
     * (8飛車問題) 王妃が行方向と列方向に重複しない組み合わせを列挙するので
     * 必要のない枝分かれを制御して不要な組み合わせの列挙を省くので限定操作という
     * 分岐操作と限定操作を組み合わせて解くのが分岐限定法
     */
// 同一行に重複して王妃を配置しないようにするために設置
    // j行目に王妃が配置済みであればflag[j]をtrueとし、未配置であればfalseとする
    static boolean[] flag = new boolean[8];
    /**
     * The constant set_line.
     */
    /*
     * 8王妃問題
     * 王妃は斜め方向のコマも取れるので
     * どの斜めライン上にも王妃を1個のみ配置するための限定操作の追加採用が必要
     */
    // 各行に王妃が配置ずみか*上のflagと同義
    static boolean[] set_line = new boolean[8];
    /**
     * The constant set_r_diagonal.
     */
// 対角線／に王妃が配置済みか
    static boolean[] set_r_diagonal = new boolean[15];

    // static int[] pos = new int[8];

    /**
     * The constant set_l_diagonal.
     */
// 対角線\に王妃が配置済みか
    static boolean[] set_l_diagonal = new boolean[15];

    /**
     * Print.
     */
// 各列の王妃の位置を出力
    static void print() {
        for (int i = 0; i < 8; i++)
            System.out.printf("%2d", pos[i]);
        System.out.println();
    }

    /**
     * Print signal.
     */
    static void printSignal() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.printf("%s", j == pos[i] ? "■ " : "□ ");
            System.out.println();
        }
    }

    /**
     * pos[i]に0から7までの値を順次代入することによって
     * i列に王妃を1個だけ配置する8通りの組み合わせを生成する
     * iは列 jは行
     *
     * @param i the
     */
    static void set1(int i) {
        for (int j = 0; j < 8; j++) {
            pos[i] = j;
            // 全列に配置終了
            if (i == 7)
                print();
            else
                // 次の*列*に王妃を配置
                set1(i + 1);
        }
    }

    /**
     * 0列目に王妃を配置するために呼び出すこのメソッドはflag[0] == falseなので0行目にまず王妃を配置する
     * その際あらかじめflag[0] = trueとしておき、それからメソッドを再帰的に呼び出す
     * 呼び出されたメソッドで1列目への配置を考える
     * for文の繰り返しでは0~7行目への配置を行う
     * 0行目はflag[0] == trueなので再帰呼び出しは行われない
     * その結果262,144個が省略される
     * 1行目はflag[1] == falseなので再帰呼び出しによって2列目への配置が行われる
     * 但し、再帰的に呼び出したメソッドset(i + 1)から戻ってきたときには
     * 王妃をj行目から取り除くためにflag[j] = falseにする
     *
     * @param i the
     */
    static void set2(int i) {
        for (int j = 0; j < 8; j++) {
            // j行には王妃は未配置
            if (!flag[j]) {
                pos[i] = j;
                // 全列配置終了(生成時にfalseで初期化)
                if (i == 7)
                    print();
                else {
                    flag[j] = true;
                    set2(i + 1);
                    flag[j] = false;
                }
            }
        }
    }

    //static int[] pos = new int[8];

    /**
     * set2では同じ行の左隣に王妃が未配置だから(flag[1] == false)
     * 1列目1行目に王妃を配置したが、
     * 今回は1列目の1行目への王妃の配置は行わない
     * set_l_diagonal[7]がtrueで0列目の0行目に王妃が配置済みのため
     *
     * @param i the
     */
    static void set3(int i) {
        for (int j = 0; j < 8; j++) {
            /*
             * 横(j行)に未配置
             * 対角線/に未配置
             *  j行i列の値(インデックス0~14)はi + jによって得られる
             * 対角線\に未配置
             *  j行i列の値(インデックス0~14)はi - j + 7によって得られる
             *
             * 各コマに対して王妃の配置を検討する場合は
             * 同一行に加えて対角線上に王妃が配置されていないかの判定も必要
             */
            if (!set_line[i] &&
                    !set_r_diagonal[i + j] &&
                    !set_l_diagonal[i - j + 7]) {
                pos[i] = j;
                if (i == 7)
                    printSignal();
                else {
                    set_line[j] = set_r_diagonal[i + j] = set_l_diagonal[i - j + 7] = true;
                    set3(i + 1);
                    set_line[j] = set_r_diagonal[i + j] = set_l_diagonal[i - j + 7] = false;
                }
            }
        }
    }

    /**
     * Sets without recur.
     *
     * FIXME:
     *
     * @param i the
     */
    static void setWithoutRecur(int i) {
        int j;
        int[] jstk = new int[8];
        Start:
        while (true) {
            j = 0;
            while (true) {
                while (j < 8) {
                    if (!set_line[j] &&
                            !set_r_diagonal[i + j] &&
                            !set_l_diagonal[i - j + 7]) {
                        pos[i] = j;
                        // 全列に配置終了
                        if (i == 7)
                            printSignal();
                        else {
                            // i列目の行をpush
                            set_line[j] = set_r_diagonal[i + j] = set_l_diagonal[i - j + 7] = true;
                            jstk[i++] = j;
                            continue Start;
                        }
                    }
                    j++;
                }
                if (--i == -1) return;
                // i行目の列をpop
                j = jstk[i];
                set_line[j] = set_r_diagonal[i + j] = set_l_diagonal[i - j + 7] = false;
                j++;
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // 0列目に王妃を配置
        // set1(0);
        // set2(0);
        set3(0);
        // setWithoutRecur(0);
    }
}
