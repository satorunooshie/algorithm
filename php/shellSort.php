<?php
/*
 * 適当な間隔をあけたとびとびのデータ列に対してあらかじめソートしておき
 * 挿入ソートを適用する
 * h(n+1) = 3h(n) + 1という条件で選び出した数列
 * 1, 4, 13, 40, 121 ...
 * h(1) = 1
 * このhの中から配列サイズ / 9の値以下にすると効率がいい
 */

$list = range(0, 200, 1);
shuffle($list);

/*
echo "ソートする配列は";
var_dump($list);
echo "\n";
 */

$listCount = count($list);

//まず最初にとびとびの間隔を決める
$step = 1;
for ($step_tmp = 1; $step_tmp < $listCount / 9; $step_tmp = $step_tmp * 3 + 1) {
	$step = $step_tmp;
}

while ($step > 0) {
	//最初は離れたところで交換し次第に細かくしていく
	for ($index = $step; $index < $listCount; $index++) {
		$tmp = $list[$index];
		//考え方は挿入ソートとほぼ同じ
		//離れた相手と比較するだけ
		for ($j = $index - $step; $j >= 0 && $list[$j] > $tmp; $j = $j - $step) {
			$list[$j + $step] = $list[$j];
		}
		$list[$j + $step] = $tmp;
	}
	$step = $step / 3;
}
echo "ソート完了";
foreach ($list as $value) {
	echo $value . "\n";
}
echo "\n";
