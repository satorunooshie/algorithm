<?php
/*
 * 単純選択ソート
 * ソート済みでないものから最小値を探して
 * 1個ずつ並べていく方法
 */
$list = range(0, 200, 1);
shuffle($list);

echo "ソートする配列は";
var_dump($list);
echo "\n";

$listCount = count($list);
$isChange = false;

for ($sortedCount = 0; $sortedCount < $listCount; $sortedCount++) {
	//最小値のキーの変数を定義
	//まずは基準位置をセット
	$minKey = $sortedCount;
	//最小値を探す
	for ($index = $sortedCount + 1; $index < $listCount; $index++) {
		if ($list[$index] < $list[$sortedCount]) {
			$minKey = $index;
			$isChange = true;
		}
		if ($isChange) {
			//最小値が変わったら
			$tmp = $list[$sortedCount];
			$list[$sortedCount] = $list[$minKey];
			$list[$index] = $tmp;
			$isChange = false;
		}
	}
}

echo "ソート完了";
foreach ($list as $value) {
	echo $value . "\n";
}
echo "\n";
