<?php
/*
 * ユークリッドの互除法は
 * 二つの自然数または整式の最大公約数を求める手法の一つである
 * 二つの自然数または整式a, b(a >= b)について
 * aのbによる余剰をrとすると
 * aとbとの最大公約数はbとrで割った剰余、
 * 除数rをその剰余で割った剰余と
 * 剰余を求める計算を逐次繰り返すと
 * 剰余が0になったときの序数が
 * a, bとの最大公約数となる
 */
echo '44, 11の最大公約数は';
var_dump(euclidean(44, 11));
echo "\n";
echo '44, 13の最大公約数は';
var_dump(euclidean(44, 13));
echo "\n";
echo '44, 28の最大公約数は';
var_dump(euclidean(44, 28));
echo "\n";
echo '44, 44の最大公約数は';
var_dump(euclidean(44, 44));
echo "\n";

function euclidean($x, $y) {
	if ($y == 0) {
		return $x;
	}
	return euclidean($y, (int)$x % $y);
}
