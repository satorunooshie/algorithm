<?php
/*
 * 君主は数字の4, 9が大嫌いです
 * それらの数字を使ってはいけないという法律を定めました
 * 数字が禁止されているのである数の十進表現を考えたとき
 * その桁に禁止された数字が一つでも含まれている場合、
 * その数を使うことはできません
 * 今まで使っていた数字を使えなくなったあなたは
 * 使う可能性がある数の区間[A, B] = {A, A+1, ..., B}に
 * いくつ禁止された数が含まれているかを確かめることにしました
 * そのプログラムを作ってください
 * この問題には二つのデータセットがあり
 * データセット枚に部分点が設定されている
 * 1<=A<=B<=10,000を満たすデータセット1に正解した30点が
 * 追加制約のないデータセット2に正解した場合は上記のデータセットとは別に
 * 70点が与えられる
 */
list($a, $b) = strs();
echo (solve($b) - solve($a - 1)) . "\n";
function solve(String $s): int {
	$l = strlen($s);
	$dp[0][0][0] = 1;
	for ($i = 1; $i <= $l; $i++) {
		$n = (int)$s[$i - 1];
		foreach ([0, 1] as $smaller) {
			foreach ([0, 1] as $hate_flag) {
				for ($x = 0; $x <= ($smaller ? 9 : $n); $x++) {
					$dp[$i][$smaller || $x < $n][$hate_flag || $x === 4 || $x === 9]
					+= ($dp[$i - 1][$smaller][$hate_flag] ?? 0);
				}
			}
		}
	}
	return $dp[$l][0][1] + $dp[$l][1][1];
}
function strs() {
	return explode(' ', trim(fgets(STDIN)));
}
