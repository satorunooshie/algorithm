<?php
/*
 * バイナリ―サーチ
 * ソート済みのリストや配列に入ったデータに対する検索を行うにあたって
 * 中央の値を見て検索したい値との大小関係を用いて
 * 検索したい値が中央の値の右にあるか
 * 左にあるかを判断して片側には存在しないことを
 * 確かめながら検索していく手法
 *
 */

/*
 * 問題
 * 95400の値が配列に存在するか調べよ
 */

//0から100000の配列を生成
//$list = range(0, 100000, 1);
$list = range(95000, 96000, 1);
$startTime = microtime(true);
$listCount = count($list);

define('SEARCHINGVALUE', 95400);

$firstIndex = 0;
$lastIndex = $listCount - 1;
$isFind = false;
do {
	$centerIndex = floor(($firstIndex + $lastIndex ) / 2);
	if ($list[$centerIndex] == SEARCHINGVALUE) {
		$isFind = true;
		echo 'Found!' . $centerIndex;
		break;
	} elseif ($list[$centerIndex] < SEARCHINGVALUE) {
		$firstIndex = $centerIndex + 1;
	} elseif ($list[$centerIndex] > SEARCHINGVALUE) {
		$lastIndex = $centerIndex - 1;
	}
} while ($firstIndex <= $lastIndex);

if (!$isFind) {
	echo 'Not Found';
}
echo "\n";

$endTime = microtime(true);
$deltaTime = $endTime - $startTime;
echo $deltaTime . "\n";
