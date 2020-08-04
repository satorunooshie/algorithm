<?php
/*
 * 二次元平面上で旅行をしようとしています
 * 旅行プランでは時刻0に点(0,0)を出発し1以上N以下の各iに対し
 * 時刻t_iに点(x_i,y_i)を訪れる予定です
 * 時刻tに点(x,y)にいるとき、時刻t+1には
 * 点(x+1,y)(x-1,y)(x,y+1)(x,y-1)のうちいずれかに存在することができる
 * 但しその場にとどまることはできない
 * 旅行プランが実行可能かどうかを判断せよ
 */
$turn_val = (int)fgets(STDIN);
$x_now = 0;
$y_now = 0;
$time_now = 0;

for ($turn = 0; $turn < $turn_val; $turn++) {
	fscanf(STDIN, "%d %d %d", $time, $x, $y);
	while ($time_now < $time) {
		if ($x_now < $x) {
			$x_now++;
			echo 'stage1 ' . $x_now;
		} elseif ($x_now > $x) {
			$x_now--;
			echo 'stage2 ' . $x_now;
		} elseif ($y_now < $y) {
			$y_now++;
			echo 'stage3 ' . $y_now;
		} elseif ($y_now > $y) {
			$y_now--;
			echo 'stage4 ' . $y_now;
		} else {
			$y_now++;
			echo 'stage5 ' . $y_now;
		}
		echo "\n";
		$time_now++;
		echo 'Time' . $time_now;
	}
	if ($x_now != $x || $y_now != $y) {
		echo "No";
		return;
	}
}
echo "Yes";

