<?php
$five_hund_num = (int)fgets(STDIN);
$one_hund_num = (int)fgets(STDIN);
$fifty_num = (int)fgets(STDIN);
$n = (int)fgets(STDIN);
$result = 0;

for ($i = 0; $i <= $five_hund_num; $i++) {
	for ($j = 0; $j <= $one_hund_num; $j++) {
		for ($k = 0; $k <= $fifty_num; $k++) {
			if ($i * 500 + $j * 100 + $k * 50 == $n) $result += 1;
		}
	}
}
echo $result;
/*
 * fscanf(STDIN, "%d", $a);
 * fscanf(STDIN, "%d", $b);
 * fscanf(STDIN, "%d", $c);
 * fscanf(STDIN, "%d", $x);
 */

/*
 * for ($i = 0; $i < 3; $i++) {
 * $num[$i] = intval(trim(fgets(STDIN)));
 * }
 * $a = intval(trim(fgets(STDIN)));
 * $count = 0;
 * for ($j = 0; $j <= $num[0]; $j++) {
 * for ($k = 0; $k <= $num[1]; $k++) {
 * for ($n = 0; $n <= $num[2]; $n++) {
 * if ((500 * $j + 100 * $k + $50 * $n) == $a) {
 * $count++:
 * }
 * }
 * }
 * }
 * echo $count;
 */
