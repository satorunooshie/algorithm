<?php
/*
 * X段重ねの鏡餅(X>=1)とはX枚の円形の持ちを縦に積み重ねたもので
 * どの餅も真下の餅より半径が小さい(一番下の餅を除く)もののこと
 * N枚の円形の餅を持っていて
 * そのうちi枚目の餅の直径はd_iセンチメートルです
 * これらの餅のうち一部または全部を使って鏡餅を作るとき
 * 最大で何枚重ねの鏡餅を作ることができるでしょうか
 * 1<=N<=100
 * 1<=d_i<=100
 */
$x = trim(fgets(STDIN));
for ($i = 1; $i <= $x; $i++) {
	$array[] = trim(fgets(STDIN));
}
//var_dump($array);
rsort($array);
//var_dump($array);
echo count(array_unique($array));
/*
 * $n = trim(fgets(STDIN));
 * $stac = array();
 * for ($i = 0;$i < $n; $i++) {
 * $stac[] = trim(fgets(STDIN));
 * }
 * $stac = array_unique($stac);
 * echo count($stac);
 */
/*
 * fscanf(STDIN, "%d", $N);
 * for ($i = 0; $i < $N; $i++) {
 * fscanf(STDIN, "%d", $data[$i]);
 * }
 * rsort($data);
 * $step = 1;
 * $large = $data[0];
 * foreach ($data as $val) {
 * $step++;
 * $large = $val;
 * }
 * }
 * echo $step;
 */
/*
 * fscanf(STDIN, "%d", $n);
 * $d = array();
 * while (($val = intval(fgets(STDIN)))) {
 * array_push($d, $val);
 * }
 * echo count(array_unique($d));
 */
