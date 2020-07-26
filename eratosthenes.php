<?php
//素数出力
/*
自然数を2から順にNまで並べる．最初はすべてが素数であると仮定する．
2は素数であるが，2の倍数は素数でないので，N以下の2の倍数に順番に素数でないと印をつける．
2の次に小さい素数候補として残ったのは3であり，3は素数．3の倍数は素数でないので，素数でないと印をつける．
3の次に小さい素数候補として残ったのは5であり，5は素数．5の倍数は素数でないので，素数でないと印をつける．
以下同様．
*/

$x = 100;
function eratothenes($x) {
	$sqrt = floor(sqrt($x));
	$lists = array_fill(2, $x -1, true);
	for ($i = 2; $i <= $sqrt; $i++) {
		if (isset($lists[$i])) {
			for ($j = $i * 2; $j <= $x; $j += $i) {
				unset($lists[$j]);
			}
		}
	}
	return array_keys($lists);
}
print_r(eratothenes($x));
//--------------------------------------------------
$i = null;
$m = null;
$n = null;
$data = [];
$result = [];
for ($i = 0; $i < $x + 1; $i++) {
	$data[$i] = 1;
}
$data[0] = 0;
$data[1] = 0;
$m = 2;
$n = 0;
do {
	for ($i = 2 * $m; $i < $x + 1; $i += $m) {
		$data[$i] = 0;
	}
	$result[$n] = $m;
	$n++;
	do {
		$m++;
	} while ($data[$m] == 0 && $m < $x + 1);
} while ($m < $x + 1);
for ($i = 0; $i < $n; $i++) {
	echo $result[$i] . ', ';
	if (($i + 1) % 10 === 0) {
		echo "\n";
	}
}
echo "\n";

//---------------------------------------------------
$start = microtime(true);  //計測前の現在時刻を取得
for ($n = 0; $n < $x; $n++) {
	$array[$n] = $n;
}
for ($i = 2; $i < floor(sqrt($x)); $i++) {
	for ($j = $i + $i; $j < $x; $j += $i) {
		$array[$j] = 0;
	}
}
for ($n = 2; $n < $x; $n++) {
	if ($array[$n] > 1) {
		echo $n . ":";
	}
}
$end = microtime(true);      //計測後の現在時刻を取得
$time = $end - $start;  //かかった時間
echo "\n時間:". $time .'秒';
