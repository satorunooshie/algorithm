<?php
//二つの数値の最大公約数を求める

$m = 28;
$n = 77;
//--------------------------------------------------
function euclidean($m, $n) {
	if ($m < $n) {
		$r = $m;
		$m = $n;
		$n = $r;
	}
	/*
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
	 */
	if ($n == 0) {
		return $m;
	}
	return euclidean($n, $m % $n);
}
echo euclidean($n, $m) . "\n";
//-------------------------------------------
echo '一つ目' . $m . "\n";
echo '二つ目' . $n . "\n";
if ($m > $n) {
	$tmp = $m;
	$m = $n;
	$n = $tmp;
}
do {
	$d = $m % $n;
	$m = $n;
	$n = $d;
} while ($d != 0);
echo "二つの数の最大公約数は$m\n";
