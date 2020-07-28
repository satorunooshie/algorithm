<?php
/*
 * クイックソート
 * 基準値よりも大きい値は右に
 * 小さい値は左に移動させこれを繰り返して
 * 比較する配列を狭めていき枢軸と比較ができなくなるまで繰り返す
 */

//0~200の配列を作成
$list = range(0, 200, 1);
//配列をシャッフルする
shuffle($list);
echo 'ソートする配列は';
var_dump($list);
echo "\n";

$listCount = count($list);
quickSort($list, 0, $listCount - 1);
echo 'ソート完了';
foreach ($list as $value) {
	echo $value . "\n";
}

function quickSort(&$list, $first, $last) {
	$firstPointer = $first;
	$lastPointer = $last;
	//枢軸値を決める、配列の中央値
	$centerValue = $list[intVal(($firstPointer + $lastPointer) / 2)];

	//並び替えができなくなるまで
	do {
		//枢軸よりも左側で値が小さい場合はポインターを進める
		while ($list[$firstPointer] < $centerValue) {
			$firstPointer++;
		}
		//枢軸よりも右側で値が大きい場合はポインタを減らす
		while ($list[$lastPointer] > $centerValue) {
			$lastPointer--;
		}
		//この操作で左側と右側の値を交換する場合は特定

		if ($firstPointer <= $lastPointer) {
			//ポインターが逆転していないときは交換可能
			$tmp = $list[$lastPointer];
			$list[$lastPointer] = $list[$firstPointer];
			$list[$firstPointer] = $tmp;
			//ポインタを進めて分割する位置を指定
			$firstPointer++;
			$lastPointer--;
		}
	} while ($firstPointer <= $lastPointer);

	if ($first < $lastPointer) {
		//左側が比較可能の時
		quickSort($list, $first, $lastPointer);
	}

	if ($firstPointer < $last) {
		//右側が比較可能の時
		quickSort($list, $firstPointer, $last);
	}
}
