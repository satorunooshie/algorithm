<?php
/*
 * N個の整数A_1...A_Nが与えられます
 * この中からちょうどK個の要素を選ぶとき
 * 選んだ要素の積としてありえる最大値を求めてください
 * そして答えを10^9+7で割った余りを0以上10^9+6以下の整数として
 * 出力してください
 * 1<=K<=N<=2*10^5
 * |A_i|<=10^9
 */

[$N, $K] = fscanf(STDIN, "%d%d");
const MOD = 1000000007;
$list = fscanf(STDIN, str_repeat("%d", $N));

$zero = 0;
$neg = [];
$pos = [];
foreach ($list as $value) {
	if ($value < 0) {
		$neg[] = $value;
	} elseif ($value > 0) {
		$pos[] = $value;
	} else {
		$zero++;
	}
}
$count_neg = count($neg);
$count_pos = count($pos);
sort($neg);
rsort($pos);
if ($count_neg + $count_pos < $K) {
	echo 0;
	exit;
}
$ans = 1;
if ($count_pos === 0) {
	if ($K % 2 == 1) {
		if ($zero > 0) {
			echo 0;
			exit;
		}
		for ($i = $count_neg - 1; $i >= $count_neg - $K; $i--) {
			$ans *= $neg[$i];
			$ans %= MOD;
		}
	} else {
		for ($i = 0; $i < $K; $i++) {
			$ans *= $neg[$i];
			$ans %= MOD;
		}
	}
} else {
	$neg_now = 0;
	$pos_now = 0;
	while ($K > $pos) {
		$ans *= $neg[$neg_now];
		$ans %= MOD;
		$neg_now++;
		$ans *= $neg[$neg_now];
		$ans %= MOD;
		$neg_now++;
		$K -= 2;
	}
	while ($K > 0) {
		if ($K > 1 && isset($neg[$neg_now + 1])) {
			$tmp_neg = $neg[$neg_now] * $neg[$neg_now + 1];
			if (isset($pos[$pos_now + 1])) {
				$tmp_pos = $pos[$pos_now] * $pos[$pos_now + 1];
			} else {
				$tmp_pos = 0;
			}
			if ($tmp_neg > $tmp_pos) {
				$ans *= $neg[$neg_now];
				$ans %= MOD;
				$neg_now++;
				$ans *= $neg[$neg_now];
				$ans %= MOD;
				$neg_now++;
				$K -= 2;
			} else {
				$ans *= $pos[$pos_now];
				$ans %= MOD;
				$pos_now++;
				$K--;
			}
		} else {
			if (isset($pos[$pos_now])) {
				$ans *= $pos[$pos_now];
				$ans %= MOD;
				$pos_now++;
				$K--;
			} else {
				$ans *= $neg[$neg_now];
				$ans %= MOD;
				$neg_now++;
				$K--;
			}
		}
	}
}
if ($ans >= MOD) {
	$ans %= MOD;
} elseif ($ans < 0) {
	if ($zero > 0) {
		echo 0;
		exit;
	}
	while ($ans < 0) {
		$ans += MOD;
	}
}
echo $ans;
