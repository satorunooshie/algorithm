<?php
/*
 * N個の非負整数A_1, ...A_N及び非負整数Kが与えられます
 * 0以上K以下の整数Xに対してf(X) - (X XOR A_1) + ...(X XOR A_N)とします
 * ここで非負整数a, bに対してaXORbは
 * aとbのビットごとの排他的論理和を表します
 * fの最大値を求めてください
 * XORとは整数A, Bのヒットごとの排他的論理和Xは以下のように定義されます
 * Xを二進表記した際の2^k(k>=0)の位の数は
 * A, Bを二進表記した際の2^kの位の数のうち一方のみが
 * 1であれば1、そうでなければ0である
 */
[$N, $K] = fscanf(STDIN, "%d%d");
$list = fscanf(STDIN, str_repeat("%d", $N));
const MAX = 100000000000;
$digit = 0;
$digit = (int)log($K * 2 + 1, 2);
$v = 1;
for ($i = 0; $i < $digit; $i++) {
	$ans[$i] = 0;
	$ni[$i] = $v;
	$v <<= 1;
}
foreach ($list as $value) {
	for ($i = 0; $i < $digit; $i++) {
		if (($value & $ni[$i]) > 0) {
			$ans[$i]++;
		}
	}
}
$half = intdiv($N + 1, 2);
$ech = 0;
for ($i = $digit - 1; $i >= 0; $i--) {
	if ($ans[$i] < $half) {
		if ($ech + $ni[$i] <= $K) {
			$ech += $ni[$i];
		}
	}
}
$ans = 0;
foreach ($list as $value) {
	$ans += $value ^ $ech;
}
echo $ans;
