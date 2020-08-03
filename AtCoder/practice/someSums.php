<?php
/*
 * 1からN以下の整数のうち10新法で各桁の和が
 * A以上B以下であるものの総和を求めてください
 * 1<=N<=10^4
 * 1<=A<=B<=36
 */
list($a, $b, $c) = explode(' ', trim(fgets(STDIN)));

function sum($n) {
	if ($n < 10) {
		return $n;
	}
	return floor(sum($n / 10)) + $n % 10;
}
$sum = 0;
for ($i = 0; $i <= $a; $i++) {
	if (sum($i) >= $b && sum($i) <= $c) {
		$sum += $i;
	}
}
echo $sum;
/*
 * fscanf(STDIN, "%s %s %s", $n, $a, $b);
 * 各桁の和が条件を満たしている数
 * $array = [];
 * for ($i = 1; $i <= $n; $i++) {
 * $num = $i;
 * $sum = 0;
 * while (1 <= $num) {
 * $sum += $num % 10;
 * $num /= 10;
 * }
 * if ($a <= $sum && $sum <= $b) {
 * $array[] = $i;
 * }
 * }
 * echo array_sum($array);
 */
/*
 * list($num, $min, $max) = explode(' ', trim(fgets(STDIN)));
 * $ret = 0;
 * for ($i = 1; $i <= $num; $i++) {
 * $sum = array_sum(str_split($i));
 * if ($min <= $sum && $sum <= $max) {
 * $ret += $i;
 * }
 * }
 * echo $ret;
 */
