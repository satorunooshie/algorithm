<?php
/*
 * 問題
 * 黒板にN個の正の整数A_1, ..., A_Nが書かれている
 * 黒板に書かれている整数がすべて偶数の時
 * 黒板に書かれている整数すべてを2で割ったものに置き換える
 * 最大何回操作できるか
 * 1<=N<=200
 * 1<=A_i<=10^9
 */
/*
$n = trim(fgets(STDIN));
$a = explode(" ", trim(fgets(STDIN)));
//var_dump($a);
//var_dump(str_split($a, 3));
$count = 0;
$i = 0;
function count_divide(&$count, $i, $n, &$a) {
	while ($i < $n) {
		if ($a[$i] % 2 == 0) {
			$a[$i] /= 2;
			var_dump($a[$i]);
			$i++;
		} else {
			break;
		}
		$count++;
		return count_divide($count, $i, $n, $a);
	}
	return $count;
}
count_divide($count, $i, $n, $a);
echo $count;
 */
$num = fgets(STDIN);
$line = explode(" ", fgets(STDIN));
$min = 100;
for ($i = 0; $i < $num; $i++) {
	for ($j = 0; $line[$i] % 2 == 0; $j++) {
		$line[$i] /= 2;
	}
	$min = min($j, $min);
}
echo $min;
/*
 * $num = trim(fgets(STDIN));
 * $naturalNumbers = explode(" ", trim(fgets(STDIN)));
 * $i = 0;
 * try {
 * while (true) {
 * $naturalNumbers = array_map(function($val) {
 * if ($val % 2 === 1) {
 * throw new Exception();
 * }
 * return $val / 2;
 * }, $naturalNumbers);
 * $i++;
 * }
 * } catch (Exception $e) {
 * }
 * echo $i;
 */

/*
 * $n = fgets(STDIN);
 * $temp_array = explode(" ", fgets(STDIN));
 * $array = [];
 * foreach ($temp_array as $d) {
 * $array[] = (int)$d;
 * }
 * $array_num = [];
 * for ($i = 0; $i < count($array); $i++) {
 * $j = 0;
 * while ($array[$i] % 2 == 0) {
 * $array[$i] /= 2;
 * $j++;
 * }
 * $array_num = $j;
 * }
 * echo min($array_num);
 */

