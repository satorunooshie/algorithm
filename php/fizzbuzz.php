<?php
/*
 * 問題
 * 1から順に数を表示
 * 3で割り切れる場合Fizz
 * 5で割り切れる場合Buzz
 * 3と5で割り切れる場合FizzBuzz
 * それ以外は数値をそのまま表示
 */
$endValue = 30;
for ($i = 1; $i < $endValue + 1; $i++) {
	if ($i % 15 == 0) {
		echo "FizzBuzz";
	} elseif ($i % 5 == 0) {
		echo "Buzz";
	} elseif ($i % 3 == 0) {
		echo "Fizz";
	} else {
		echo $i;
	}
	echo "\n";
}
