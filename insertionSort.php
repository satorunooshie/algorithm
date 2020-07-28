<?php
/*
 * 単純挿入ソート
 * ソート済みの部分にソートされていない部分から
 * 要素を適切な位置に挿入することでソートする
 * 最初はソート済みが0個から始まる
 */

$list = range(0, 200, 1);
shuffle($list);

echo 'ソートする配列は';
var_dump($list);
echo "\n";

$listCount = count($list);
for ($sortedCount = 1; $sortedCount < $listCount; $sortedCount++) {
	//挿入する値を保存
	$tmp = $list[$sortedCount];
	//ソート位置を探す
	//$indexは減少していく数で1以上であることかつ
	//挿入する値よりも値が大きいときfor文の中身が実施される
	for ($index = $sortedCount; $index >= 1 && $list[$index - 1] > $tmp; $index--) {
		//一個ずらして保存
		$list[$index] = $list[$index - 1];
	}
	//ソート位置が決まったら挿入
	//var_dump($index); //for文の中で使われたindexの値-1がこのindex
	$indexMinusOne = $index;
	$list[$indexMinusOne] = $tmp;
}

echo 'ソート完了';
foreach ($list as $value) {
	echo $value . "\n";
}
echo "\n";
