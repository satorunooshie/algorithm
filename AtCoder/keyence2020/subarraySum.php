<?php
/*
 * 三つの整数N,K,Sが与えられます
 * 1以上10^9以下の整数からなる長さNの数列A_1, A_2, A_Nであって
 * 以下の条件を満たすものを一つ求めてください
 * なお制約の項で記述される条件の下で
 * このような数列は必ず存在することが証明できます
 * 1<=l<=r<=Nを満たす整数の組(l, r)であって
 * A_l+A_l+r+...A_r=Sを満たすものはちょうどK個ある
 * 1<=N<=10^5
 * 0<=K<=N
 * 1<=S<=10^9
 */
list($n, $k, $s) = ints();
for ($i = 0; $i < $n; $i++)
	$a[] = $i < $k ? $s : $s % 10 ** 9 + 1;
echo implode(' ', $a);
function ints() {
	return array_map('intval', explode(' ', trim(fgets(STDIN))));
}
