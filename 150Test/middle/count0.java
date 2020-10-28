/* nの階乗を計算したときに末尾の連続する0の数を数えるアルゴリズム */
/*
   末尾の連続する0は10をかけるときにできるので
   5と2を1回ずつかける場合も10をかけたことになる
   例えば1*2*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19は
   19!=2*...*5*...*10*...*15*16*...
   なので0の数を数えるには5と2を何回かけているか数えるだけで良い
   2を掛ける回数の方が5をかける回数よりも常に多いはずなので
   単純に5をかけた回数を数えるだけで良い
*/
//2からn全ての数に対して5で割れる数を繰り返し数える
/*
   数が5の倍数なら5の何乗であるかを返す
   5->1
   25->2
*/
public int factorsOf5(int i) {
    int count = 0;
    while (i % 5 == 0) {
        count++;
        i /= 5;
    }
    return count;
}
public int countFactZeros1(int num) {
    int count = 0;
    for (int i = 2; i <= num; i++) {
        count += factorsOf5(i);
    }
    return count;
}
//1からnまでにあるmの倍数の数はnをmで割ることで簡単に数えられるので
public int countFactZeros2(int num) {
    int count = 0;
    if (num < 0) {
        return -1;
    }
    for (int i = 5; num / i > 0; i *= 5) {
        count += num / i;
    }
    return count;
}
