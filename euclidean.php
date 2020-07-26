<?php
//二つの数値の最大公約数を求める
$m = 28;
$n = 77;
function euclidean($m, $n) {
	if ($m < $n) {
		$r = $m;
		$m = $n;
		$n = $r;
	}
	$d = 0;
	while ($d >= 0) {
		$d = $m % $n;
		if ($d === 0) {
			$ans = $n;
			break;
		}
		$m = $n;
		$n = $d;
	}
	return $ans;
}
echo euclidean($m, $n);
