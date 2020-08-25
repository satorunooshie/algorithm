<?php
/*
 * 座標面上にN個の町があります
 * 町iは座標(x_i, y_i)に位置しています
 * 町iと町jの間の距離はroot(x_i - x_j)^2+(y_i - y_j)^2です
 * これらの街をすべて一回ずつ訪れるとき
 * 町を訪れる経路は全部ですN!通りあります
 * 一番目に訪れる街から出発し二番目に訪れる街、、、を経由しN番目に訪れる街に到着するまでの移動距離をその経路の長さとします
 * これらのN!通りの経路の長さの平均値を計算してください
 * 2<=N<=8
 * -1000<=x_i<=1000
 * -1000<=y_i<=1000
 * (x_i, y_i) != (x_j, y_j)
 * 出力はジャッジの出力との絶対誤差または相対誤差が10^-6以下の時正解と判定される
 */
fscanf(STDIN, "%d", $n);
$x = array();
$y = array();
for ($i = 0; $i < $n; $i++) {
	fscanf(STDIN, "%d %d", $x[$i], $y[$i]);
}
//総当たりパターン数
$k = 1;
for ($i = $n; $i > 0; $i--) {
	$k *= $i;
}
//距離配列
$distance_array = array();
$distance_total = 0;
//距離を求める
$diff_pattern = 0;
for ($i = 0; $i < $n; $i++) {
	for ($j = $i + 1; $j < $n; $j++) {
		$distance_array[$diff_pattern] = sqrt(pow(($x[$i] - $x[$j]), 2) +
			pow(($y[$i] - $y[$j]), 2));
		$distance_total += $distance_array[$diff_pattern];
		$diff_pattern++;
	}
}
$cond_num = ($k * ($n - 1)) / $diff_pattern;
echo $cond_num * $distance_total / $k;

