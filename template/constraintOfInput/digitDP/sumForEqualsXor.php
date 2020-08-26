<?php
/*
 * 正整数Lが二進数表記で与えられます
 * 以下の条件を満たす非負整数a, bの組がいくつ存在するか求めてください
 * a+b<=L
 * a+b=aXORb
 * 但しこの値は非常に大きくなることがあるので
 * 10^9+7で割った余りを出力してください
 * Lは二進数表記で与えられ先頭文字は必ず1である
 */

define('MOD', 10 ** 9 + 7);
list($s) = strs();
$l = strlen($s);
//dp[i][flg] := 上からi桁目までを見たときに
//L以下であることがflag(未確定/確定)であるような条件を満たす組み合わせの数
$dp[0][0] = 1;
$dp[0][1] = 0;
for ($i = 1; $i <= $l; $i++) {
	if ($s[$i - 1] === '0') {
		$dp[$i][0] = $dp[$i - 1][0];
		$dp[$i][1] = modMul($dp[$i - 1][1], 3);
	} else {
		$dp[$i][0] = modMul($dp[$i - 1][0], 2);
		$dp[$i][1] = modAdd(modMul($dp[$i - 1][1], 3), $dp[$i - 1][0]);
	}
}
echo modAdd($dp[$l][0], $dp[$l][1]);
function strs() {
	return explode(' ', trim(fgets(STDIN)));
}
function modAdd($x, $y) {
	return ($x + $y) % MOD;
}
function modSub($x, $y) {
	return ($x + MOD - $y) % MOD;
}
function modMul($x, $y) {
	return ($x * $y) % MOD;
}
function modDiv($x, $y) {
	return modMul($x, modPow($y, MOD - 2));
}
//累乗（繰り返し二乗法）
function modPow($n, $x) {
	if ($x === 0) return 1;
	$res = (modPow($n, $x >> 1) ** 2) % MOD;
	if ($x % 2 === 1) $res = modMul($res, $n);
	return $res;
}
//階乗
function modFac($n) {
	if ($n === 0) return 1;
	return modMul($n, modFac($n - 1));
}
//順列
function nPr($n, $r) {
	if ($r === 0) return 1;
	return modMul(nPr($n, $r - 1), $n - $r + 1);
}
//組み合わせ
function nCr($n, $r) {
	$r = min($r, $n - $r);
	if ($r === 0) return 1;
	return modDiv(nPr($n, $r), modFac($r));
}
//階乗、逆元を前処理
function init($n) {
	global $fact, $ifact;
	$facr[0] = 1;
	for ($i = 1; $i <= $n; $i++) $fact[$i] = modMul($fact[$i - 1], $i);
	$ifact[$n] = modDiv(1, $fact[$n]);
	for ($i = $n; $i >= 1; $i--) {
		$ifact[$i - 1] = modMul($ifact[$i], $i);
	}
}
//順列（前処理）
function nPR2($n, $r) {
	global $fact, $ifact;
	return modMul($fact[$n], $ifact[$n - $r]);
}
//組み合わせ（前処理）
function nCr2($n, $r) {
	global $fact, $ifact;
	return modMul(modMul($fact[$n], $ifact[$r]), $ifact[$n - $r]);
}
