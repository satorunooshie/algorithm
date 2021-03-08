<?php
/*
 * 問題
 * 交互に要素をとることで二つのリストを結合する関数を記述
 */

$list1 = array('a', 'b', 'c', 'd', 'e', 'f');
$list2 = array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
print_r(getAlternationArrayMerge($list1, $list2));
function getAlternationArrayMerge($list1, $list2) {
	$answer = array();
	while (true) {
		if (empty($list1) && empty($list2)) {
			break;
		}
		if ($list1) {
			$answer[] = array_shift($list1);
		}
		if ($list2) {
			$answer[] = array_shift($list2);
		}
	}
	return $answer;
}
