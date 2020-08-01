<?php
/*
 * 問題
 * 二つの整数a, bの積が偶数か奇数か判定
 * 1<=a, b<=10000
 */
fscanf(STDIN, "%d %d", $a,$b);
if ($a % 2 == 0 || $b % 2 == 0) {
	echo 'Even';
} else {
	echo 'Odd';
}
/*
 * list($a, $b) = explode(' ', trim(fgets(STDIN)));
 * echo ($a * $b) % 2 === 1 ? 'Odd' : 'Even';
 */
