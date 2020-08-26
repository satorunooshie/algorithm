<?php
/*
 * 高橋君はNこの風船をすべてわり
 * 得られる特典をできるだけ小さくする協議に参加しています
 * 風船には1からNまでの番号がつけれていて
 * 風船i(1<=i<=N)は競技開始時に高度H_iのところにあり
 * 1秒経過するにつれて高度がS_iだけ増加する
 * 高橋君は競技開始時に1個風船を割ることができ
 * そこから1秒ごとに1この風船を割ることができる
 * どの順番で風船を割るのかは高橋君が自由に決定できる
 * どの風船についてもその風船を割ることによるペナルティが発生する
 * ペナルティはその風船が割られた時の高度と等しい整数値となる
 * 高橋君が最終的に得る特典はN個の風船のペナルティのうち最大値となる
 * 高橋君が得ることのできる特典として考えられる最小値を求めよ
 * N
 * H_1 S_1
 * ...
 * H_N S_N
 * 一行目には風船の個数を表す整数N(1<=N<=100,000)が書かれている
 * 二行目からN行には風船に関する情報が与えられる
 * N行のうちi(1<=i<=N)行目には二つの整数H_i(1<=H_i<=1,000,000,000),
 * S_i(1<=S_i<=1,000,000,000)が空白区切りで与えられる
 * これは風船iが競技開始時に高度H_iにあり
 * 一秒経過するにつれて高度がS_iずつ上昇していくことを表す
 * この問題には部分点が設定されている
 * N<=50&&H_i<100,000&&S_i<=2,000を満たすデータセット1に正解した場合は
 * 30点が与えられる
 * 追加制約のないデータセット2に正解した場合は上記とは別に70点が与えられる
 */
fscanf(STDIN, "%d", $n);
$H = [];
$S = [];
for ($i = 0; $i < $n; $i++) {
	fscanf(STDIN, "%d %d", $h, $s);
	$H[] = $h;
	$S[] = $s;
}
function f($mid) {
	global $n;
	global $H;
	global $S;

	$count = array_fill(0, $n, 0);
	for ($i = 0; $i < $n; $i++) {
		if ($mid < $H[$i]) {
			return false;
		}
		++$count[min($n - 1, floor(($mid - $H[$i]) / $S[$i]))];
	}
	for ($i = 0; $i < $n - 1; $i++) {
		$count[$i + 1] += $count[$i];
	}
	for ($i = 0; $i < $n; $i++) {
		//割らないといけない風船の数はi個
		if ($i + 1 < $count[$i]) {
			return false;
		}
	}
	return true;
}

$l = 0;
$r = PHP_INT_MAX;
while ($r - $l >= 1) {
	$mid = floor(($r + $l) / 2);
	if (f($mid)) {
		$r = $mid;
	} else {
		$l = $mid + 1;
	}
}
echo $r . "\n";