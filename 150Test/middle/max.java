/* 二つの最大値を見つけるメソッド */
/* ただしif-elseや比較演算子を使ってはいけない */
/*
   一般的な方法はa - bの符号を見ること
   比較演算子を使えないが乗算はできる
   a - b >= 0ならばkを1、そうでなければkを0とする変数kを用意する
   さらにkと異符号qを用意する
*/
//1を0に、0を1に反転する
public static int flip(int bit) {
    return 1^bit;
}
//aが正なら1を、負なら0を返す
public static int sign(int a) {
    return flip((a >> 31) & 0x1);
}
public static int getMaxNaive(int a, int b) {
    int k = sign(a - b);
    int q = flip(k);
    return a * k + b * q;
}
/*
   a - bがオーバーフローする場合はうまくいかない
   結果負の値が返る
   例えばINT_MAX - 2, -15にするとa - bはINT_MAXよりも大きくなる
   とにかくa > bの時にkが1になればいい
   オーバーフローする可能性はaが正の値でかつbが負の値の場合のみなので
   符号が異なるかどうかを調べればいい
   符号が異なる場合はkの値がsign(a)と等しくなるようにする
*/
/*
   aとbの符号が異なる時
   a>0 -> b<0 => k=1
   a<0 -> b>0 => k=0
   よってどちらの場合もk=sign(a)
*/
    let k = sign(a)
else
    let k = sign(a - b) //決してオーバーフローしない
public static int getMax(int a, int b) {
    int c = a - b;
    int sa = sign(a); //a>=0なら1、そうでなければ0
    int sb = sign(b); //b>=0なら1、そうでなければ0
    int sc = sign(c); //a-bがオーバーフローしたかどうかに依存
    /*
       a > bならば1
       a < bならば0となる値kを作る
       a = bの時はどちらでも良い
    */
    //aとbが異なる符号を持つ場合k=sign(a)
    int use_sign_of_a = sa ^ sb;
    //aとbが同じ符号を持つ場合k=sigin(a-b)
    int use_sign_of_c = flip(sa ^sb);

    int k =use_sign_of_a * sa + use_sign_of_c * sc;
    int q = flip(k); //kを反転する
    return a * k + b + q;
}
