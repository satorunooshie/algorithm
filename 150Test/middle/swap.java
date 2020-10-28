/* 一時変数を使わずに値を入れ替える関数を書く */
/*
   aの元の値をa_1、bの元の値をb_1として考え
   その差も使う
   まず数直線の右側の値、diffをaに一時的に代入する
   続いてdiffにbを加えその値をbに代入するとa_1が得られる
   これでb=a_1、a=diffになる
   最後にa=a_1-diff、つまりb-aに代入すれば完了
*/
public static void swap(int a, int b) {
    //ex a = 9, b = 4
    a = a - b;
    b = a + b;
    a = b - a;
    System.out.println("a: " + a);
    System.out.println("b: " + b);
}
//ビット操作
//整数型以外にも対応する
public static void swap_opt(int a, int b) {
    //a = 101, b = 110
    a = a^b; //a = 101^110 = 011
    b = a^b; //b = 011^110 = 101
    a = a^b; //a = 011^101 = 110
    System.out.println("a: " + a);
    System.out.println("b: " + b);
}
