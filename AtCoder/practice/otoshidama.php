<?php
/*
 * 10000, 5000, 1000円札をお札と呼ぶとします
 * 祖父から受け取ったお年玉袋にはお札がN枚入っていて
 * 合計Y円だったそうですが嘘かもしれません
 * このような状況がありえるか判定し
 * ありうる場合はお年玉袋の中身の候補を一つ見つけてください
 * なお、祖父は十分裕福でお年玉袋も十分大きかったものとします
 * 1<=N<=2000
 * 1000=Y<=2*10^7
 * Nは整数
 * Yは1000の倍数である
 *
 * N枚のお札の合計金額がY円となることがありえない場合は-1 -1 -1と出力
 * あり得る場合は一例をx y zと出力せよ
 * 複数の可能性が考えられるときはそのうちどれを出力しても良い
 */

list($number, $sum) = explode(' ', trim(fgets(STDIN)));
list($x, $y, $z) = 0;
for ($z = 0; $z <= $number; $z++) {
	for ($y = 0; $y <= $number - $z; $y++) {
		for ($x = 0; $x <= $number - $z - $y; $x++) {
			if ($sum == $x * 10000 + $y * 5000 + $z * 1000 &&
			$number == $x + $y + $z) {
				echo $x . " " . $y . " " . $z;
				exit();
			}
		}
	}
}
/*
for ($x = 0; $x <= $number; $x++) {
	for ($y = 0; $y <= $number - $x; $y++) {
		for ($z = 0; $z <= $number - $x -$y; $z++) {
			if ($sum == $x * 10000 + $y * 5000 + $z * 1000) {
				echo $x . " " . $y . " " . $z;
				exit();
			}
		}
	}
}
 */
echo '-1 -1 -1';
