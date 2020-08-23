<?php
/*
 * H行W列に並ぶマスからなるマス目があります
 * 上からi行目左からj列目(1<=i<=H,1<=j<=W)のマスの色は
 * 文字c_i,jとして与えられc_i,jが'.'の時白、'#'の時黒です
 * 次の操作を行うことを考えます
 * 行を何行か選び列を何列か選ぶ。なお０行でもいい
 * そして選んだ業に含まれるマスと
 * 選んだ列に含まれるマスをすべて赤く塗る
 * 正の整数Kが与えられるとき操作後に黒いマスがちょうどK個残るような
 * 行と列の選び方は何通りでしょうか
 * ここで二つの選び方は一方においてのみ選ばれる行または列が存在するときに
 * 異なるとみなされます
 * 1<=H,W<=6
 * 1<=K<=HW
 */
list($H, $W, $K) = array_map('intval', explode(' ', trim(fgets(STDIN))));
for ($i = 0; $i < $H; $i++) {
	$map_info[] = str_split(trim(fgets(STDIN)));
}
$count = 0;
$main_map_info = $map_info;

//列に対してビット全探索を行う
for ($bit = 0; $bit < (1 << $W); $bit++) {
	$map_info = $main_map_info;
	for ($i = 0; $i < $W; $i++) {
		if ($bit & (1 << $i)) {
			//$iが赤色にする列を決める
			for ($x = 0; $x < $H; $x++) {
				$map_info[$x][$i] = 'red';
			}
		}
	}
	//ここで一度保存
	$map_info_sub = $map_info;

	//行に対してビット全探索を行う
	for ($bitx = 0; $bitx < (1 << $H); $bitx++) {
		$map_info = $map_info_sub;
		for ($j = 0; $j < $H; $j++) {
			if ($bitx & (1 << $j)) {
				//$jが赤色になる行を決める
				for ($y = 0; $y < $W; $y++) {
					$map_info[$j][$y] = 'red';
				}
			}
		}
		//黒の残りの数を数える
		$sum = 0;
		foreach ($map_info as $value) {
			$output = array_count_values($value);
			if (!empty($output['#'])) {
				$sum += $output['#'];
			}
		}
		if ($sum == $K) {
			$count++;
		}
	}
}
echo $count;
