<?php
fscanf(STDIN, "%d", $n);
$position = [0, 0];
$count = 0;
$can_arrive = true;

for ($i = 0; $i < $n; $i++) {
	fscanf(STDIN, "%d %d %d", $t, $x, $y);
	while ($count <= $t) {
		if ($position[0] < $x) {
			$position[0]++;
		} elseif ($position[1] < $y) {
			$position[1]++;
		} elseif ($position[0] > $x) {
			$position[0]--;
		} elseif ($position[1] > $y) {
			$position[1]--;
		} else {
			//到着、残り時間が偶数なら次へ奇数ならNo
			if (($t - $count) % 2 == 0) {
				$count = $t;
				break;
			} else {
				$can_arrive = false;
				break 2;
			}
		}
		if ($count == $t && ($position[0] != $x || $position[1] != $y)) {
			$can_arrive = false;
			break;
		}
		$count++;
	}
}
if ($can_arrive) {
	echo "Yes";
} else {
	echo "No";
}
