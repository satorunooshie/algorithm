/* 0からnまで(nを含む)の整数の文字列表記に現れる二の個数を数えるメソッドの実装 */
/* ブルーとフォース */
//0からnに現れる'2'の個数を数える
int numberOf2sInRange(int n) {
    int count = 0;
    for (int i = 2; i <= n; i++) {
        //2から始めても問題なし
        count += numberOf2s(i);
    }
    return count;
}
//一つの数字に含まれる'2'の個数を数える
int numberOf2s(int n) {
    int count = 0;
    while (n > 0) {
        if (n % 10 == 2) {
            count++;
        }
        n /= 10;
    }
    return count;
}
//メソッドを切り離し、可読性をあげる
/*
 * 数の集まりとして見るのではなく、各桁ごとに10個ずつ区切ってみて見ると
 * 一行につき一の位が2になるものが一つ現れるので一の位が2の数は大体全体の10分の1になるということがわかる
 * ある桁に2が現れる数の個数はどの桁でも大体10分の1になる
 * 大体なのは範囲によって異なる可能性があるからで1-37の場合であれば10分の1よりも大きくなる
 * 2が現れる割合を正確に求めるために各桁の数に対してdigit < 2とdigit = 2、digit > 2の三つの場合に分ける
 */
/*
 * digit < 2の場合
 * x = 61523, d = 3としてx[d] = 1(d番目の桁(千の位)が1)の部分に注目する
 * 千の位に2が現れるのは2000-2999, 12000-12999, 22000-22999, 32000-32999, 42000-42999, 52000-52999の範囲で
 * 62000-62999は含まれないので千の位には合計6000個の2がある
 * これは0から60000までの範囲の千の位にある2の数を数えるのと同じ結果になる
 * つまりd桁目以下を切り捨て10^(d+1)の倍数に丸めてしまい、それを10で割ることで
 * d桁目に2が現れる数の個数を求めることができる
 */
if x[d] < 2: count2sInRangeAtDigit(x, d) =
    let y = round down to nearest 10^(d+1)
    return y / 10
/*
 * digit > 2の場合
 * 次はx[d] > 2になっている桁に注目する
 * x = 63525, d = 4として
 * 先ほどとほとんど同じようにして考えると0-632525の範囲にある3桁目の2の個数は0-70000の範囲にある三桁目の2の個数と一致する
 * 先ほどと違うのはd桁目以下を切り捨てるのではなく切り上げるということ
 */
if x[d] > 2: count2sInRangeAtDigit(x, d) =
    let y = round up to nearest 10^(d+1)
    return y / 10
/*
 * digit = 2の場合
 * x = 62523, d = 3として考えて見ると
 * 2000-2999, 12000-12999...52000-52999まではこれまでと同じように計算できるが
 * 62000-62523については524個あるがどのように計算すればいいか
 */
if x[d] > 2: count2sInRangeAtDigit(x, d) =
    let y = round down to nearest 10^(d+1)
    let z = right side of x (i.e., x % 10^d)
    return y / 10 + z + 1
/* 後はこの計算を書く桁に繰り返し適用するだけ */
public static int count2sInRangeAtDigit(int number, int d) {
    int powerOf10 = (int)Math.pow(10, d);
    int nextPowerOf10 = powerOf10 * 10;
    int right = number % powerOf10;

    int roundDown = number - number % nextPowerOf10;
    int roundUp = roundDown + nextPowerOf10;

    int digit = (number / powerOf10) % 10;
    if (digit < 2) { //注目している桁の数字で場合わけ
        return roundDown / 10;
    } else if (digit == 2) {
        return roundDown / 10 + right + 1;
    } else {
        return roundUp / 10;
    }
}
public static int count2sInRange(int number) {
    int count = 0;
    int len = String.valueOf(number).length();
    for (int digit = 0; digit < len; digit++) {
        count += count2sInRangeAtDigit(number, digit);
    }
    return count;
}
