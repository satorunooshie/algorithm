<?php
/*
 * 問題
 * 正の整数のリストを与えられたとき
 * かずを並び替えて可能な最大数を返す関数を記述せよ
 * [50, 2, 1, 9]が与えられたとき95021が答え
 */

$list = array(5, 50, 56);
//$list = array(50, 2, 1, 9);
usort($list, 'comp');
print_r(implode('', $list));

function comp($int1, $int2) {
	$str1 = $int1;
	$str2 = $int2;
	if ($str1 === $str2) {
		return 0;
	}
	return -1 * strcmp($str1 . $str2, $str2 . $str1);
}
