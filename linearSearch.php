<?php
/*
 * リニアサーチ
 * 対象のデータを先頭から順番に調べていく手法
 */

/*
 * 問題
 * 95400の値が配列のどこにあるか調べよ
 */

$list = range(0, 1000000, 1);
$startTime = microtime(true);
define('SEARCHINGVALUE', 95400);
$length = count($list);
$isFind = false;
for ($i = 0; $i < $length; $i++) {
	if ($list[$i] === SEARCHINGVALUE) {
		$isFind = true;
		echo 'Found!' . $i;
		break;
	}
}
if (!$isFind) {
	echo 'Not Found';
}
echo "\n";

$endTime = microtime(true);
$deltaTime = $endTime - $startTime;
echo $deltaTime;
