<?php
/*
 * 整数xに対しxの正の約数の個数をf(x)とします
 * 整数nが与えられるのでΣn_k=1 K*f(K)を求めよ
 * 1<=N<=10^7
 * ex
 * 4
 * f(1)=1,f(2)=2,f(3)=2...=23
 */
$n = trim(fgets(STDIN));
$ans = 0;
for ($i = 1; $i <= $n; $i++) {
	$t = floor($n / $i);
	$ans += $t * ($t + 1) * $i / 2;
}
echo sprintf("%.0f", $ans) . "\n";
