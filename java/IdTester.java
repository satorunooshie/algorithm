/**
 * クラスメソッドはクラス全体に関わる処理やそのクラスに所属する個々のインスタンスの状態とは無関係な処理を実現するためのメソッド
 */
class Id {
    private static int counter = 0;
    private int id;

    /**
     * [++a]
     * 前置増分演算子は式全体の評価が行われる前にオペランドの値がインクリメントされる
     * そのためa=3, b=++aとすると、まずaがインクリメントされて4となり、それから式++aを評価した値である4がbに代入される
     * よってa=b=4
     *
     * [a++]
     * 後置増分演算子は式全体の評価が行われた後にオペランドの値がインクリメントされる
     * そのためa=3, b=a++とするとまずa++を評価した値3がbに代入され、それからインクリメントが行われてaの値が4となる
     * よってa=4, b=3
     */
    public Id() {
        id = ++counter;
    }
    public static int getCounter() {
        return counter;
    }
    public int getId() {
        return id;
    }
}

/**
 * インスタンスとは無関係に一個のみが作られるクラス変数counterは現在までに何番までの識別番号を与えたのかを示し
 * インスタンスごとに一個ずつ割り当てられるインスタンス変数idはそのインスタンスの識別番号を表す
 */
public class IdTester {
    public static void main(String[] args) {
        Id a = new Id();
        Id b = new Id();
        System.out.println("aの識別番号:" + a.getId());
        System.out.println("bの識別番号:" + b.getId());
        System.out.println("最後に与えた識別番号:" + Id.getCounter());
    }
}