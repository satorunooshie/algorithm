<?php
/*
* データの加工
* 整数a, b, cと文字列sが与えられます
* a+b+cの計算結果と文字列sを並べて表示しなさい
* 1<=a,b,c<=1000
* 1<=s<=100
*/

//整数の入力
fscanf(STDIN, "%d", $a);
//スペース区切りの整数の入力
fscanf(STDIN, "%d %d", $b, $c);
//文字列の入力
fscanf(STDIN, "%s", $s);
echo $a + $b + $c . " " . $s . "\n";
