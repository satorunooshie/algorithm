<?php
/*
 * 問題
 * 1, 2, 3の番号が付いた三つのマスからなるマス目を持っています
 * 各マスには0か1が書かれている
 * 1が書かれたマスにビー玉を置くときビー玉が置かれるマスがいくつあるか
 */
$a = str_split(trim(fgets(STDIN)));
//echo array_search('1', $a, true)?: 0;
$count = 0;
foreach ($a as $value) {
	if ($value === '1') {
		$count++;
	}
}
echo $count;
/*
 * echo substr_count(fgets(STDIN), 1);
 */
