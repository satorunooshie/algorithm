<?php
/*
 * 問題
 * for, while, 再帰を利用してリスト内の数字を合計する
 */

$list = array(1, 2, 3, 4, 5, 6);
echo getSumByFor($list);
echo "\n";
echo getSumByWhile($list);
echo "\n";
echo getSumByRecursive(0, $list);
echo "\n";

function getSumByFor($list) {
	$len = count($list);
	$sum = 0;

	for ($i = 0; $i < $len; $i++) {
		$sum += $list[$i];
	}
	return $sum;
}

function getSumByWhile($list) {
	$sum = 0;
	while (true) {
		if (empty($list)) {
			break;
		}
		$sum += array_shift($list);
	}
	return $sum;
}

function getSumByRecursive($sum, $list) {
	if (empty($list)) {
		return $sum;
	}
	$sum += array_shift($list);
	return getSumByRecursive($sum, $list);
}
