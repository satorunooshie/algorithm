<?php
fscanf(STDIN, "%d %d %d", $H, $W, $K);
$map = [];
for ($i = 0; $i < $H; $i++) {
	fscanf(STDIN, "%s", $c);
	$map[$i] = str_split($c);
}
//var_dump($map);
$HW = pow(2, $H + $W);
$ans = 0;
for ($i = 1; $i < $HW; $i++) {
	$cnt = 0;
	$selH = intdiv($i, pow(2, $W));
	$selW = $i - pow(2, $W) * $selH;
	if ($selH === 0 || $selW === 0) continue;
	$cnt = 0;
	for ($y = 0; $y < $H; $y++) {
		if (($selH & pow(2, $y)) === 0) continue;
		for ($x = 0; $x < $W; $x++) {
			if (($selW & pow(2, $x)) === 0) continue;
			if ($map[$y][$x] === '#') $cnt++;
		}
	}
	if ($cnt === $K) $ans++;
}
printf("%d", $ans);
