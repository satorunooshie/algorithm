<?php
/*
 * 問題
 * 最初の100個のフィボナッチ数のリストを計算する関数を記述
 * 定義ではフィボナッチ数列の最初の数字は0と1で次の数は前の二つの合計となる
 * 例えば最初の10個のフィボナッチ数列は0, 1, 1, 2, 3, 5, 8, 13, 21, 34
 *
 */
print_r(getSumFibonacci([0, 1], 0, 1, 100));

function getSumFibonacci($array, $firstValue, $secondValue, $limit) {
	if (count($array) == $limit) {
		return $array;
	} else {
		$array[] = $firstValue + $secondValue;
		return getSumFibonacci(
			$array, $secondValue, $firstValue + $secondValue, $limit
		);
	}
}
