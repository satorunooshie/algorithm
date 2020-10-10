<?php
/*
 * フィボナッチ数列
 */
echo 'メモ化再帰を使わない場合のgetFib(35)を求める';
echo "\n";
$startTime = microtime(true);
echo 'getFib(35)の答えは' . getFib(35);
$endTime = microtime(true);
$deltaTime = $endTime - $startTime;
echo "\n";
echo '処理にかかった時間は' . $deltaTime . 'ミリ秒です';
echo "\n";
echo 'メモ化再帰であれば$n=100もいけちゃう';
echo "\n";
echo 'getFibByMemo(100)の答えは' . getFibByMemo(100);

function getFIb($n) {
	if ($n < 3) {
		return $n;
	}
	return getFib($n - 1) + getFib($n - 2);
}

$memo = array();
function getFibByMemo($n) {
	global $memo;
	if ($n < 3) {
		return $n;
	}
	if (isset($memo[$n])) {
		return $memo[$n];
	}
	return $memo[$n] = getFibByMemo($n - 1) + getFibByMemo($n - 2);
}
